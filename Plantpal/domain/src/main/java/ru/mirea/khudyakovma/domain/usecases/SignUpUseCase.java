package ru.mirea.khudyakovma.domain.usecases;

import ru.mirea.khudyakovma.domain.models.User;
import ru.mirea.khudyakovma.domain.repository.UserRepository;

public class SignUpUseCase {
    private final UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(User user) {
        return userRepository.signUp(user);
    }
}
