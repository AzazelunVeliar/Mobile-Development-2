package ru.mirea.khudyakovma.plantpal.domain.usecases;

import ru.mirea.khudyakovma.plantpal.domain.models.User;
import ru.mirea.khudyakovma.plantpal.domain.repository.UserRepository;

public class SignUpUseCase {
    private UserRepository userRepository;

    public SignUpUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(User user) {
        return userRepository.signUp(user);
    }
}
