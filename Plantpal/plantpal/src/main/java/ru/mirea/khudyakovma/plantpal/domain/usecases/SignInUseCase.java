package ru.mirea.khudyakovma.plantpal.domain.usecases;

import ru.mirea.khudyakovma.plantpal.domain.repository.UserRepository;

public class SignInUseCase {
    private UserRepository userRepository;

    public SignInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String email, String password) {
        return userRepository.signIn(email, password);
    }
}
