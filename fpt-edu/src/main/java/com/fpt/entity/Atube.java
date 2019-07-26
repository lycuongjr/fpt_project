package com.fpt.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Calendar;
@Entity
public class Atube {
    @Id
    private long id;
    @Index
    private String name;
    private String description;
    @Index
    private long createdAt;
    @Index
    private long updatedAt;
    private long deletedat;
    @Index
    private int status; // 1 active, 2 deactive
public Atube(){
    this.id = Calendar.getInstance().getTimeInMillis();
    this.createdAt = Calendar.getInstance().getTimeInMillis();
    this.updatedAt = Calendar.getInstance().getTimeInMillis();

}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedat() {
        return deletedat;
    }

    public void setDeletedat(long deletedat) {
        this.deletedat = deletedat;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
