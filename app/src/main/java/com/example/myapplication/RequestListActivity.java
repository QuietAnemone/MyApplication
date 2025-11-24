package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class RequestListActivity extends AppCompatActivity {

    private ListView listViewRequests;
    private RequestAdapter adapter;
    private List<Request> requestsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_list);

        listViewRequests = findViewById(R.id.listViewRequests);
        requestsList = new ArrayList<>();
        adapter = new RequestAdapter(this, requestsList);
        listViewRequests.setAdapter(adapter);

        Button buttonBackRequests = findViewById(R.id.buttonBackRequests);
        buttonBackRequests.setOnClickListener(view -> finish());

        // Получение данных из Intent — добавление новой заявки после формы
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras.getString("ownerName") != null) {
                String ownerName = extras.getString("ownerName");
                String phoneNumber = extras.getString("phoneNumber");
                String pcModel = extras.getString("pcModel");
                String serviceType = extras.getString("serviceType");
                String note = extras.getString("note");
                String date = extras.getString("date");
                String time = extras.getString("time");
                String status = extras.getString("status");

                Request request = new Request(ownerName, phoneNumber, pcModel, serviceType, note, date, time, status);
                requestsList.add(request);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
