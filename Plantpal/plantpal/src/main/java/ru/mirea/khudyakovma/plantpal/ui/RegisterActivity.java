package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.khudyakovma.plantpal.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailField, passwordField, confirmField, nameField;
    private Button registerButton, toLoginButton;
    private RegisterViewModel vm;

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

        vm = new ViewModelProvider(this, new RegisterVMFactory()).get(RegisterViewModel.class);

        vm.getRegisterSuccess().observe(this, ok -> {
            if (ok == null) return;
            Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        vm.getRegisterError().observe(this, msg -> {
            if (msg == null) return;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        });

        registerButton.setOnClickListener(v ->
                vm.register(
                        emailField.getText().toString().trim(),
                        passwordField.getText().toString().trim(),
                        confirmField.getText().toString().trim(),
                        nameField.getText().toString().trim()));

        toLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
