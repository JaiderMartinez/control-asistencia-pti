package com.colegio.asistencia.configurations;

import com.colegio.asistencia.models.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String[] resources = new String[]{"/include/**","/css/**","/static/**","/img/**","/js/**","/layer/**",
            "/index/inicio-sesion", "/asistencia/comun/sing-in", "/asistencia/reporte"};
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().and()
                .csrf().disable()
                .authorizeRequests( authz -> authz.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .antMatchers(resources)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin()
                        .permitAll()
                        .loginPage(Constants.PATH_GET_MAPPING_LOGIN)
                        .usernameParameter(USERNAME_PARAMETER).passwordParameter(PASSWORD_PARAMETER)
                        .defaultSuccessUrl(Constants.PATH_GET_MAPPING_INDEX, true)
                        .failureUrl(Constants.PATH_GET_MAPPING_LOGIN)
                        .and()
                .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl(Constants.PATH_GET_MAPPING_LOGIN)
                        .permitAll()
                        .and()
                .build();
    }
}
