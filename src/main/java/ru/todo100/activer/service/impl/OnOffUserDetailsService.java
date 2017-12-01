package ru.todo100.activer.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Igor Bobko <limit-speed@yandex.ru>.
 */

public class OnOffUserDetailsService extends JdbcUserDetailsManager {
    public OnOffUserDetailsService() {
        super();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return super.loadUserByUsername(username);
    }



}
