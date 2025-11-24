package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;
=======
>>>>>>> daec64f8c7e765329b522865633226af1c04c126
import android.widget.TextView;
import java.util.List;

public class RequestAdapter extends BaseAdapter {
    private Context context;
    private List<Request> requestList;
    private DatabaseHelper dbHelper;

    public RequestAdapter(Context context, List<Request> requestList, DatabaseHelper dbHelper) {
        this.context = context;
        this.requestList = requestList;
        this.dbHelper = dbHelper;
    }

    @Override
    public int getCount() {
        return requestList.size();
    }

    @Override
    public Object getItem(int position) {
        return requestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return requestList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_request, parent, false);
        }

        Request request = requestList.get(position);
<<<<<<< HEAD

        EditText editOwnerName = convertView.findViewById(R.id.editOwnerName);
        EditText editPhoneNumber = convertView.findViewById(R.id.editPhoneNumber);
        EditText editPcModel = convertView.findViewById(R.id.editPcModel);
        EditText editServiceType = convertView.findViewById(R.id.editServiceType);
        EditText editNote = convertView.findViewById(R.id.editNote);
        TextView textDateTimeStatus = convertView.findViewById(R.id.textDateTimeStatus);
        Button buttonMarkDone = convertView.findViewById(R.id.buttonMarkDone);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        // Заполняем поля данными
        editOwnerName.setText(request.getOwnerName());
        editPhoneNumber.setText(request.getPhoneNumber());
        editPcModel.setText(request.getPcModel());
        editServiceType.setText(request.getServiceType());
        editNote.setText(request.getNote());
        textDateTimeStatus.setText(String.format("Дата: %s | Время: %s | %s",
                request.getDate(), request.getTime(), request.getStatus()));

        // Обработчик изменения данных
        View.OnFocusChangeListener changeListener = (v, hasFocus) -> {
            if (!hasFocus) {
                request.setOwnerName(editOwnerName.getText().toString());
                request.setPhoneNumber(editPhoneNumber.getText().toString());
                request.setPcModel(editPcModel.getText().toString());
                request.setServiceType(editServiceType.getText().toString());
                request.setNote(editNote.getText().toString());

                dbHelper.updateRepairRequest(
                        request.getId(),
                        request.getOwnerName(),
                        request.getPhoneNumber(),
                        request.getPcModel(),
                        request.getServiceType(),
                        request.getNote(),
                        request.getDate(),
                        request.getTime(),
                        request.getStatus()
                );
            }
        };

        editOwnerName.setOnFocusChangeListener(changeListener);
        editPhoneNumber.setOnFocusChangeListener(changeListener);
        editPcModel.setOnFocusChangeListener(changeListener);
        editServiceType.setOnFocusChangeListener(changeListener);
        editNote.setOnFocusChangeListener(changeListener);

        // Показываем кнопки для статуса "В работе"
        if ("В работе".equals(request.getStatus())) {
            buttonMarkDone.setVisibility(View.VISIBLE);
            buttonDelete.setVisibility(View.VISIBLE);

=======
        TextView textInfo = convertView.findViewById(R.id.textInfo);
        Button buttonMarkDone = convertView.findViewById(R.id.buttonMarkDone);

        String info = String.format(
                "ФИО: %s\nТелефон: %s\nПК: %s\nУслуга: %s\nПримечание: %s\nДата: %s\nВремя: %s\nСтатус: %s",
                request.getOwnerName(),
                request.getPhoneNumber(),
                request.getPcModel(),
                request.getServiceType(),
                request.getNote(),
                request.getDate(),
                request.getTime(),
                request.getStatus()
        );
        textInfo.setText(info);

        // Показываем галочку только для статуса "В работе"
        if ("В работе".equals(request.getStatus())) {
            buttonMarkDone.setVisibility(View.VISIBLE);
>>>>>>> daec64f8c7e765329b522865633226af1c04c126
            buttonMarkDone.setOnClickListener(v -> {
                dbHelper.updateRepairRequestStatus(request.getId(), "Готово", "");
                request.setStatus("Готово");
                notifyDataSetChanged();
            });
<<<<<<< HEAD

            buttonDelete.setOnClickListener(v -> {
                dbHelper.deleteRepairRequest(request.getId());
                requestList.remove(position);
                notifyDataSetChanged();
            });
        } else {
            buttonMarkDone.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
=======
        } else {
            buttonMarkDone.setVisibility(View.GONE);
>>>>>>> daec64f8c7e765329b522865633226af1c04c126
        }

        return convertView;
    }

<<<<<<< HEAD
=======
    // Метод для обновления данных списка
>>>>>>> daec64f8c7e765329b522865633226af1c04c126
    public void updateRequests(List<Request> newRequests) {
        this.requestList = newRequests;
    }
}
