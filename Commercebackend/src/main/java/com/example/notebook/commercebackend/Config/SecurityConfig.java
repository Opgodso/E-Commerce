package com.example.notebook.commercebackend.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.GET,
                            "/products", "/products/**",
                            "/products-category", "/products-category/**",
                            "/search", "/search/**",
                            "/countries", "/countries/**",
                            "/states", "/states/**",
                            "/checkout", "/checkout/**","/checkout/payment"
                    ).permitAll()
//                    .requestMatchers("/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/checkout/payment", "/checkout/purchase").permitAll()
                    .requestMatchers(HttpMethod.POST, "/checkout", "/checkout/**").permitAll()
                    .anyRequest().authenticated()
            ).httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}

