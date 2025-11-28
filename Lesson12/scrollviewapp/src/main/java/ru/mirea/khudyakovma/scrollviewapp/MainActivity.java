package ru.mirea.khudyakovma.scrollviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout wrapper = findViewById(R.id.wrapper);
        LayoutInflater inflater = LayoutInflater.from(this);

        BigInteger current = BigInteger.ONE;

        for (int i = 1; i <= 100; i++) {
            View view = inflater.inflate(R.layout.item_progression, wrapper, false);
            TextView textView = view.findViewById(R.id.textView);
            textView.setText("a" + i + " = " + current.toString());
            wrapper.addView(view);
            current = current.multiply(BigInteger.valueOf(2));
        }
    }
}
