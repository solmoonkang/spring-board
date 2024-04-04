package kr.co.noticeboard.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    /**
     * 요청되는 모든 URL을 허용하겠다는 의미이다.
     * @param httpSecurity
     * @return
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests((authorizeRequests ->
                authorizeRequests.anyRequest().permitAll())
        );

        return httpSecurity.build();
    }
}
