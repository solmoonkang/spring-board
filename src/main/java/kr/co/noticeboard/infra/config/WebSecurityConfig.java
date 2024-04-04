package kr.co.noticeboard.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final MemberDetailService memberDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        // 요청되는 모든 URL을 허용

        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/members").permitAll()
                .requestMatchers("/api/v1/members/**").authenticated();

        httpSecurity.formLogin().permitAll()
                .defaultSuccessUrl("/api/v1/members")
                .failureUrl("/api/v1/members/fail");

        httpSecurity
                .logout()
                .logoutUrl("/logout");

        httpSecurity.userDetailsService(memberDetailService);


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User
                    .withUsername("user1")
                    .password("1234")
                    .roles("user")
                    .build()
        );

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
