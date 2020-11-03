package com.jimds.buyers.service;

import java.util.List;

public interface IGeneralService<T>{
    List<T> findAll();

    T  findById(int id) throws Exception;

    T add(T object) throws Exception;

    T delete(int id) throws Exception;

    void updateUser(T object) throws Exception;

}
