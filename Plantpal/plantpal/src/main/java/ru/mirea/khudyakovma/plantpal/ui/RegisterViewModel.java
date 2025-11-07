package ru.mirea.khudyakovma.plantpal.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterViewModel extends ViewModel {

    private final FirebaseAuth auth;
    private final MutableLiveData<Boolean> registerSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> registerError = new MutableLiveData<>();

    public RegisterViewModel(FirebaseAuth auth) {
        this.auth = auth;
    }

    public MutableLiveData<Boolean> getRegisterSuccess() { return registerSuccess; }
    public MutableLiveData<String> getRegisterError() { return registerError; }

    public void register(String email, String password, String confirm, String name) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()
                || confirm == null || confirm.isEmpty() || name == null || name.isEmpty()) {
            registerError.setValue("Заполните все поля");
            return;
        }
        if (!password.equals(confirm)) {
            registerError.setValue("Пароли не совпадают");
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(r -> registerSuccess.setValue(true))
                .addOnFailureListener(e -> registerError.setValue("Ошибка регистрации: " + e.getMessage()));
    }

    public static RegisterViewModel create() {
        return new RegisterViewModel(FirebaseAuth.getInstance());
    }
}
