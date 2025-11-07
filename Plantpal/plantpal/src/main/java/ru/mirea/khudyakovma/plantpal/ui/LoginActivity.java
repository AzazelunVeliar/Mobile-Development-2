package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.khudyakovma.plantpal.MainActivity;
import ru.mirea.khudyakovma.plantpal.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton, toRegisterButton;
    private LoginViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.editTextEmail);
        passwordField = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        toRegisterButton = findViewById(R.id.buttonGoToRegister);

        vm = new ViewModelProvider(this, new LoginVMFactory(this)).get(LoginViewModel.class);

        vm.getLoginSuccess().observe(this, ok -> {
            if (ok == null) return;
            Toast.makeText(this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        vm.getLoginError().observe(this, msg -> {
            if (msg == null) return;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        });

        loginButton.setOnClickListener(v ->
                vm.login(emailField.getText().toString().trim(), passwordField.getText().toString().trim()));

        toRegisterButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
}
