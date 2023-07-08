package com.colegio.asistencia.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFDesigner {

    public Paragraph createTitle(String text, int fontSize, BaseColor fontColor, float borderWidth) {
        PdfPTable titleTable = new PdfPTable(1);
        PdfPCell titleCell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize, Font.NORMAL, fontColor)));
        titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        titleCell.setBorderWidthLeft(0);
        titleCell.setBorderWidthRight(0);
        titleCell.setBorderWidthTop(borderWidth);
        titleCell.setBorderWidthBottom(borderWidth);
        titleCell.setPaddingBottom(10f);
        titleTable.addCell(titleCell);
        return createParagraph(titleTable);
    }

    private Paragraph createParagraph(Element element) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(element);
        return paragraph;
    }

    public Paragraph createSubtitle(String text, int fontSize, int fontStyle, BaseColor fontColor) {
        PdfPTable subtitleTable = new PdfPTable(1);
        PdfPCell subtitleCell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.TIMES_ROMAN, fontSize, fontStyle, fontColor)));
        subtitleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        subtitleCell.setBorderWidthLeft(0);
        subtitleCell.setBorderWidthRight(0);
        subtitleCell.setBorderWidthTop(1f);
        subtitleCell.setBorderWidthBottom(1f);
        subtitleCell.setPaddingBottom(5f);
        subtitleTable.addCell(subtitleCell);
        return createParagraph(subtitleTable);
    }

    public PdfPCell createHeaderCell(String column, BaseColor backgroundColor, BaseColor fontColor) {
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(backgroundColor);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5);
        headerCell.setBorderColor(BaseColor.BLACK);
        headerCell.setBorderWidth(1f);
        headerCell.setBorderWidthLeft(0f);
        headerCell.setBorderWidthRight(0f);
        headerCell.setBorderWidthTop(1f);
        headerCell.setBorderWidthBottom(1f);
        headerCell.setPhrase(new Phrase(column.toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, fontColor)));
        return headerCell;
    }

    public PdfPCell createDataCell(String column, BaseColor backgroundColor, BaseColor fontColor) {
        PdfPCell dataCell = new PdfPCell();
        dataCell.setBackgroundColor(backgroundColor);
        dataCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dataCell.setPadding(5);
        dataCell.setBorderColor(BaseColor.LIGHT_GRAY);
        dataCell.setBorderWidth(1f);
        dataCell.setBorderWidthLeft(0);
        dataCell.setBorderWidthRight(0);
        dataCell.setBorderWidthTop(0);
        dataCell.setBorderWidthBottom(1f);
        dataCell.setPhrase(new Phrase(column, FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, fontColor)));
        return dataCell;
    }
}
