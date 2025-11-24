package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RequestsListActivity extends AppCompatActivity {
    private ListView listViewRequests;
    private RequestAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);

        listViewRequests = findViewById(R.id.listViewRequests);
        Button buttonBack = findViewById(R.id.buttonBackRequests);

        dbHelper = new DatabaseHelper(this);
        List<Request> requests = dbHelper.getAllRepairRequests();

        adapter = new RequestAdapter(this, requests, dbHelper);
        listViewRequests.setAdapter(adapter);

        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Request> requests = dbHelper.getAllRepairRequests();
        adapter.updateRequests(requests);
        adapter.notifyDataSetChanged();
    }
}
