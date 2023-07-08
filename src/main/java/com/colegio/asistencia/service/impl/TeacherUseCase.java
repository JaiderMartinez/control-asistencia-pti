package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.persistence.criteria.IStudentCriteriaRepository;
import com.colegio.asistencia.persistence.jpa.entity.AttendanceTypeEnum;
import com.colegio.asistencia.persistence.jpa.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.mapper.TeacherMapper;
import com.colegio.asistencia.model.PDFDesigner;
import com.colegio.asistencia.persistence.jpa.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.persistence.jpa.repository.IUserRepository;
import com.colegio.asistencia.service.ITeacherService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherUseCase implements ITeacherService {

    private static final String TITLE_OF_THE_PDF = "REPORTE EN EL AMBIENTE %s";
    private static final String SUBTITLE_OF_THE_PDF = "DETALLES DE ASISTENCIAS";
    private static final String COLUMN_ONE_NAME = "Estudiante";
    private static final String HOME_SYSTEM_ENVIRONMENT_VARIABLE = System.getProperty("user.home");
    private static final String MESSAGE_ILLEGAL_ARGUMENT_IN_THE_FILE = "La ruta de guardado no coincide con el directorio principal del usuario";
    private final IUserRepository userRepository;
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IStudentCriteriaRepository studentCriteriaRepository;

    @Override
    public void generatedReportInFormatPdf(Long codeEnvironmentPti, String pathToSaveFile) throws FileNotFoundException, DocumentException, UsernameNotFoundException, DataNotFoundException {
        EnvironmentPtiEntity environmentPtiEntity = getEnvironmentPtiEntityByCodePti(codeEnvironmentPti);
        Document document = createDocument(pathToSaveFile);
        PDFDesigner designerPdf = new PDFDesigner();
        PdfPTable table = createPdfTableHeaders(designerPdf);

        addTitleSection(document, designerPdf, environmentPtiEntity);
        addSubtitleSection(document, designerPdf);
        addAttendanceDataToTable(table, designerPdf, environmentPtiEntity);
        addTableToDocument(document, table);

        closeDocument(document);
        log.info("Se genero el documento en la ruta " + pathToSaveFile);
    }

    private EnvironmentPtiEntity getEnvironmentPtiEntityByCodePti(Long codePti) {
        return this.environmentPtiRepository.findById(codePti).orElseThrow();
    }

    private String validateAndAdjustFilePath(String pathToSaveFile) throws IllegalArgumentException {
        StringBuilder pathToSaveFileWithExtension = new StringBuilder(pathToSaveFile);
        if (!pathToSaveFile.startsWith(HOME_SYSTEM_ENVIRONMENT_VARIABLE)) {
            throw new IllegalArgumentException(MESSAGE_ILLEGAL_ARGUMENT_IN_THE_FILE);
        }
        if (!pathToSaveFile.endsWith(".pdf")) {
            pathToSaveFileWithExtension.append(".pdf");
        }
        return pathToSaveFileWithExtension.toString();
    }

    private Document createDocument(String pathToSaveFile) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(validateAndAdjustFilePath(pathToSaveFile)));
        return document;
    }

    private PdfPTable createPdfTableHeaders(PDFDesigner designerPdf) {
        PdfPTable table = new PdfPTable(5);
        table.addCell(designerPdf.createHeaderCell(COLUMN_ONE_NAME, BaseColor.GRAY, BaseColor.WHITE));
        table.addCell(designerPdf.createHeaderCell(AttendanceTypeEnum.ASISTENCIA.name(), BaseColor.GRAY, BaseColor.WHITE));
        table.addCell(designerPdf.createHeaderCell(AttendanceTypeEnum.INASISTENCIA.name(), BaseColor.GRAY, BaseColor.WHITE));
        table.addCell(designerPdf.createHeaderCell(AttendanceTypeEnum.EVASION.name(), BaseColor.GRAY, BaseColor.WHITE));
        table.addCell(designerPdf.createHeaderCell(AttendanceTypeEnum.RETRASO.name(), BaseColor.GRAY, BaseColor.WHITE));
        return table;
    }

    private void addTitleSection(Document document, PDFDesigner designerPdf, EnvironmentPtiEntity environmentPtiEntity) throws DocumentException {
        document.open();
        document.add(designerPdf.createTitle(String.format(TITLE_OF_THE_PDF, environmentPtiEntity.getName().toUpperCase()), 28, BaseColor.BLACK, 2f));
        document.add(Chunk.NEWLINE);
    }

    private void addSubtitleSection(Document document, PDFDesigner designerPdf) throws DocumentException {
        document.add(designerPdf.createSubtitle(SUBTITLE_OF_THE_PDF, 18, Font.BOLD, BaseColor.BLACK));
        document.add(Chunk.NEWLINE);
    }

    private void addAttendanceDataToTable(PdfPTable table, PDFDesigner designerPdf, EnvironmentPtiEntity environmentPtiEntity) {
        this.studentCriteriaRepository.getAllStudentAttendanceDetails(environmentPtiEntity.getCodePti()).forEach(attendance -> {
            table.addCell(designerPdf.createDataCell(attendance.getStudentName(), BaseColor.WHITE, BaseColor.BLACK));
            table.addCell(designerPdf.createDataCell(String.valueOf(attendance.getNumberOfAssists()), BaseColor.WHITE, BaseColor.BLACK));
            table.addCell(designerPdf.createDataCell(String.valueOf(attendance.getNumberOfFouls()), BaseColor.WHITE, BaseColor.BLACK));
            table.addCell(designerPdf.createDataCell(String.valueOf(attendance.getAmountOfClassAvoidance()), BaseColor.WHITE, BaseColor.BLACK));
            table.addCell(designerPdf.createDataCell(String.valueOf(attendance.getNumberOfDelays()), BaseColor.WHITE, BaseColor.BLACK));
        });
    }

    private void addTableToDocument(Document document, PdfPTable table) throws DocumentException {
        document.add(table);
    }

    private void closeDocument(Document document) {
        document.close();
    }

    @Override
    public List<EnvironmentOfPTIResponseDto> getAllEnvironmentsByDniOfTheTeacher(Long dniTeacher) {
        UserEntity teacher = getUserEntityByDni(dniTeacher);
        return this.environmentPtiRepository.getAllByUserEntityIdUser(teacher.getIdUser()).stream()
                .map(TeacherMapper::mapEnvironmentToEnvironmentOfPTIResponseDto)
                .toList();
    }

    private UserEntity getUserEntityByDni(Long dni) throws UsernameNotFoundException{
        return this.userRepository.findByEmployeeEntityDni(dni).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
