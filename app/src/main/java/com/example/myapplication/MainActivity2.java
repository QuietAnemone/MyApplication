
package com.example.myapplication;

import android.content.Intent;      // <-- вот этот импорт ОБЯЗАТЕЛЬНО!
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {
    private EditText editTextName, editTextPhone, editTextPCModel, editTextServiceType, editTextNote, editTextDate, editTextTime;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPCModel = findViewById(R.id.editTextPCModel);
        editTextServiceType = findViewById(R.id.editTextServiceType);
        editTextNote = findViewById(R.id.editTextNote);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);

        Button buttonSend = findViewById(R.id.buttonOpenStats); // именно так называется твоя кнопка!
        buttonSend.setOnClickListener(v -> submitRepairRequest());

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
    }

    private void submitRepairRequest() {
        String ownerName = editTextName.getText().toString().trim();
        String phoneNumber = editTextPhone.getText().toString().trim();
        String pcModel = editTextPCModel.getText().toString().trim();
        String serviceType = editTextServiceType.getText().toString().trim();
        String note = editTextNote.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();

        if (ownerName.isEmpty() || phoneNumber.isEmpty() || pcModel.isEmpty()) {
            Toast.makeText(this, "Введите ФИО, телефон и модель ПК", Toast.LENGTH_SHORT).show();
            return;
        }

        String status = "В работе";
        Request request = new Request(ownerName, phoneNumber, pcModel, serviceType, note, date, time, status);

        long id = dbHelper.addRepairRequest(request);

        if (id > 0) {
            Toast.makeText(this, "Заявка успешно создана!", Toast.LENGTH_SHORT).show();

            // Очистка полей
            editTextName.setText("");
            editTextPhone.setText("");
            editTextPCModel.setText("");
            editTextServiceType.setText("");
            editTextNote.setText("");
            editTextDate.setText("");
            editTextTime.setText("");


            Intent intent = new Intent(MainActivity2.this, StatisticsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Ошибка при создании заявки", Toast.LENGTH_SHORT).show();
        }
    }

}
