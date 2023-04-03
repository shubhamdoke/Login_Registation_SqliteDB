package com.example.my_kuchbhi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_kuchbhi.Helper.DBHelper;
import com.example.my_kuchbhi.R;

import java.util.Calendar;

public class Registation extends AppCompatActivity {
    EditText uname, umob, uemail,upass;
    TextView gotolog, tv_bod;
    RadioButton radio_id1, radio_id2;
    RadioGroup radioGroup;
    DBHelper dbHelper;
    Button signup;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    RadioGroup radioButton;
    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        uname = findViewById(R.id.uname);
        umob = findViewById(R.id.umob);
        uemail = findViewById(R.id.uemail);
        upass=findViewById(R.id.upass);
        gotolog = findViewById(R.id.goto_log);
        signup = findViewById(R.id.btn_signup);
        tv_bod = findViewById(R.id.tv_bod);
    //    radioButton=findViewById(R.id.groupradio);

//
//        String name=uname.getText().toString();
//        String mob=umob.getText().toString();
//        String email=uemail.getText().toString();
//        String bod=tv_bod.getText().toString();
//        String pass=upass.getText().toString();
  //      final String[] gender = new String[1];



//        radioGroup = findViewById(R.id.groupradio);
//
//        // Uncheck or reset the radio buttons initially
//        radioGroup.clearCheck();
//
//        // Add the Listener to the RadioGroup
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//
//            public void onCheckedChanged(RadioGroup group,
//                                         int checkedId) {
//
//                // Get the selected Radio Button
//               radioButton = group.findViewById(checkedId);
//            }
//        });


        tv_bod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Registation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tv_bod.setText("BOD -> " + day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }


        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                radioButton = radioGroup.findViewById(selectedId);
              //  gender=radioButton.getText().toString();


                String name=uname.getText().toString();
                String mob=umob.getText().toString();
                String email=uemail.getText().toString();
                String bod=tv_bod.getText().toString();
                String pass=upass.getText().toString();
                Log.d("Values.....", "onClick: "+name+mob+email+bod+pass);
                if (name.isEmpty() || mob.isEmpty() || email.isEmpty() || bod.isEmpty()) {
                    Toast.makeText(Registation.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(name,mob,email,bod,pass);
                    Toast.makeText(Registation.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }

            }

        });

        gotolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registation.this, Login.class);
                startActivity(intent);
            }
        });


    }

    private void insertData(String name, String mob, String email,String bod, String Password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.COL_2,name);
        contentValues.put(DBHelper.COL_3,mob);
        contentValues.put(DBHelper.COL_4,email);
        contentValues.put(DBHelper.COL_5,bod);
        contentValues.put(DBHelper.COL_6,Password);

        long id = db.insert(DBHelper.TABLE_NAME,null,contentValues);
    }
}