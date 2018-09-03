package com.example.securitytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return user;
    }

    @Transactional
    public void registerAdmin(String username, String password, String mailAddress) {
        User user = new User(username, passwordEncoder.encode(password), mailAddress);
        user.setAdmin(true);
        user.setMailAddressVerified(true);
        user.setEnabled(true);
        //user.Authorities = EnumSet.of(User.Authority.ROLE_ADMIN);
        userMapper.save(user);
    }

    @Transactional
    public void registerUser(String username, String password, String mailAddress) {
        User user = new User(username, passwordEncoder.encode(password), mailAddress);
        user.setAdmin(false);
        user.setMailAddressVerified(true);
        user.setEnabled(true);
        //user.Authorities = EnumSet.of(User.Authority.ROLE_USER);
        userMapper.save(user);
    }

}
