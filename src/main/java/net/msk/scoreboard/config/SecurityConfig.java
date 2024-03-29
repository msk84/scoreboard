package net.msk.scoreboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/actuator/**").permitAll()
				.and().csrf().ignoringAntMatchers("/api/**");
	}
}
