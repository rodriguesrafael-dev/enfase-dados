package br.com.esig_group.enfase_dados.backend.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JWTAttributes {

  private final String headerString = "Authorization";

  private final String tokenPrefix = "Bearer ";

  @Value("${token.secret}")
  private String secret;

  @Value("${token.expiration.time}")
  private Long expirationTime;
  
}
