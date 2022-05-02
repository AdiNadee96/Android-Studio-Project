package com.example.ordinary_scoop_essentials;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProducts extends AppCompatActivity {

    private DatabaseHelper DBHelper;
    EditText productName, productCategory, price, availability, buyWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        productName = findViewById(R.id.idProductName);
        productCategory = findViewById(R.id.idCategory);
        price = findViewById(R.id.idPrice);
        availability = findViewById(R.id.idProductAvailability);
        buyWhere = findViewById(R.id.idBuyWhere);

        DBHelper = new DatabaseHelper(this);


    }

    /*public void btn_Click(View view){
        switch (view.getId()){
            case R.id.btnAdd:
                try{
                    DBHelper.insertProduct(productName.getText().toString(), productCategory.getText().toString(), price.getText().toString(),
                            availability.getText().toString(), buyWhere.getText().toString());

                }catch (SQLiteException e){
                    Toast.makeText(this, "Already Exists", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.idBtnDelete:
                DBHelper.deleteProduct(productName.getText().toString());
                break;

            case R.id.idBtnUpdate:
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddProducts.this);
                dialog.setTitle("Enter new Availability");

                EditText new_availability = new EditText(this);
                dialog.setView(new_availability);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper.updateProduct(availability.getText().toString(), new_availability.getText().toString());
                    }
                });
                dialog.show();
        }
    }*/
}