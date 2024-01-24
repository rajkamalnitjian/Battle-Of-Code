package com.example.AuthenticationService.Service;

import java.util.ArrayList;
import java.util.*;
import com.example.AuthenticationService.Model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    List<User> store=new ArrayList<User>();
      UserService()
        {
            store.add(User.builder().userId(UUID.randomUUID().toString()).email("abc1@gmail.com").name("rajkamal").build());
            store.add(User.builder().userId(UUID.randomUUID().toString()).email("abc2@gmail.com").name("rajkamal").build());
        }
        public List<User> getUsers()
        {
            return store;
        }
}
