package com.esrayilmaz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.esrayilmaz.jwt.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public static final String AUTHENTICATE = "/authenticate";
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "/refreshToken";
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	
	
	public static final String[] SWAGGER_PATHS = {"/swagger-ui/**", "/v3/api-docs/**"};
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests(request->   								//lambda expression. herbir istegi yakaliyor
		request.requestMatchers(AUTHENTICATE, REGISTER, REFRESH_TOKEN).permitAll()	//burdaki endpointlerde yetki kontrolu yapma, direkt izin ver
		.requestMatchers(SWAGGER_PATHS).permitAll() 
		.anyRequest().authenticated())									//onun dısındakilerde filtreden gecir diyoruz. 
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)					//authenticationProvider olarak kendi yazdığımız authenticationProvider classını veriyoruz
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);	//filtre olarak da kendi yazdigimiz filtre classi verdik				

		return http.build();
	}
}
