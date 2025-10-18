package ru.mirea.khudyakovma.data.firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.khudyakovma.domain.models.User;

public class FirebaseUserDataSource {
    private final FirebaseAuth auth;

    public FirebaseUserDataSource() {
        this.auth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> signUp(@NonNull User user) {
        return auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public Task<AuthResult> signIn(@NonNull String email, @NonNull String password) {
        return auth.signInWithEmailAndPassword(email, password);
    }
}
