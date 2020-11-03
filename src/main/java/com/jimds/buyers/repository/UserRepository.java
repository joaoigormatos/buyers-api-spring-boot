package com.jimds.buyers.repository;

import com.jimds.buyers.model.AplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AplicationUser,Integer> {
    Optional<AplicationUser> findByEmail(String email);

    Optional<AplicationUser> findById(int id);

    @Query("SELECT new AplicationUser (u.email,u.name) FROM AplicationUser u")
    List<AplicationUser> findAll();


}
