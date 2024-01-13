package pl.edu.wszib.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String username;
    private String passwordHash;
    private String role;
}