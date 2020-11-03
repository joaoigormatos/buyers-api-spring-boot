package com.jimds.buyers.controller;

import com.jimds.buyers.dto.UserAuthenticationDTO;
import com.jimds.buyers.exceptions.StandardError;
import com.jimds.buyers.model.AplicationUser;
import com.jimds.buyers.repository.UserRepository;
import com.jimds.buyers.service.UserService;
import com.jimds.buyers.util.JWTHandler;
import com.jimds.buyers.util.UserAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthenticationController {
    @Autowired
    private JWTHandler jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> signin(@RequestBody UserAuthenticationRequest data) {

        try {
            String username = data.getUsername();
            Optional<AplicationUser> user = userRepository.findByEmail(username);

            List<String> roleList = roles(user.get());

            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username,data.getPassword()));

            String token = jwtTokenProvider.createToken(username,roleList);
            Map<Object, Object> model = new HashMap<>();
            model.put("username", user.get().getName());
            model.put("token", "Bearer " + token);
            return ok(model);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
        catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new StandardError(HttpStatus.BAD_REQUEST.value(),"Body is missing an element","Please send a valid element","/login")
            );
        }
    }
    @PostMapping("/singup")
    public ResponseEntity<?> create(@RequestBody UserAuthenticationDTO userRegisterDTO) {
        // check if the user exists
        // as long as the users doesnot exist so then add the users
        try {
            AplicationUser aplicationUser = userRegisterDTO.toUser();
            return ok(userService.addUser(aplicationUser));
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).
                    body(new StandardError(HttpStatus.IM_USED.value(), "already in use", e.getMessage(), "/usuarios"));
        }
    }

    private List<String> roles(UserDetails userDetails){
        List<String> auxRoles = new ArrayList<>();
        for(GrantedAuthority grantedAuthority:  userDetails.getAuthorities()) {
            auxRoles.add(grantedAuthority.getAuthority());
        }
        return auxRoles;
    }
}
