package com.jimds.buyers.controller;

import com.jimds.buyers.exceptions.StandardError;
import com.jimds.buyers.model.AplicationUser;
import com.jimds.buyers.repository.UserRepository;
import com.jimds.buyers.service.UserService;
import com.jimds.buyers.util.JWTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
//TODO: Allow just the users with role admin
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTHandler jwtHandler;


    //TODO: Create UserLogin DTO



    @GetMapping("")
    public ResponseEntity getAllUsers(){
        return ok(userRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUser(@PathVariable Integer id){
        System.out.println(id);
        try{
            AplicationUser aplicationUser = userService.findById(id);
           return ok().body(aplicationUser);
        }
        catch(Exception e){
            // TODO: Create an standtard error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new StandardError(HttpStatus.NOT_FOUND.value(),"Not Found", "The provided ID is not valid","/user/"+id)
            );
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@RequestParam Integer id){
        // TODO: Create a standard message of success
        try{
            userService.deleteUser(id);
            return ok("User with id "+id+"");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody AplicationUser aplicationUser){
        try{
             userService.updateUser(aplicationUser);
            return ok(aplicationUser.getName() + " Has been updated sucessfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
