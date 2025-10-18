package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.khudyakovma.data.repository.UserRepositoryImpl;
import ru.mirea.khudyakovma.domain.usecases.SignInUseCase;
import ru.mirea.khudyakovma.plantpal.MainActivity;
import ru.mirea.khudyakovma.plantpal.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton, toRegisterButton;
    private SignInUseCase signInUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        toRegisterButton = findViewById(R.id.buttonGoToRegister);

        signInUseCase = new SignInUseCase(new UserRepositoryImpl(this));

        loginButton.setOnClickListener(v -> loginUser());

        toRegisterButton.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    private void loginUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите email и пароль", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = signInUseCase.execute(email, password);

        if (success) {
            Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Ошибка входа. Проверьте данные.", Toast.LENGTH_LONG).show();
        }
    }
}