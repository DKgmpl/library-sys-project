package pl.edu.wszib.services;

import pl.edu.wszib.model.User;
import pl.edu.wszib.repository.UserRepository;
import pl.edu.wszib.utils.SecurityUtils;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws SecurityException {
        String hashedPassword = SecurityUtils.hashPasswordWithSeed(password);
        return userRepository.findByUsernameAndPassword(username, hashedPassword)
                .orElseThrow(() -> new SecurityException("Nieprawidłowa nazwa użytkownika lub hasło"));
    }

}