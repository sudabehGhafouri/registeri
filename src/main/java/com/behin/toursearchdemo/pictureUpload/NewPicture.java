package com.behin.toursearchdemo.pictureUpload;


public class NewPicture {
    private String name;
    private boolean error;

    public NewPicture(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}