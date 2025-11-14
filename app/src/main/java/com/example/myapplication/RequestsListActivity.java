package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RequestsListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);


        ListView listViewRequests = findViewById(R.id.listViewRequests);
        Button buttonBack = findViewById(R.id.buttonBackRequests);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Request> requests = dbHelper.getAllRepairRequests();

        // Подключаем кастомный адаптер (где реализована галочка)
        RequestAdapter adapter = new RequestAdapter(this, requests, dbHelper);
        listViewRequests.setAdapter(adapter);

        // Кнопка "Назад"
        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }
    }
}
