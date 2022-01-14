package br.com.esig_group.enfase_dados.backend.service;

import br.com.esig_group.enfase_dados.backend.data.UserDetailsData;
import br.com.esig_group.enfase_dados.backend.model.UserModel;
import br.com.esig_group.enfase_dados.backend.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repository.findByLogin(username).orElse(null);
        return new UserDetailsData(user);
    }
    
}
