package com.colegio.asistencia.infrastructure.adapters.driven.persistence;

import com.colegio.asistencia.domain.model.UserModel;
import com.colegio.asistencia.domain.spi.IUserPersistencePort;
import com.colegio.asistencia.infrastructure.adapters.driven.mapper.IUserEntityMapper;
import com.colegio.asistencia.infrastructure.adapters.driven.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userEntityMapper.toUserModel(userRepository.save(userEntityMapper.toUserEntity(userModel)));
    }
}
