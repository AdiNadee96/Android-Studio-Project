package com.example.ordinary_scoop_essentials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database Name
    private static final String DB_NAME = "essentials.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, "essentials.db", null, DB_VERSION);
    }

    //Table 01
    private static final String TABLE_NAME1 = "registration";
    private static final String USER_ID_COL = "user_id";
    private static final String USER_NAME_COL = "user_name";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";

    //Table 02
    private static final String TABLE_NAME2 = "products";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "product_name";
    private static final String CATEGORY_COL = "category";
    private static final String PRICE_COL = "price";
    private static final String AVAILABILITY_COL = "availability";
    private static final String BUYWHERE_COL = "buy_where";




    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1= "CREATE TABLE " + TABLE_NAME1 + " ("
                + USER_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";


       String query2 = "CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + CATEGORY_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + AVAILABILITY_COL + " TEXT,"
               + BUYWHERE_COL + "TEXT)";

        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);

    }

    public boolean registerUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = db.insert("registration", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from registration where user_name = ? and password = ?",
                new String[] {username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("Select * from registration where user_name = ? ", new String[] {username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public void insertProduct(String productName, String category, String price, String availability, String buyWhere){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_name", productName);
        values.put("category", category);
        values.put("price", price);
        values.put("availability", availability);
        values.put("buy_where", buyWhere);

       this.getWritableDatabase().insertOrThrow("products", "", values);
    }

    public void deleteProduct(String productName) {
        this.getWritableDatabase().delete("products","product_name ='"+productName+"'", null);
    }

    public void updateProduct(String productName, String newAvailability){
        this.getWritableDatabase().execSQL("UPDATE products SET availability='"+ newAvailability+"'WHERE product_name = '"+productName+"'");
    }

    public void viewAll(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM products", null);
        textView.setText("");
        while(cursor.moveToNext()){
            textView.append(cursor.getString(1) + "" + cursor.getString(2) +""+ cursor.getString(3) + "" + cursor.getString(4));
        }
    }





}
