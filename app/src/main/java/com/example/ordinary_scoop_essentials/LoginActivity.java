package com.example.ordinary_scoop_essentials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username2, password2;
    private Button btnLogin2, btnRegister2;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username2 = findViewById(R.id.username2);
        password2 = findViewById(R.id.password2);
        btnLogin2 = findViewById(R.id.btnLogin2);
        btnRegister2 =  findViewById(R.id.btnRegister2);
        myDB = new DatabaseHelper(this);

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username2.getText().toString();
                String password = password2.getText().toString();

                if(username2.equals("")||password2.equals(""))
                    Toast.makeText(LoginActivity.this, "All the fields are required !!!", Toast.LENGTH_SHORT).show();
                else{
                    boolean checkUserPass = myDB.checkUsernamePassword(username,password);
                    if(checkUserPass == true){
                        Toast.makeText(LoginActivity.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}