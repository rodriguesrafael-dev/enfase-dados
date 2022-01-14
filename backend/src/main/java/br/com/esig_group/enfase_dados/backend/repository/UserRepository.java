package br.com.esig_group.enfase_dados.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.esig_group.enfase_dados.backend.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
   public Optional<UserModel> findByLogin(String login);
}
