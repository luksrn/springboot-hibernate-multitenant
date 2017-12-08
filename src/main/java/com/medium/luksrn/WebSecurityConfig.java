package com.medium.luksrn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medium.luksrn.app.security.CustomSuccessHandler;
import com.medium.luksrn.app.security.MultitenantAuthenticationProvider;
import com.medium.luksrn.app.security.MultitenantWebAuthenticationDetailsSource;
import com.medium.luksrn.multitenant.web.MultitenantHolderFilter;

/**
 * Configurações relacionadas ao Spring Security.
 * 
 * @author lucas.oliveira
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MultitenantAuthenticationProvider authenticationProvider;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new MultitenantHolderFilter(), UsernamePasswordAuthenticationFilter.class);
		
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.successHandler(new CustomSuccessHandler())
            	.authenticationDetailsSource(new MultitenantWebAuthenticationDetailsSource())
                .loginPage("/login")
                .permitAll();
		http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        
    }

    
}
