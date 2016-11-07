package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDetailsActivity extends AppCompatActivity {

    private ImageView petImageView;
    private TextView petNameTextView;
    private TextView petDetailsTextView;
    private TextView petPhone;

    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        Intent detailsIntent = getIntent();
        String name = detailsIntent.getStringExtra("Name");
        String details = detailsIntent.getStringExtra("Details");
        String phone = detailsIntent.getStringExtra("Phone");
        String image = detailsIntent.getStringExtra("Image");
    }
}
