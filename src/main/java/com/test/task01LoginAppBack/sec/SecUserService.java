package com.test.task01LoginAppBack.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecUserService implements UserDetailsService {
    @Autowired
    private SecUserRepository secUserRepository;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecUser> user = secUserRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }

    public void addUser(SecUser user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        secUserRepository.save(user);
    }

    public List<SecUser> getAllUsers() {
        return secUserRepository.findAll();
    }

    public Optional<SecUser> findByUsername(String userName) {
        return secUserRepository.findByUsername(userName);
    }

}
