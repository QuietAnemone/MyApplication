package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
            buttonMarkDone.setOnClickListener(v -> {

                dbHelper.updateRepairRequestStatus(request.getId(), "Готово", "");

                request.setStatus("Готово");
                notifyDataSetChanged();
            });
        } else {
            buttonMarkDone.setVisibility(View.GONE);
        }

        return convertView;
    }
}
