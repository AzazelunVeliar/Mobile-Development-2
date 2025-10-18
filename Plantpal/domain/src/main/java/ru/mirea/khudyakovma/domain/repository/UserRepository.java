package ru.mirea.khudyakovma.domain.repository;

import ru.mirea.khudyakovma.domain.models.User;

public interface UserRepository {
    boolean signUp(User user);
    boolean signIn(String email, String password);
    User getUserByEmail(String email);

    interface AuthCallback {
        void onResult(boolean success, String message);
    }

    void signUpAsync(User user, AuthCallback callback);
    void signInAsync(String email, String password, AuthCallback callback);
}