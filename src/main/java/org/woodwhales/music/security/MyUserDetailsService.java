package org.woodwhales.music.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.woodwhales.music.config.SystemConfig;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 验证用户身份及角色
 * @author woodwhales
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SystemConfig systemConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        if(StringUtils.equals(username, systemConfig.getUsername())) {
            String password = systemConfig.getPassword();
            return new User(username, password, authorities);
        }
        return null;
    }
	
}