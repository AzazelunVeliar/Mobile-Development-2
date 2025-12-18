package ru.mirea.khudyakovma.resultapifragmentapp;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DataFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText et = view.findViewById(R.id.editTextInfo);
        Button btn = view.findViewById(R.id.buttonOpenBottomSheet);

        btn.setOnClickListener(v -> {
            String text = et.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("key", text);

            getParentFragmentManager().setFragmentResult("requestKey", bundle);

            BottomSheetFragment bottomSheet = new BottomSheetFragment();
            bottomSheet.show(getParentFragmentManager(), "ModalBottomSheet");
        });
    }
}
