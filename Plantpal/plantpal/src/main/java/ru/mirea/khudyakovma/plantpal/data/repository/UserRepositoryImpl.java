package ru.mirea.khudyakovma.plantpal.data.repository;

import java.util.HashMap;
import java.util.Map;
import ru.mirea.khudyakovma.plantpal.domain.models.User;
import ru.mirea.khudyakovma.plantpal.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public boolean signUp(User user) {
        if (users.containsKey(user.getEmail())) return false;
        users.put(user.getEmail(), user);
        return true;
    }

    @Override
    public boolean signIn(String email, String password) {
        return users.containsKey(email) && users.get(email).getPassword().equals(password);
    }
}
