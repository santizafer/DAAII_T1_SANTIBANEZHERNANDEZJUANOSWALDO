package pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.service.DetalleUsuarioService;


@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private DetalleUsuarioService detalleUsuarioService;
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detalleUsuarioService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(
                        csrf -> csrf.disable()
                )
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/auth/login",
                                                "/auth/registrar",
                                                "/auth/cambiarcontrasena",
                                                "/auth/guardarusuario",
                                                "/auth/dashboard",
                                                "/resources/**",
                                                "/static/**",
                                                "/styles/**",
                                                "/scripts/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                ).formLogin(
                        login -> login.loginPage("/auth/login")
                                .defaultSuccessUrl("/auth/login-success")
                                .usernameParameter("nomusuario")
                                .passwordParameter("password")
                ).authenticationProvider(authenticationProvider());
        return httpSecurity.build();
    }

}