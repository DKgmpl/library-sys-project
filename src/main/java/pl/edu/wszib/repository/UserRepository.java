package pl.edu.wszib.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.wszib.model.User;
import pl.edu.wszib.utils.SecurityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@NoArgsConstructor
public class UserRepository {
    private final List<User> users = new ArrayList<>();
    public void initializeData() {
        add(new User("admin", SecurityUtils.hashPasswordWithSeed("haslo123"), "ADMIN"));
        add(new User("user", SecurityUtils.hashPasswordWithSeed("haslo"), "USER"));
    }
    public Optional<User> findByUsernameAndPassword(String username, String hashedPassword) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPasswordHash().equals(hashedPassword))
                .findFirst();
    }

    public void add(User user) {
        users.add(user);
    }

}
