package com.example.mazhar.nutrigym;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class FDActivity extends AppCompatActivity {
    EditText txtFoodName, txtCalorie;
    Spinner ddlFoodCatagory;
    Button button, btnShow, btnBackMain,btnDelete;

    DatabaseHelper myDB;

    String catagory = "", food_name;
    Double calorie = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd);
        init();
        registerEvents();
    }

    private void registerEvents() {
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FDActivity.this,LauncherActivity.class));
                finish();
            }
        });

        ddlFoodCatagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catagory = ddlFoodCatagory.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        txtFoodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                food_name = s.toString();
            }
        });
        txtCalorie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String ss = s.toString();
                if (ss.length() == 0) {
                    ss = "0";
                }

                calorie = Double.parseDouble(ss);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAll();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {

        boolean isDataInserted = myDB.insertData(food_name, calorie, catagory);
        if (isDataInserted) {
            Toast.makeText(FDActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FDActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

        }
        clearData();
    }

    private void deleteData() {
        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setInputType(InputType.TYPE_CLASS_NUMBER
        );

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Index To Delete")
                .setView(input).setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Integer deletedRow = myDB.deleteData(input.getText().toString());
                                if (deletedRow > 0) {
                                    showMessage("Info", "Data Deleted");
                                } else {
                                    showMessage("Info", "Data not Deleted");

                                }
                            }

                        }).setNegativeButton("Cancel", null).show();
        clearData();


    }

    private void init() {


        myDB = new DatabaseHelper(this);

        txtFoodName = (EditText) findViewById(R.id.txtFoodName);
        btnShow = (Button) findViewById(R.id.btnShow);
        btnBackMain = (Button) findViewById(R.id.btnBackMain);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtCalorie = (EditText) findViewById(R.id.txtCalorie);
        ddlFoodCatagory = (Spinner) findViewById(R.id.ddlFoodCatagory);
        button = (Button) findViewById(R.id.button);
        addDataToSpinner();
    }

    public void viewAll() {
        Cursor cursor = myDB.getAllData();
        if (cursor.getCount() == 0) {
            showMessage("Data Not Found", "No Data Available");
        } else {
            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                sb.append("Index :" + cursor.getInt(0) + "\nFood Name: " + cursor.getString(1) + "\n");
                sb.append("Calories: " + cursor.getDouble(2) + "\n");
                sb.append("Category: " + cursor.getString(3) + "\n\n");
            }
            showMessage("Data", sb.toString());

        }
        clearData();

    }

    public void clearData() {
        txtFoodName.setText("");
        txtCalorie.setText("");
    }

    public void showMessage(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }


    private void addDataToSpinner() {
        ArrayList<String> dataCatagory;
        dataCatagory = new ArrayList<>();
        dataCatagory.add("CAT A (in gm)");
        dataCatagory.add("CAT B (in ml)");
        dataCatagory.add("CAT C (in piece)");
        dataCatagory.add("CAT D (in ts)");

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(FDActivity.this, R.layout.support_simple_spinner_dropdown_item, dataCatagory);

        ddlFoodCatagory.setAdapter(stringArrayAdapter);
    }
}

