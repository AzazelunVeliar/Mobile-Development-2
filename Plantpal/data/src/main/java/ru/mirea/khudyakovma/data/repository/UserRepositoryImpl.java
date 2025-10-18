package ru.mirea.khudyakovma.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.mirea.khudyakovma.domain.models.User;
import ru.mirea.khudyakovma.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "user_preferences";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";
    private static final String KEY_USER_SETTINGS = "user_settings";

    public UserRepositoryImpl(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void signUpAsync(@NonNull User user, @NonNull AuthCallback callback) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserToPreferences(user);
                        callback.onResult(true, "Регистрация успешна");
                    } else {
                        String message = task.getException() != null
                                ? task.getException().getMessage()
                                : "Неизвестная ошибка";
                        callback.onResult(false, message);
                    }
                });
    }

    @Override
    public void signInAsync(@NonNull String email, @NonNull String password, @NonNull AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveLoginInfo(email);
                        callback.onResult(true, "Вход выполнен успешно");
                    } else {
                        String message = task.getException() != null
                                ? task.getException().getMessage()
                                : "Неизвестная ошибка";
                        callback.onResult(false, message);
                    }
                });
    }

    @Override
    public boolean signUp(User user) {
        if (isValidUser(user)) {
            saveUserToPreferences(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean signIn(String email, String password) {
        if (isValidCredentials(email, password)) {
            saveLoginInfo(email);
            return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null && firebaseUser.getEmail().equals(email)) {
            return new User(
                    firebaseUser.getEmail(),
                    "",
                    firebaseUser.getDisplayName() != null ? firebaseUser.getDisplayName() : "User"
            );
        }

        String savedEmail = sharedPreferences.getString(KEY_USER_EMAIL, "");
        if (savedEmail.equals(email)) {
            return new User(
                    savedEmail,
                    "",
                    sharedPreferences.getString(KEY_USER_NAME, "User")
            );
        }

        return null;
    }


    private void saveUserToPreferences(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, user.getEmail());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putBoolean(KEY_USER_LOGGED_IN, true);
        editor.apply();
    }

    private void saveLoginInfo(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.putBoolean(KEY_USER_LOGGED_IN, true);
        editor.apply();
    }

    private boolean isValidUser(User user) {
        return user.getEmail() != null && !user.getEmail().isEmpty() &&
                user.getPassword() != null && !user.getPassword().isEmpty() &&
                user.getName() != null && !user.getName().isEmpty();
    }

    private boolean isValidCredentials(String email, String password) {
        return email != null && !email.isEmpty() &&
                password != null && !password.isEmpty();
    }


    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(KEY_USER_LOGGED_IN, false);
    }

    public void saveUserSetting(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_SETTINGS + "_" + key, value);
        editor.apply();
    }

    public String getUserSetting(String key, String defaultValue) {
        return sharedPreferences.getString(KEY_USER_SETTINGS + "_" + key, defaultValue);
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER_EMAIL);
        editor.remove(KEY_USER_NAME);
        editor.putBoolean(KEY_USER_LOGGED_IN, false);
        editor.apply();
        auth.signOut();
    }
}