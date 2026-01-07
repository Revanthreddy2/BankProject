package com.auth.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
////        http
////            // Disable CSRF for APIs
////            .csrf(csrf -> csrf.disable())
////
////            // Allow all APIs (for development)
////            .authorizeHttpRequests(auth -> auth
////                    .requestMatchers(
////                            "/swagger-ui/**",
////                            "/v3/api-docs/**",
////                            "/swagger-ui.html",
////
////                            "/api/users/**",
////                            "/api/accounts/**",
////                            "/api/transactions/**"
////                    ).permitAll()
////                    .anyRequest().authenticated()
////            )
//    	// Disable default login & basic auth
////        .formLogin(form -> form.disable())
////        .httpBasic(basic -> basic.disable());
//
//    	
//    	http
//    	.cors(Customizer.withDefaults())
//    	.csrf(csrf -> csrf.disable())
//        .authorizeHttpRequests(auth -> auth
//            .requestMatchers("/api/auth/**",
//            		"/api/users/register",
//            		"/v3/api-docs/**").permitAll()
//            .requestMatchers("/api/admin/**").hasRole("ADMIN")
//            .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
//            .requestMatchers("/api/accounts/**").hasAnyRole("USER", "ADMIN")
//            .requestMatchers("/api/transactions/**").hasAnyRole("USER", "ADMIN")
//            .anyRequest().authenticated()
//        )
//        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//        .sessionManagement(session ->
//        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    );
//
//            
//        return http.build();
//    }

    
  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auth/**",
                    "/api/users/register",
                    "/v3/api-docs/**",
                    "/swagger-ui/**"
                ).permitAll()

                // Match the ROLE_ prefix from JWT
                .requestMatchers("/api/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/api/accounts/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers("/api/transactions/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

