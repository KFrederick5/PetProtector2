package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PetListActivity extends AppCompatActivity {

    private ImageView petImageView;
    private static final int REQUEST_CODE = 100;

    private DBHelper database;
    private List<Pet> petsList;
    private PetListAdapter mPetListAdapter;

    private EditText petNameText;
    private EditText petDetailsText;
    private EditText petPhoneText;
    private ListView petsListView;

    //Sets member variable to whatever image has been selected.
    //Default is set to none.png (R.drawable.none)
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        database = new DBHelper(this);
        petsList = database.getAllPets();

        mPetListAdapter = new PetListAdapter(this, R.layout.pet_item, petsList);

        petsListView = (ListView) findViewById(R.id.petsListView);
        petsListView.setAdapter(mPetListAdapter);

        petImageView = (ImageView) findViewById(R.id.petImageView);

        //constructs a full URI to any android resource
        imageURI = getUriToResource(this, R.drawable.none);

        //Set image of URI as code.
        petImageView.setImageURI(imageURI);
    }

    public void seePetDetails(View v)
    {
        if(v instanceof LinearLayout){
            LinearLayout selectedLayout = (LinearLayout) v;
            Pet pet = (Pet) selectedLayout.getTag();

            Intent intent = new Intent(this, PetDetailsActivity.class);
            intent.putExtra("Name", pet.getName());
            intent.putExtra("Details", pet.getDetails());
            intent.putExtra("Phone", pet.getPhone());
            intent.putExtra("Image", pet.getImage());
            startActivity(intent);
        }
    }
    public void addPet(View v)
    {
        petNameText = (EditText) findViewById(R.id.nameEditText);
        petDetailsText = (EditText) findViewById(R.id.detailsEditText);
        petPhoneText = (EditText) findViewById(R.id.phoneEditText);

        String name = petNameText.getText().toString();
        String details = petDetailsText.getText().toString();
        String phone = petPhoneText.getText().toString();

        if(name.isEmpty() || details.isEmpty() || phone.isEmpty())
            Toast.makeText(this, "All fields must have content for a pet to be displayed.",
                    Toast.LENGTH_LONG).show();
        else
        {
            Pet newPet = new Pet(name, details, phone, imageURI);

            mPetListAdapter.add(newPet);

            database.addPet(newPet);

            petNameText.setText("");
            petDetailsText.setText("");
            petPhoneText.setText("");
            imageURI = getUriToResource(this, R.drawable.none);
            petImageView.setImageURI(imageURI);
        }
    }

    public void clearList(View v)
    {
        petsList.clear();
        database.deleteAllPets();
        mPetListAdapter.notifyDataSetChanged();
    }

    public void setPetImageView(View v)
    {
        //List of all permissions needed from the user
        ArrayList<String> permList = new ArrayList<>();

        //Start by seeing if we have permission to the camera
        int cameraPermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if(cameraPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.CAMERA);

        int readExternal = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if(readExternal != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        int writeExternal = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(writeExternal != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //If list size > 0 must request permissions from user
        if(permList.size() > 0)
        {
            //Convert arrayList into an array of strings
            String[] perms = new String[permList.size()];
            //Request permissions from the user

            ActivityCompat.requestPermissions(
                    this, permList.toArray(perms), REQUEST_CODE);
        }

        if(cameraPermission == PackageManager.PERMISSION_GRANTED &&
                readExternal == PackageManager.PERMISSION_GRANTED &&
                writeExternal == PackageManager.PERMISSION_GRANTED)
        {
            //Use intent to launch gallery and take pics
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, REQUEST_CODE);
        }
        else
            Toast.makeText(this,
                    "Pet protector requires camera and external storage permission",
                    Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK &&
                data != null) {
            imageURI = data.getData();
            petImageView.setImageURI(imageURI);
        }
    }

    /**
     * Get uri to any resource type within an Android Studio project. Method
     * is public static to allow other classes to use it as a helper function
     * @param context The current context
     * @param resId The resource identifier of the drawable
     * @return Uri to resource by given id
     * @throws Resources.NotFoundException
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId)
            throws Resources.NotFoundException {

        //Return a resource instance for application package
        Resources res = context.getResources();

        //return URI
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId));
    }
}