package com.example.AuthenticationService.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class LoginRes {
    private String email;
    private String token;
}