package br.com.esig_group.enfase_dados.backend.controller;

import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import br.com.esig_group.enfase_dados.backend.model.UserModel;
import br.com.esig_group.enfase_dados.backend.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(value = "API Rest Users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

   private final UserRepository repository;
   private final PasswordEncoder encoder;

   @GetMapping("/listAll")
   @ApiOperation(value = "Lista todos os usuarios cadastrados no DB")
   public ResponseEntity<List<UserModel>> listAll() {
      return ResponseEntity.ok(repository.findAll());
   }

   @PostMapping("/saveUser")
   @ApiOperation(value = "Insere um novo usuario no DB")
   public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
      user.setPassword(encoder.encode(user.getPassword()));
      return ResponseEntity.ok(repository.save(user));
   }

   @GetMapping("/authPassword")
   @ApiOperation(value = "Autentica as credenciais de acesso ao sistema de usuario cadastrado no DB")
   public ResponseEntity<Boolean> authPassword(
         @RequestParam String email,
         @RequestParam String password) {
      Optional<UserModel> optUser = repository.findByEmail(email);

      if (optUser.isEmpty()) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
      }

      UserModel user = optUser.get();
      boolean valid = encoder.matches(password, user.getPassword());

      HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
      return ResponseEntity.status(status).body(valid);
   }

}
