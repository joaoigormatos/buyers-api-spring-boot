package com.jimds.buyers.service;

import com.jimds.buyers.model.AplicationUser;

import java.util.List;

public interface IUserService {
    AplicationUser findByEmail(String email) throws Exception;

    List<AplicationUser> findAll();

    AplicationUser findById(int id) throws Exception;

    AplicationUser addUser(AplicationUser usuario) throws Exception;

    public void deleteUser(int id) throws Exception;

    void updateUser(AplicationUser usuario) throws Exception;
}
