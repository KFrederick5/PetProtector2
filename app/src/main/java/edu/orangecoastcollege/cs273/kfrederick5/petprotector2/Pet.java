package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

/**
 * Created by Link on 10/27/2016.
 */

public class Pet {

    private int mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private String mImage;

    public Pet() {
    }

    public Pet(int id, String name, String details, String phone, String image) {
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

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
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
