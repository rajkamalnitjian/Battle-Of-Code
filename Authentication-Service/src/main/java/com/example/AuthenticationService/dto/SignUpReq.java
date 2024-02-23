package com.example.AuthenticationService.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SignUpReq {
    private String id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
