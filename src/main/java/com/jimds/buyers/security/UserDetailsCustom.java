package com.jimds.buyers.security;

import com.jimds.buyers.model.AplicationUser;
import com.jimds.buyers.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustom implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AplicationUser aplicationUser = null;
        try {
            aplicationUser = userService.findByEmail(username);
            if(aplicationUser == null){
                throw new UsernameNotFoundException("Either username or password invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aplicationUser;
    }
}
