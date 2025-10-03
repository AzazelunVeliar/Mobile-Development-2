package ru.mirea.khudyakovma.plantpal.domain.repository;

import ru.mirea.khudyakovma.plantpal.domain.models.User;

public interface UserRepository {
    boolean signUp(User user);
    boolean signIn(String email, String password);
}
