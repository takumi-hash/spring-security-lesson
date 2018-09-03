package com.example.securitytest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public enum Authority {ROLE_USER, ROLE_ADMIN}

    private Long id;

    private String username;

    private String password;

    private String mailAddress;

    private boolean mailAddressVerified;

    private boolean enabled;

    private Date createdAt;

    private Set<Authority> authorities;

/*
    // JPA requirement
    protected User() {}

    public User(String username, String password, String mailAddress) {
        this.username = username;
        this.password = password;
        this.mailAddress = mailAddress;
        this.mailAddressVerified = false;
        this.enabled = true;
        this.authorities = EnumSet.of(Authority.ROLE_USER);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
*/
    public boolean isAdmin() {
        return this.authorities.contains(Authority.ROLE_ADMIN);
    }

    public void setIsAdmin(boolean isAdmin) {
        if (isAdmin) {
            this.authorities.add(Authority.ROLE_ADMIN);
        } else {
            this.authorities.remove(Authority.ROLE_ADMIN);
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : this.authorities) {
            authorities.add(new SimpleGrantedAuthority(authority.toString()));
        }
        return authorities;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public boolean isMailAddressVerified() {
        return mailAddressVerified;
    }

    public void setMailAddressVerified(boolean mailAddressVerified) {
        this.mailAddressVerified = mailAddressVerified;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
