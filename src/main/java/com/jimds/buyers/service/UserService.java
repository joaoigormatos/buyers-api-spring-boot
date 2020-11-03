package com.jimds.buyers.service;

import com.jimds.buyers.exceptions.InvalidEmailException;
import com.jimds.buyers.exceptions.UserNotFoundException;
import com.jimds.buyers.model.AplicationUser;
import com.jimds.buyers.repository.UserRepository;
import com.jimds.buyers.util.RegexHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private RegexHandler regexHandler;


    @Autowired
    UserRepository userRepository;
    @Override
    public AplicationUser findByEmail(String email) throws Exception{

        if(regexHandler.isEmailValid(email)) {
            Optional<AplicationUser> user = userRepository.findByEmail(email);
            if(user.isPresent())
                return user.get();
            throw new UserNotFoundException(email);
        }
        throw new InvalidEmailException();
    }

    @Override
    public List<AplicationUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AplicationUser findById(int id) {
        //TODO: Must add validation to see if the user has a valid ID
        Optional<AplicationUser> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        throw new UserNotFoundException(id);
    }

    @Override
    public AplicationUser addUser(AplicationUser usuario) throws Exception {

        //TODO: Should check if the email already exists and if the password is valid
        //TODO: Should valid if the fileds have not sqlinject
        if(regexHandler.isEmailValid(usuario.getEmail()) && regexHandler.isPasswordValid(usuario.getPassword())){
            return userRepository.save(usuario);
        }
        throw new Exception("Email or Password is invalid");
    }

    @Override
    public void deleteUser(int id) throws Exception {
       //TODO: Check if the id is a valid id
       // TODO: Check the role of the user
        Optional<AplicationUser> searchedUser =  userRepository.findById(id);
        if(searchedUser.isPresent()){
            userRepository.delete(searchedUser.get());
            return;
        }
        throw new UserNotFoundException(id);
    }

    @Override
    public void updateUser(AplicationUser usuario) throws Exception {
        Optional<AplicationUser> searchedUser =  userRepository.findByEmail(usuario.getEmail());
        if(searchedUser.isPresent()){
            userRepository.delete(searchedUser.get());
            userRepository.save(usuario);
            return;
        }
        throw new UserNotFoundException(usuario.getEmail());
    }
}
