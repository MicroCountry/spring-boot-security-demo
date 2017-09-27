package com.demo.security.service;

import com.demo.model.PubAuthorities;
import com.demo.model.PubUsers;
import com.demo.persistence.PubAuthoritiesMapper;
import com.demo.persistence.PubUsersMapper;
import com.demo.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PubUsersMapper pubUsersMapper;
    @Autowired
    private PubAuthoritiesMapper pubAuthoritiesMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PubUsers user = pubUsersMapper.selectByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<PubAuthorities> list = pubAuthoritiesMapper.selectAuthoritiesByUserName(username);
            user.setAuthorities(list);
            return JwtUserFactory.create(user);
        }
    }
}
