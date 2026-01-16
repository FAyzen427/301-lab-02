package com.example.a301_lab_02;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1; // Track which city is tapped

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addBtn = findViewById(R.id.add_city_button);
        Button deleteBtn = findViewById(R.id.delete_city_button);
        Button confirmBtn = findViewById(R.id.confirm_button);
        EditText cityInput = findViewById(R.id.city_input);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Show input field when ADD CITY is pressed
        addBtn.setOnClickListener(v -> {
            cityInput.setVisibility(View.VISIBLE);
            confirmBtn.setVisibility(View.VISIBLE);
        });

        // Add city to list when CONFIRM is pressed
        confirmBtn.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged(); // Refresh the list
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmBtn.setVisibility(View.GONE);
            }
        });

        // Select a city when it is tapped
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (selectedPosition == position) {
                cityList.setItemChecked(position, false);
                selectedPosition = -1;
            } else {
                cityList.setItemChecked(position, true);
                selectedPosition = position;
            }
        });

        // Delete the selected city
        deleteBtn.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityList.clearChoices();
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection
            }
        });
    }
}