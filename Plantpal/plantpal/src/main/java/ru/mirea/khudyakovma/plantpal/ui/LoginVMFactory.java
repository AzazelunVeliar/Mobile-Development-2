package ru.mirea.khudyakovma.plantpal.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginVMFactory implements ViewModelProvider.Factory {
    private final Context app;

    public LoginVMFactory(Context context) { this.app = context.getApplicationContext(); }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) LoginViewModel.create(app);
        }
        throw new IllegalArgumentException();
    }
}
