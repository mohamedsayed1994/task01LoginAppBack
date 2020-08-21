package com.test.task01LoginAppBack.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String[] PUBLIC_ENDPOINTS = {
            "/api/login", "/api/auth/singIn"
//            , "/v2/api-docs","/configuration/ui", "/swagger-resources",
//            "/configuration/security", "/swagger-ui.html",
//            "/webjars/**","/swagger-resources/configuration/ui"
    };
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtUnAuthResponse jwtUnAuthResponse;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtUnAuthResponse)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManager();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
