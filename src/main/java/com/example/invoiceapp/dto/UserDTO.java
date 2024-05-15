package com.example.invoiceapp.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private LocalDate registrationDate;

   
}
