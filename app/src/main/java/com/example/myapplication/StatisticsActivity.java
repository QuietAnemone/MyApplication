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
        int totalRequests = dbHelper.getTotalRepairRequests();

        TextView statValue = findViewById(R.id.statValue);
        statValue.setText("Всего заявок: " + totalRequests);

        Button buttonViewRequests = findViewById(R.id.buttonViewRequests);
        buttonViewRequests.setOnClickListener(v -> {
            Intent intent = new Intent(StatisticsActivity.this, RequestsListActivity.class);
            startActivity(intent);
        });

        Button buttonBack = findViewById(R.id.buttonBack);
        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }
    }
}
