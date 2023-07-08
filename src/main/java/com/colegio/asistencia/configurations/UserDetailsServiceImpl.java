package com.colegio.asistencia.configurations;

import com.colegio.asistencia.persistence.jpa.entity.UserEntity;
import com.colegio.asistencia.persistence.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;
    private static final String MESSAGE_DNI_NOT_FOUND = "No estas registrado";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userFound = this.userRepository.findByEmployeeEntityDni(Long.valueOf(username))
                            .orElseThrow( () -> new UsernameNotFoundException(MESSAGE_DNI_NOT_FOUND) );
        return new UserDetailsImpl(userFound);
    }
}
