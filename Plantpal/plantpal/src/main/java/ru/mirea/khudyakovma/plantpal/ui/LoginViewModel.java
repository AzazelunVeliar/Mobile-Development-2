package ru.mirea.khudyakovma.plantpal.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.khudyakovma.data.repository.UserRepositoryImpl;
import ru.mirea.khudyakovma.domain.usecases.SignInUseCase;

public class LoginViewModel extends ViewModel {
    private final SignInUseCase signInUseCase;
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();

    public LoginViewModel(SignInUseCase signInUseCase) {
        this.signInUseCase = signInUseCase;
    }

    public MutableLiveData<Boolean> getLoginSuccess() { return loginSuccess; }
    public MutableLiveData<String> getLoginError() { return loginError; }

    public void login(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            loginError.setValue("Введите email и пароль");
            return;
        }
        boolean ok = signInUseCase.execute(email, password);
        if (ok) loginSuccess.setValue(true);
        else loginError.setValue("Ошибка входа. Проверьте данные.");
    }

    public static LoginViewModel create(android.content.Context context) {
        SignInUseCase uc = new SignInUseCase(new UserRepositoryImpl(context.getApplicationContext()));
        return new LoginViewModel(uc);
    }
}
