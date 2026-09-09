package com.ecommerce.www.config;
import java.util.List;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(
	        HttpSecurity http,
	        JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable()).cors(cors -> {})

	        .authorizeHttpRequests(auth -> auth
	        	    .requestMatchers(
	        	            "/api/users/register",
	        	            "/api/users/login"
	        	        ).permitAll()

	        	        .requestMatchers(HttpMethod.POST, "/api/products/**").hasAuthority("ADMIN")
	        	        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAuthority("ADMIN")
	        	        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority("ADMIN")

	        	        .anyRequest().authenticated()
	        	    )
	        

	        .addFilterBefore(
	            jwtAuthenticationFilter,
	            UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

	    CorsConfiguration configuration = new CorsConfiguration();

	    configuration.setAllowedOrigins(
	            List.of("http://localhost:5173")
	    );

	    configuration.setAllowedMethods(
	            List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
	    );

	    configuration.setAllowedHeaders(
	            List.of("*")
	    );

	    configuration.setAllowCredentials(false);

	    UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
}
