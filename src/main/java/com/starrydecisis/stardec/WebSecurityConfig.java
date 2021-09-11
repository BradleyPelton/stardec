package com.starrydecisis.stardec;

import com.starrydecisis.stardec.util.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()

                // PERMIT ALL
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/constellations").permitAll()
                .antMatchers("/bodyNames").permitAll()
                .antMatchers("/bodyTypes").permitAll()
                .antMatchers("/descriptions").permitAll()
                .antMatchers("/smartSearch").permitAll()

                // REQUIRES AUTH
//                .antMatchers("/showNewDeepSkyBodyForm").permitAll()
//                .antMatchers("/deleteDeepSkyBody/{id}").permitAll()
//                .antMatchers("/showFormForUpdate/{id}").permitAll()
//                .antMatchers("/saveDeepSkyBody").permitAll()

                // IF URL DOESNT MATCH ANY PATTERN ABOVE,
                // DEFAULT TO REQUIRE authenticated()
                .anyRequest().authenticated().and()
                .exceptionHandling().and().sessionManagement()

                // CLOSE BUILDER
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
