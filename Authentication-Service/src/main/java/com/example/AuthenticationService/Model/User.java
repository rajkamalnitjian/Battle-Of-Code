package com.example.AuthenticationService.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Builder
@Table(name = "_user")
public class User{
    @Id
    private String email;
    private String password;
    private String firstname;
    private String lastname;

}