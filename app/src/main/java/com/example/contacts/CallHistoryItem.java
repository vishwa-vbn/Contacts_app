package com.example.contacts;

//package com.example.contacts;
//
//public class CallHistoryItem {
//    private String dateTime;
//    private String phoneNumber;
//    private boolean isIncomingCall;
//
//    public CallHistoryItem(String dateTime, String phoneNumber, boolean isIncomingCall) {
//        this.dateTime = dateTime;
//        this.phoneNumber = phoneNumber;
//        this.isIncomingCall = isIncomingCall;
//    }
//
//    public String getDateTime() {
//        return dateTime;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public boolean isIncomingCall() {
//        return isIncomingCall;
//    }
//}
public class CallHistoryItem {
    private String dateTime; // Date and time of the call
    private String phoneNumber; // Contact's phone number
    private String contactName; // Contact's name
    private long callDuration; // Call duration in milliseconds

    private long  contactId;
    private boolean isIncomingCall;

    public CallHistoryItem(String dateTime, String phoneNumber, String contactName, long callDuration, long contactId) {
        this.dateTime = dateTime;
        this.contactId = contactId;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.callDuration = callDuration;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getContactId() {
        return contactId;
    }


    public String getContactName() {
        return contactName;
    }

    public long getCallDuration() {
        return callDuration;
    }

    public boolean isIncomingCall() {
        return isIncomingCall;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setCallDuration(long callDuration) {
        this.callDuration = callDuration;
    }

    public void setIsIncomingCall(boolean isIncomingCall) {
        this.isIncomingCall = isIncomingCall;
    }

    public String getCallDate() {
        return dateTime;
    }
}
