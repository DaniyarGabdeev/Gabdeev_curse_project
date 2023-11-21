package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPressure, editTextSugar, editTextWeight, editTextHeight;
    private SharedPreferences sharedPreferences;
    private TextView textViewReports;

    private TextView textViewRecommendations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPressure = findViewById(R.id.editTextPressure);
        editTextSugar = findViewById(R.id.editTextSugar);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);

        // Инициализация SharedPreferences для сохранения данных
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        textViewReports = findViewById(R.id.textViewReports);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        // Получение данных из SharedPreferences
        String pressure = sharedPreferences.getString("Pressure", "");
        String sugar = sharedPreferences.getString("Sugar", "");
        String weight = sharedPreferences.getString("Weight", "");
        String height = sharedPreferences.getString("Height", "");

        // Формирование отчета на основе полученных данных
        String report = "Давление: " + pressure + "\n" +
                "Сахар в крови: " + sugar + "\n" +
                "Вес: " + weight + "\n" +
                "Рост: " + height;

        textViewReports.setText(report);
        textViewRecommendations = findViewById(R.id.textViewRecommendations);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        // Получение данных из SharedPreferences
        String sugar1 = sharedPreferences.getString("Sugar", "");

        // Здесь можно добавить логику для анализа данных и вывода рекомендаций
        if (!sugar.isEmpty()) {
            float sugarValue = Float.parseFloat(sugar);
            if (sugarValue > 100) {
                textViewRecommendations.setText("Слишком высокий уровень сахара в крови! Необходимо обратиться к врачу.");
            }
        }
    }
    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Pressure", editTextPressure.getText().toString());
        editor.putString("Sugar", editTextSugar.getText().toString());
        editor.putString("Weight", editTextWeight.getText().toString());
        editor.putString("Height", editTextHeight.getText().toString());
        editor.apply();

        Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
    }
}