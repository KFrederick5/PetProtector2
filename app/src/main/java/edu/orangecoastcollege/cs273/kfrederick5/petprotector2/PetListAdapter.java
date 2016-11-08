package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PetListAdapter extends ArrayAdapter<Pet> {

    private Context mContext;
    private List<Pet> mPetList = new ArrayList<>();
    private int mResourceId;

    private ImageView petListImageView;
    private TextView petListNameText;
    private TextView petListDetailsText;

    private Uri imageURI;

    public PetListAdapter(Context c, int rId, List<Pet> allPets){
        super(c, rId, allPets);
        mContext = c;
        mResourceId = rId;
        mPetList = allPets;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        final Pet selectedPet = mPetList.get(pos);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(mResourceId, null);

        LinearLayout petListLinearLayout = (LinearLayout) v.findViewById(R.id.petItemLinearLayout);
        petListLinearLayout.setTag(selectedPet);

        petListImageView = (ImageView) v.findViewById(R.id.listPetImageView);
        petListNameText = (TextView) v.findViewById(R.id.listPetName);
        petListDetailsText = (TextView) v.findViewById(R.id.listPetDetails);

        petListImageView.setImageURI(selectedPet.getImage());

        petListNameText.setText(selectedPet.getName());
        petListDetailsText.setText(selectedPet.getDetails());

        return v;
    }
}

