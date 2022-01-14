package br.com.esig_group.enfase_dados.backend.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity(name="sig_users")
public class UserModel implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @NonNull
   @Column(name = "id")
   private Long id;

   @NonNull
   @Column(name = "login", unique = true)
   private String login;

   @NonNull
   @Column(name = "password")
   private String password;

   @NonNull
   @Column(name = "user_name")
   private String name;

   @NonNull
   @Column(name = "user_email")
   private String email;
   
}
