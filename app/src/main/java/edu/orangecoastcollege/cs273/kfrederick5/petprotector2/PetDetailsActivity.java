package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDetailsActivity extends AppCompatActivity {

    private ImageView petImageView;
    private TextView petNameTextView;
    private TextView petDetailsTextView;
    private TextView petPhoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        petNameTextView = (TextView) findViewById(R.id.petNameTextView);
        petDetailsTextView = (TextView) findViewById(R.id.petDetailsTextView);
        petPhoneTextView = (TextView) findViewById(R.id.petPhoneTextView);
        petImageView = (ImageView) findViewById(R.id.petImageView);

        Intent detailsIntent = getIntent();

        Uri image = detailsIntent.getParcelableExtra("Image");
        petImageView.setImageURI(image);
        petNameTextView.setText(detailsIntent.getStringExtra("Name"));
        petDetailsTextView.setText(detailsIntent.getStringExtra("Details"));
        petPhoneTextView.setText(detailsIntent.getStringExtra("Phone"));
    }

}
