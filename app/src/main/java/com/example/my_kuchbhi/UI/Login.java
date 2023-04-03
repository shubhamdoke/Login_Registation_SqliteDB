package com.example.my_kuchbhi.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_kuchbhi.Dashboard;
import com.example.my_kuchbhi.Helper.DBHelper;
import com.example.my_kuchbhi.R;

public class Login extends AppCompatActivity {
    TextView goto_reg;
    EditText email1, pass;
    Button btn_signin;
    DBHelper dbHelper;
    Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email1 = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btn_signin = findViewById(R.id.btn_signin);
        goto_reg = findViewById(R.id.goto_reg);




            btn_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = email1.getText().toString();
                    String password = pass.getText().toString();


                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(Login.this, "Enter your Email and Password to login", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        cursor = db.rawQuery("SELECT *FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.COL_4 + "=? AND " + DBHelper.COL_5 + "=?", new String[]{email, password});
                        if (cursor != null) {
                            if (cursor.getCount() > 0) {
                                startActivity(new Intent(Login.this, Dashboard.class));
                                Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


//
                }
            });

            goto_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login.this, Registation.class);
                    startActivity(i);


                }
            });
        }




}