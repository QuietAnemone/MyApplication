package com.example.myapplication;

public class Request {
    private int id;
    private String ownerName;
    private String phoneNumber;
    private String pcModel;
    private String serviceType;
    private String note;
    private String date;
    private String time;
    private String status;
    private String completionDate;

    public Request() {
    }

    public Request(String ownerName, String phoneNumber, String pcModel,
                   String serviceType, String note, String date,
                   String time, String status) {
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.pcModel = pcModel;
        this.serviceType = serviceType;
        this.note = note;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPcModel() { return pcModel; }
    public void setPcModel(String pcModel) { this.pcModel = pcModel; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCompletionDate() { return completionDate; }
    public void setCompletionDate(String completionDate) { this.completionDate = completionDate; }
}
