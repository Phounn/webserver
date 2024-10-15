package gov.la.webserver.config;

import gov.la.webserver.user.entity.Role;
import gov.la.webserver.user.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = false)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    final UserLogService userLogService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(e -> e.loginPage("/login").successForwardUrl("/main").permitAll())
                .userDetailsService(userLogService)
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequest -> {
                    authorizeRequest
                            //for home page
                            .requestMatchers("/main/logout").permitAll()
                            // for h2 console
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                            //for swagger
                            .requestMatchers(AntPathRequestMatcher.antMatcher("swagger-ui/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/v3/api-docs/**")).permitAll()
                            //for User API
//                            .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/users/**")).permitAll()
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/api/v1/user-change-logs/**")).hasRole("ADMIN")
                            .anyRequest().authenticated();

                })
                .headers(headers -> {
                    headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                .logout((logout) -> {
                    logout.logoutSuccessUrl("/main/input")
                            .deleteCookies( "JSESSIONID", "remember-me");
                });

        return http.build();


    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
