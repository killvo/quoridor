package com.quebec.core.domains.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers()
			.frameOptions()
			.sameOrigin()
			.and()
			.cors()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/game/**").permitAll();
	}
}
