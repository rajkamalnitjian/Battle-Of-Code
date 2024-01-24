package com.example.AuthenticationService.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private  String  userId;
    private  String  email;
    private  String  name;
}
