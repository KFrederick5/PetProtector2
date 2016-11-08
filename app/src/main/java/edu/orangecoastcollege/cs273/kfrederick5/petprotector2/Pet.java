package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.net.Uri;

/**
 * Created by Link on 10/27/2016.
 */

public class Pet {

    private int mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImage;

    public Pet(String name, String details, String phone, Uri image) {
        this(-1, name, details, phone, image);
    }

    public Pet(int id, String name, String details, String phone, Uri image) {
        mId = id;
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImage = image;
    }

    public int getId() {return mId;}

    public void setId(int id) {mId = id;}

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

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public Uri getImage() {
        return mImage;
    }

    public void setImage(Uri image) {
        mImage = image;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mDetails='" + mDetails + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
