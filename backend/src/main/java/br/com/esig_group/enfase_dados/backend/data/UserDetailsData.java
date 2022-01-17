package br.com.esig_group.enfase_dados.backend.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.esig_group.enfase_dados.backend.model.UserModel;

import java.util.*;

public class UserDetailsData implements UserDetails {

   private Optional<UserModel> user;

   public UserDetailsData(UserModel user) {
       this.setUser(user);
   }

   public void setUser(UserModel userModel) {
       this.user = Optional.of(userModel);
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return new ArrayList<>();
   }

   @Override
   public String getPassword() {
       return user.orElse(new UserModel()).getPassword();
   }

   @Override
   public String getUsername() {
       return user.orElse(new UserModel()).getEmail();
   }

   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @Override
   public boolean isAccountNonLocked() {
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       return true;
   }

   @Override
   public boolean isEnabled() {
       return true;
   }

}
