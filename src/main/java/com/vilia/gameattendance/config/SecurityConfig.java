package com.vilia.gameattendance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vilia.gameattendance.login.JwtTokenFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = false, securedEnabled = false, jsr250Enabled = true)
public class SecurityConfig {

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
			.anyRequest().authenticated();

		http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});
		
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		/*
		 * .and() .formLogin() .loginPage("/login/auth") .defaultSuccessUrl("/") .and()
		 * .logout() .logoutUrl("/logout") .logoutSuccessUrl("/login")
		 * .invalidateHttpSession(true) .deleteCookies("JSESSIONID") .and()
		 * .sessionManagement() .invalidSessionUrl("/login") .maximumSessions(1)
		 * .expiredUrl("/login") .sessionRegistry(sessionRegistry()) .and()
		 * .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		 */
	}

/*	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}*/

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
