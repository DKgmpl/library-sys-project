package pl.edu.wszib.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.wszib.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@NoArgsConstructor
public class UserRepository {
    private final List<User> users = new ArrayList<>();
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}
