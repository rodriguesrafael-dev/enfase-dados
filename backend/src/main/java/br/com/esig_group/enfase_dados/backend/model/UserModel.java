package br.com.esig_group.enfase_dados.backend.model;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="sig_users")
public class UserModel implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id")
   private Long id;

   @Column(name = "name", length = 100)
   private String name;

   @Column(name = "email", length = 100, unique = true)
   private String email;

   @Column(name = "password")
   private String password;
   
}
