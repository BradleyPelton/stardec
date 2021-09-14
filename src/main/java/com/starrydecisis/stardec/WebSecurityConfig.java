package com.starrydecisis.stardec;

import com.starrydecisis.stardec.service.StarDecUserDetailsService;
import com.starrydecisis.stardec.util.filters.JwtRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private StarDecUserDetailsService starDecUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // Why is this necessary?
    // AuthenticationManagerBuilder and AuthenticationManager
    // Why is the builder not setting the bean for the AuthenticationManager?
    // SEE https://stackoverflow.com/a/21639553/12220587
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(starDecUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                // THYMELEAF LOGIN
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error.html")
                .and()


                // Options
                .csrf().disable()

                .authorizeRequests()

                // PERMIT ALL
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/page/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/constellations").permitAll()
                .antMatchers("/bodyNames").permitAll()
                .antMatchers("/bodyTypes").permitAll()
                .antMatchers("/descriptions").permitAll()
                .antMatchers("/smartSearch").permitAll()

                // REQUIRES AUTH
//                .antMatchers("/api/**")
                .antMatchers("/showNewDeepSkyBodyForm").hasAnyRole("ADMIN","USER")
//                .antMatchers("/deleteDeepSkyBody/{id}").hasAnyRole("ADMIN","USER")
//                .antMatchers("/showFormForUpdate/{id}").hasAnyRole("ADMIN","USER")
//                .antMatchers("/addNewBody").hasAnyRole("ADMIN","USER")


//                .and().formLogin();


                // IF URL DOESNT MATCH ANY PATTERN ABOVE,
                // DEFAULT TO REQUIRE authenticated()
                .anyRequest().authenticated().and()
                .exceptionHandling().and()

                // stateless session to ensure we don't reuse user's session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // THYMELEAF LOGOUT
                .and()
                .logout()
                .logoutSuccessUrl("/index.html");

                // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // TODO - CHANGE TO SOMETHING MORE SECURE
//        return BCryptPasswordEncoder();
    }
}
