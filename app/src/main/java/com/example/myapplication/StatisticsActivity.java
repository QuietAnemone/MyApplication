package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Получение статистики
        int totalRequests = dbHelper.getTotalRepairRequests();
        int inProgressCount = dbHelper.countRepairRequestsByStatus("В работе");
        int completedCount = dbHelper.countRepairRequestsByStatus("Готово");

        // Вывод статистики на экран
        TextView statTotal = findViewById(R.id.statTotal);
        TextView statInProgress = findViewById(R.id.statInProgress);
        TextView statCompleted = findViewById(R.id.statCompleted);

        statTotal.setText("Всего заявок: " + totalRequests);
        statInProgress.setText("В работе: " + inProgressCount);
        statCompleted.setText("Выполнено: " + completedCount);

        // Кнопка "Просмотр заявок"
        Button buttonViewRequests = findViewById(R.id.buttonViewRequests);
        buttonViewRequests.setOnClickListener(v -> {
            Intent intent = new Intent(StatisticsActivity.this, RequestsListActivity.class);
            startActivity(intent);
        });

        // Кнопка "Назад"
        Button buttonBack = findViewById(R.id.buttonBack);
        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }
<<<<<<< HEAD

        // Кнопка "Сброс статистики"
        Button buttonDeleteRequests = findViewById(R.id.buttonDeleteRequests);
        buttonDeleteRequests.setOnClickListener(v -> {
            dbHelper.clearAllRepairRequests();

            statTotal.setText("Всего заявок: 0");
            statInProgress.setText("В работе: 0");
            statCompleted.setText("Выполнено: 0");

            Toast.makeText(this, "Статистика и заявки сброшены!", Toast.LENGTH_SHORT).show();
        });
=======
>>>>>>> daec64f8c7e765329b522865633226af1c04c126
    }
}
