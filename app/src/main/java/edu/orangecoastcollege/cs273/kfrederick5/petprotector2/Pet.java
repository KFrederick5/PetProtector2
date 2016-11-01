package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

/**
 * Created by Link on 10/27/2016.
 */

public class Pet {

    private String mName;
    private String mDetails;
    private int mPhone;
    private String mImage;

    public Pet() {
    }

    public Pet(String name, String details, int phone, String image) {
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public int getPhone() {
        return mPhone;
    }

    public void setPhone(int phone) {
        mPhone = phone;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
