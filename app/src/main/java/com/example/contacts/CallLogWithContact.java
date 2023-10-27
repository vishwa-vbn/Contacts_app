package com.example.contacts;

public class CallLogWithContact {
    private long contactId;
    private String contactName;
    private String callDate;

    public CallLogWithContact(long contactId, String contactName, String callDate) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.callDate = callDate;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }
}
