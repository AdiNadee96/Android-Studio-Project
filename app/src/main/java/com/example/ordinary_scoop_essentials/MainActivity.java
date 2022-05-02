package com.example.ordinary_scoop_essentials;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username1, email, password1;
    private Button btnRegister1, btnLogin1;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username1 = findViewById(R.id.username1);
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnLogin1 =  findViewById(R.id.btnLogin1);
        myDB = new DatabaseHelper(this);

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username1.getText().toString();
                String mail = email.getText().toString();
                String pass = password1.getText().toString();
                boolean var = myDB.registerUser(user, mail, pass);

                    if(user.equals("")||pass.equals("")||mail.equals(""))
                        Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    else {
                        if(user.length() < 5 || pass.length()< 5) {
                            Toast.makeText(MainActivity.this, "Username & " +
                                    "Password must be more than 5 characters", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(MainActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                    }
                }
        });

        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}