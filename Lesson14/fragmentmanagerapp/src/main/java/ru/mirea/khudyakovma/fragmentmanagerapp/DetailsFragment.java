package ru.mirea.khudyakovma.fragmentmanagerapp;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;
import java.util.Map;

public class DetailsFragment extends Fragment {

    private final Map<String, CountryInfo> db = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db.put("Россия", new CountryInfo(
                "Россия",
                "Москва",
                "~146 млн",
                "Российский рубль (RUB)",
                "Русский (гос.)",
                "~17,1 млн км²",
                "UTC+2…UTC+12 (много зон)",
                "Самая большая страна мира по площади."
        ));

        db.put("Германия", new CountryInfo(
                "Германия",
                "Берлин",
                "~84 млн",
                "Евро (EUR)",
                "Немецкий",
                "~357 тыс. км²",
                "UTC+1 (CET), летом UTC+2",
                "Одна из крупнейших экономик Европы, известна автобанами."
        ));

        db.put("Япония", new CountryInfo(
                "Япония",
                "Токио",
                "~124 млн",
                "Иена (JPY)",
                "Японский",
                "~378 тыс. км²",
                "UTC+9",
                "Архипелаг из тысяч островов; высокая сейсмическая активность."
        ));

        db.put("Бразилия", new CountryInfo(
                "Бразилия",
                "Бразилиа",
                "~203 млн",
                "Бразильский реал (BRL)",
                "Португальский",
                "~8,5 млн км²",
                "UTC−2…UTC−5",
                "В Амазонии — крупнейший тропический лес планеты."
        ));

        db.put("Канада", new CountryInfo(
                "Канада",
                "Оттава",
                "~40 млн",
                "Канадский доллар (CAD)",
                "Английский и французский",
                "~9,98 млн км²",
                "UTC−3:30…UTC−8",
                "Имеет одну из самых длинных береговых линий в мире."
        ));

        db.put("Уганда", new CountryInfo(
                "Уганда",
                "Кампала",
                "~48–50 млн",
                "Угандийский шиллинг (UGX)",
                "Английский и суахили (офиц.)",
                "~241 тыс. км²",
                "UTC+3",
                "В стране находится исток Белого Нила (район озера Виктория)."
        ));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = view.findViewById(R.id.tvDetails);
        tv.setText("Выбери страну слева");

        SharedViewModel vm = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        vm.getSelected().observe(getViewLifecycleOwner(), countryName -> {
            CountryInfo info = db.get(countryName);

            if (info == null) {
                tv.setText("Нет данных по стране: " + countryName);
                return;
            }

            String text =
                    "Страна: " + info.name + "\n" +
                            "Столица: " + info.capital + "\n" +
                            "Население: " + info.population + "\n" +
                            "Валюта: " + info.currency + "\n" +
                            "Язык: " + info.language + "\n" +
                            "Площадь: " + info.area + "\n" +
                            "Часовой пояс: " + info.timeZone + "\n\n" +
                            "Факт: " + info.fact;

            tv.setText(text);
        });
    }
}
