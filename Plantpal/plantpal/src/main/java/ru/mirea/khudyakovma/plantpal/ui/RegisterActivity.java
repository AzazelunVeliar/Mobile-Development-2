package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.khudyakovma.domain.models.User;
import ru.mirea.khudyakovma.plantpal.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailField, passwordField, confirmField, nameField;
    private Button registerButton, toLoginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        confirmField = findViewById(R.id.editTextConfirmPassword);
        nameField = findViewById(R.id.editTextName);
        registerButton = findViewById(R.id.buttonRegister);
        toLoginButton = findViewById(R.id.buttonGoToLogin);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(v -> registerUser());
        toLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirm = confirmField.getText().toString().trim();
        String name = nameField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || confirm.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Ошибка регистрации: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
