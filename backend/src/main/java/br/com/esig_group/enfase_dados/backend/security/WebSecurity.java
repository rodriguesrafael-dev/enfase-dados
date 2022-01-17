package br.com.esig_group.enfase_dados.backend.security;

import br.com.esig_group.enfase_dados.backend.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

   private final UserDetailsServiceImpl userDetailsService;
   private final PasswordEncoder passwordEncoder;
   private final JWTAttributes jwtAttributes;

   @Value("http://localhost:4200/")
   private String[] allowedOrigins;

   public WebSecurity(UserDetailsServiceImpl userService, 
                        PasswordEncoder passwordEncoder,
                        JWTAttributes jwtAttributes) {
      this.userDetailsService = userService;
      this.passwordEncoder = passwordEncoder;
      this.jwtAttributes = jwtAttributes;
   }

   @Override
   protected void configure(HttpSecurity httpSecurity) throws Exception {
      
      httpSecurity.authorizeRequests()
                  .antMatchers(  
                     "/csrf",
                     "/v2/api-docs",
                     "/swagger-resources/configuration/ui",
                     "/swagger-resources", 
                     "/swagger-resources/configuration/security", 
                     "/swagger-ui.html", 
                     "/webjars/**").permitAll();

      httpSecurity.headers().frameOptions().disable();

      httpSecurity.csrf().disable().cors().and().authorizeRequests()
                  .antMatchers(HttpMethod.POST, "/login").permitAll()
                  .antMatchers(HttpMethod.GET, "/api/user/passwordGen").permitAll().anyRequest().authenticated().and()
                  .addFilter(new JWTAuthenticationFilter
                  (authenticationManager(), jwtAttributes))
                  .addFilter(new JWTAuthorizationFilter
                  (authenticationManager(), jwtAttributes))
                  .cors().and().csrf().disable()
                  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
   }

   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder);
   }

   @Bean
   CorsConfigurationSource corsConfigurationSource() {

      CorsRegistry corsRegistry = new CorsRegistry();
      CorsConfiguration configuration = new CorsConfiguration();
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

      corsRegistry.addMapping("/**").allowedOriginPatterns(allowedOrigins).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
      configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
      configuration.setAllowedHeaders(Arrays.asList("*"));

      source.registerCorsConfiguration("/api/**", configuration);
      return source;
   }
   
}
