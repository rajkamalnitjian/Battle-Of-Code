package com.example.AuthenticationService.Controller;

import com.example.AuthenticationService.Model.ErrorRes;
import com.example.AuthenticationService.Model.User;
import com.example.AuthenticationService.Repository.UserRepository;
import com.example.AuthenticationService.dto.LoginReq;
import com.example.AuthenticationService.dto.LoginRes;
import com.example.AuthenticationService.dto.SignUpReq;
import com.example.AuthenticationService.dto.SignUpRes;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  com.example.AuthenticationService.Util.JwtUtil;
import java.util.UUID;
import com.example.AuthenticationService.Config.SecurityConfig;
@Slf4j
@Controller
@RequestMapping("/rest/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder noOpPasswordEncoder;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, SecurityConfig securityConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = new User();
            user.setEmail(email);
            user.setPassword(loginReq.getPassword());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            log.error("Invalid username or password  : {} ", loginReq, e);
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            log.error("Unexpected error during login", e);
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public ResponseEntity signup(@RequestBody SignUpReq signUpReq)  {

        try {
             var response=userRepository.findUserByEmail(signUpReq.getEmail());

              if(null!=response){
                    ErrorRes errorResponse = new ErrorRes(HttpStatus.ALREADY_REPORTED,"User Already Exist");
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(errorResponse);
              }
              else {
                  User user= new User();
//                  user.setId(UUID.randomUUID().toString());
                  user.setEmail(signUpReq.getEmail());
                  CharSequence charSequence =new StringBuilder(signUpReq.getPassword());
                  user.setPassword(noOpPasswordEncoder.encode(charSequence));
                  user.setFirstname(signUpReq.getFirstname());
                  user.setLastname(signUpReq.getLastname());
                  userRepository.save(user);
                  String token = jwtUtil.createToken(user);
                  SignUpRes signUpRes = new SignUpRes(user.getEmail(),token);
                  return ResponseEntity.ok(signUpRes);
              }
        }catch (BadCredentialsException e){
            log.error("Bad credentials for email: {} ", signUpReq.getEmail(), e);
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            log.error("Unexpected error during signup", e);
            ErrorRes errorResponse = new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public ResponseEntity check() {

        return ResponseEntity.ok("hello from check box");
    }
    }
