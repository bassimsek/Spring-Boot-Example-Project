package com.example.demo.security;



import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.util.concurrent.TimeUnit;


import static com.example.demo.security.ApplicationUserPermission.WRITE;
import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig  {



    @Configuration
    @Order(1)
    public static class SecurityForAPI extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;
        private final ApplicationUserService applicationUserService;
        private final SecretKey secretKey;
        private final JwtConfig jwtConfig;

        @Autowired
        public SecurityForAPI(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, SecretKey secretKey, JwtConfig jwtConfig) {
            this.passwordEncoder = passwordEncoder;
            this.applicationUserService = applicationUserService;
            this.secretKey = secretKey;
            this.jwtConfig = jwtConfig;
        }


        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }


        // example for Jwt authentication fot other clients such as Android app, Web App, IOS App etc.
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // only for CUSTOMER role users
            http
                    .csrf().disable()
                    .antMatcher("/customer/api/**")
                    .authorizeRequests().antMatchers("/customer/api/register", "/customer/api/authenticate").permitAll()
                    .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // because JWT is stateless
                    .and()
                    .addFilterBefore(new JwtTokenVerifier(secretKey, jwtConfig), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/customer/api/**").hasRole(CUSTOMER.name())
                    .anyRequest()
                    .authenticated();

            // There is no concern about CSRF while using JWT.

        }



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // for database users (i.e normal customers)
            auth.authenticationProvider(daoAuthenticationProvider());
        }


        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(passwordEncoder);
            provider.setUserDetailsService(applicationUserService);
            return provider;
        }


    }




    @Configuration
    @Order(2)
    public static class SecurityForWebView extends WebSecurityConfigurerAdapter {

        private final PasswordEncoder passwordEncoder;

        @Autowired
        public SecurityForWebView(PasswordEncoder passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
        }



        // example for Form-based authentication
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // only for MANAGER and ADMINISTRATOR role users
            http
                    /*.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()*/ // Csrf should be enabled if there are "Form Submit" situations at browser (check again)
                    .csrf().disable()
                    .antMatcher("/management/api/**")
                    .authorizeRequests()
                    .antMatchers("/","index","/css/*","/js/*").permitAll()
                    .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(WRITE.getPermission())
                    .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(WRITE.getPermission())
                    .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(WRITE.getPermission())
                    .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(MANAGER.name(), ADMINISTRATOR.name())
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/management/api/authenticateTheUser") // custom endpoint according to corresponding Thymeleaf template
                        .defaultSuccessUrl("/greeting",true)
                    .and()
                    .rememberMe() // default to 2 weeks
                        .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                        .key("any_secure_key")
                    .and()
                    .logout()
                        .logoutUrl("/management/api/logout") // again custom endpoint according to corresponding Thymeleaf template
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID","remember-me")
                        .logoutSuccessUrl("/login");

        }



        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // for admins and managers, in-memory authentication can be used as an exercise
            auth.inMemoryAuthentication()
                    .withUser("manager1")
                    .password(passwordEncoder.encode("password123"))
                    .roles(MANAGER.name())
                    .authorities(MANAGER.getGrantedAuthorities());
            auth.inMemoryAuthentication()
                    .withUser("admin1")
                    .password(passwordEncoder.encode("password123"))
                    .roles(ADMINISTRATOR.name())
                    .authorities(ADMINISTRATOR.getGrantedAuthorities());
        }


    }

}


