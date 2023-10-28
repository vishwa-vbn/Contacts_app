package com.example.contacts;

public class Group {
    private long groupId;
    private String groupName;

    public Group(long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }



    //this is comment
    public long getId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
