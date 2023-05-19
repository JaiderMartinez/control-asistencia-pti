package com.colegio.asistencia.application.handler.implementation;

import com.colegio.asistencia.application.dto.UserSaveRequestDto;
import com.colegio.asistencia.application.handler.IAdminService;
import com.colegio.asistencia.application.mapper.request.IUserRequestMapper;
import com.colegio.asistencia.domain.api.IAdminUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class IAdminServiceImpl implements IAdminService {

    private final IAdminUseCasePort useCasePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveUser(UserSaveRequestDto userSaveRequestDto) {
        useCasePort.saveUser(userRequestMapper.toUserModel(userSaveRequestDto));
    }
}
