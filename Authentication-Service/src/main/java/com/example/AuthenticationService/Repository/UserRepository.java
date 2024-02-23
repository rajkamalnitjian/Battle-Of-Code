package com.example.AuthenticationService.Repository;
import com.example.AuthenticationService.Model.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<User, String>    {
    public User findUserByEmail(String email);
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword("123456");
//        user.setFirstName("FirstName");
//        user.setLastName("LastName");
//        return user;
}

