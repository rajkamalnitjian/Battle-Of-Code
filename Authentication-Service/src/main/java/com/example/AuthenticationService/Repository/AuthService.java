package com.example.AuthenticationService.Repository;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
 public interface AuthService {
    public String login(String s, String password);
    public String signUp(String name, String s, String password);
}
