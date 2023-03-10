/*
package com.sparta.clonesweetter.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig implements WebMvcConfigurer {

    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring()
                // .requestMatchers(PathRequest.toH2Console())
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //????????? ??? ??? ???????????? ?????? ???????????? ?????? ??????
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //????????????, ?????????,??????????????? security ?????? ????????? ?????????
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers("/docs").permitAll()
                .antMatchers("/api/members/signup").permitAll()
                .antMatchers("/api/members/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/studies/**").permitAll()
                .anyRequest().authenticated()

                // JWT ??????/????????? ???????????? ?????? ??????
                .and()
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //Controller ??? ?????? ?????????????????? ??????????????? ?????? Exceptionhandler??? ????????????
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // ?????????????????? ???????????? URL
                .allowedOrigins("https://viking-band-fe.vercel.app") // ????????? ????????? ????????? ??????, ?????? ?????? (??????????????? ????????? ????????????.
                .allowedMethods("*") // ?????? ???????????? ????????? ????????? (GET, POST...)
                .allowedHeaders("*", "Content-Type") // ?????? ???????????? ????????? ?????????
                .exposedHeaders("Authorization")
                .allowCredentials(true) // ?????? ????????? ????????????(?????? ????????? ????????? ???????????? ???????????? ??????????????????, true ????????? ????????? ????????? ????????? ??? ??????)
                .maxAge((long)3600 * 24 * 365); // preflight ????????? ?????? ????????? ?????????????????? ???????????? ??????;
    }
}
*/
