package com.example.AuthenticationService.Model;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ErrorRes {
    HttpStatus httpStatus;
    String message;
}