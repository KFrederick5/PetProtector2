package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

/**
 * Created by Link on 10/27/2016.
 */

public class Pet {
    private String mDetails;
    private int mId;
    private String mImageURI;
    private String mName;
    private int mPhone;


    public String getmDetails() {
        return mDetails;
    }

    public int getmId() {
        return mId;
    }

    public String getmImageURI() {
        return mImageURI;
    }

    public String getmName() {
        return mName;
    }

    public int getmPhone() {
        return mPhone;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmImageURI(String mImageURI) {
        this.mImageURI = mImageURI;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPhone(int mPhone) {
        this.mPhone = mPhone;
    }

    public void Pet(String name, String details, int phone)
    {

    }
}
