package com.soft.app.JWTAuthenticationProject.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.soft.app.JWTAuthenticationProject.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //used to enable Spring Security global method security
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired private UserDetailsServiceImpl userDetailsService;
	
	@Autowired private JwtEntryPoint entryPoint;
	
	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/auth/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
