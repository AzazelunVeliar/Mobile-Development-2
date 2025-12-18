package ru.mirea.khudyakovma.fragmentmanagerapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<>();

    public void select(String value) {
        selected.setValue(value);
    }

    public LiveData<String> getSelected() {
        return selected;
    }
}
