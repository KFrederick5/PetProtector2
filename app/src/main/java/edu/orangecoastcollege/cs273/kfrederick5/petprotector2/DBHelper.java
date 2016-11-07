package edu.orangecoastcollege.cs273.kfrederick5.petprotector2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Link on 10/27/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PetProtector";
    static final String DATABASE_TABLE = "Pets";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_IMAGE = "image";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_PHONE + " TEXT, "
                + FIELD_IMAGE + " TEXT" + ")";
        database.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    public void addPet(Pet newPet) {
        SQLiteDatabase petDB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, newPet.getName());
        values.put(FIELD_DETAILS, newPet.getDetails());
        values.put(FIELD_PHONE, newPet.getPhone());
        values.put(FIELD_IMAGE, newPet.getImage());

        petDB.insert(DATABASE_TABLE, null, values);

        petDB.close();
    }

    public ArrayList<Pet> getAllPets() {
        SQLiteDatabase petDB = this.getReadableDatabase();

        ArrayList<Pet> allPets = new ArrayList<>();

        Cursor results = petDB.query(DATABASE_TABLE, null, null, null, null, null, null, null);

        if (results.moveToFirst()) {
            do {
                int id = results.getInt(0);
                String name = results.getString(1);
                String details = results.getString(2);
                String phone = results.getString(3);
                String image = results.getString(4);
                allPets.add(new Pet(id, name, details, phone, image));
            } while (results.moveToNext());
        }

        petDB.close();
        results.close();
        return allPets;
    }

}
