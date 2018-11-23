package com.apap.ta46.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/js/**").permitAll()
			.antMatchers("/user/addUser").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/api/daftar-ranap").permitAll()
			
			
			//setting hak akses URL disini
			.antMatchers("/daftar-request/**").hasAnyAuthority("ADMIN")
			.antMatchers("/penanganan/**").hasAnyAuthority("ADMIN", "DOKTER")
			.antMatchers("/pasien-ranap/**").hasAnyAuthority("ADMIN", "DOKTER")
			.antMatchers("/obat/request/**").hasAnyAuthority("ADMIN")
			.antMatchers("/jadwal-jaga/**").hasAnyAuthority("ADMIN")
			.antMatchers("/kamar/**").hasAnyAuthority("ADMIN")
			.antMatchers("/daftar-ranap/**").hasAnyAuthority("ADMIN")
			.antMatchers("/daftar-pasien/**").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
			
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
	}
		
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
}
