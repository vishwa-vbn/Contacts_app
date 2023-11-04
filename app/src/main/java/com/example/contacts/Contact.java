package com.example.contacts;

import android.graphics.Bitmap;
import android.content.ContentValues;

public class Contact {

    private long id;
    private String label;
    private String name;
    private String callLogDate;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String phoneType;
    private String email;
    private String date;
    private String dateLabel;
    private String address;
    private String notes;
    private boolean isFavorite;
    private int groupId;
    private boolean isSelected;
    private String category;
    private String group;
    private Bitmap image;

    private String profilePicturePath;

    public Contact(long id, String firstName, String lastName, String phoneNumber, String phoneType,
                   String email, String date, String dateLabel, String address,
                   String notes, boolean isFavorite, int groupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.email = email;
        this.date = date;
        this.dateLabel = dateLabel;
        this.address = address;
        this.notes = notes;
        this.isFavorite = isFavorite;
        this.groupId = groupId;
        this.isSelected = false;
        this.profilePicturePath = "";
        this.image = null;
    }


    public Contact(long id, String name, String phoneNumber, String callLogDate, boolean isFavorite, String category, String group) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.callLogDate = callLogDate;
        this.isFavorite = isFavorite;
        this.category = category;
        this.group = group;
        this.isSelected = false;
    }

    //NEW,this method is called when trash is clicked in nav menu.
    public Contact(long id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }



    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    // Add a setter method for the profile picture path
    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public  String  getFirstName(){ return firstName; }

    public  String  getLastName(){ return lastName; }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCallLogDate() {
        return callLogDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategory() {
        return category;
    }

    public String getGroup() {
        return group;
    }

    public Bitmap getImage() {
        return image;
    }

    public boolean isSelected() {
        return isSelected;
    }


    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }



    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getLabel() {
        return label;
    }




    public String getDate() {
        return callLogDate;
    }
}
