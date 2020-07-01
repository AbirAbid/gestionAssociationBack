package com.proxym.pfe.association.gestion_utilisateurs.security;

import com.proxym.pfe.association.gestion_utilisateurs.services.auth_services.UserDetailsServiceRestImp;
import com.proxym.pfe.association.gestion_utilisateurs.security.jwt.JwtAuthTokenFilter;
import com.proxym.pfe.association.gestion_utilisateurs.services.auth_services.UserDetailsServiceImpl;
import com.proxym.pfe.association.gestion_utilisateurs.security.jwt.JwtAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.sql.DataSource;

// @EnableWebSecurity is used to enable web security in a project.
// @EnableGlobalMethodSecurity(prePostEnabled = true) is used to enable Spring Security global method security.
@Configuration
@EnableWebSecurity
/*@EnableGlobalMethodSecurity(
        prePostEnabled = true
)*/
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)

public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter */ {
    //configuration  backoffice
    @Configuration
    @Order(2)
    public static class BOSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
        }

        @Autowired
        UserDetailsServiceImpl userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

            // Setting Service to find User in the database.
            // And Setting PassswordEncoder
            auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().disable();
            // The pages does not require login
            http.authorizeRequests().antMatchers("/login", "/logout").permitAll();

            // For ADMIN only.
            http.authorizeRequests().antMatchers("/evenement/**", "/membres/**", "/", "/cotisations/**", "/sponsors/**", "/mission/**", "/bien/**").access("hasRole('ROLE_ADMIN')");
            // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
            // If no login, it will redirect to /login page.

            // For ADMIN only.

            // When the user has logged in as XX.
            // But access a page that requires role YY,
            // AccessDeniedException will be thrown.
            http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

            // Config for Login Form
            http.authorizeRequests().and().formLogin()//
                    // Submit URL of login page.
                    .loginProcessingUrl("/j_spring_security_check") // Submit URL
                    .loginPage("/login")//
                    .defaultSuccessUrl("/")//
                    .failureUrl("/login?error=true")//
                    .usernameParameter("username")//
                    .passwordParameter("password")
                    // Config for Logout Page
                    .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

            // Config Remember Me.
            http.authorizeRequests().and() //
                    .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                    .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h


        }


        // Token stored in Table (Persistent_Logins)

        /* @Bean
         public PersistentTokenRepository persistentTokenRepository() {
             JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
             db.setDataSource(dataSource);
             return db;
         }*/
        ///stored / Token in Memory (Of Web Server).
        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
            return memory;
        }
    }


    /*******************configuration partie angular***************************/


    @Configuration
    @Order(1)
    public static class APIBEWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserDetailsServiceRestImp userRestDetailsServiceImpl;
        /*@Autowired
        UserDetailsServiceImpl userRestDetailsServiceImpl;*/

        @Autowired
        private JwtAuthEntryPoint unauthorizedHandler;

        @Bean
        public JwtAuthTokenFilter authenticationJwtTokenFilter() {

            return new JwtAuthTokenFilter();
        }

        @Override
        public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

            authenticationManagerBuilder
                    .userDetailsService(userRestDetailsServiceImpl)
                    .passwordEncoder(passwordEncoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {


            return super.authenticationManagerBean();
        }

        /*  @Autowired
          public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
              *//*
            auth.userDetailsService(userApiDetailsServiceImpl)
                    .passwordEncoder(passwordEncoder());
            *//*
        }
*/
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .headers()
                    .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
            http.cors().and().csrf().disable()
                    .antMatcher("/apiMembreAuthoriser/**")
                    .authorizeRequests()
                    .antMatchers(
                            "/api/auth/**","/api/biens/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

}

