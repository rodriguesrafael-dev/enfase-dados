package br.com.esig_group.enfase_dados.backend.controller;

/*
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
*/
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
*/
import org.springframework.web.bind.annotation.*;

import br.com.esig_group.enfase_dados.backend.model.UserModel;
import br.com.esig_group.enfase_dados.backend.repository.UserRepository;

/*
import java.util.List;
import java.util.Optional;
*/
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

   private final UserRepository repository;
   private final PasswordEncoder encoder;

   public UserController(UserRepository repository, PasswordEncoder encoder) {
      this.repository = repository;
      this.encoder = encoder;
   }

   @GetMapping("/listAll")
   public ResponseEntity<List<UserModel>> listAll() {
      return ResponseEntity.ok(repository.findAll());
   }

   @PostMapping("/saveUser")
   public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
      user.setPassword(encoder.encode(user.getPassword()));
      return ResponseEntity.ok(repository.save(user));
   }

   @GetMapping("/authPassword")
   public ResponseEntity<Boolean> authPassword(
         @RequestParam String login,
         @RequestParam String password) {
      Optional<UserModel> optUser = repository.findByLogin(login);

      if (optUser.isEmpty()) {
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
      }

      UserModel user = optUser.get();
      boolean valid = encoder.matches(password, user.getPassword());

      HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
      return ResponseEntity.status(status).body(valid);
   }

}
