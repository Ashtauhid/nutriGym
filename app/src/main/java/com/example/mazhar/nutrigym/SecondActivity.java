package com.example.mazhar.nutrigym;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SecondActivity extends AppCompatActivity {
    Button btnSearch, btnBackSecond;
    EditText txtName, txtUnit;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        register();
    }

    String search = "";
    Double cal = 0.0;

    private void register() {
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search = s.toString();
            }
        });
        txtUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String sa = s.toString();
                if (sa.trim().length() == 0) {
                    sa = "0.0";
                }
                cal = Double.parseDouble(sa);

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtName.getText().toString().length() > 0 && txtUnit.getText().toString().length() > 0) {
                    Cursor cursor = myDb.getSearchData(search);
                    if (cursor.getCount() == 0) {
                        showMessage("Error!", "Data not found");
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();

                        while (cursor.moveToNext()) {
                            stringBuilder.append("Food name: " + cursor.getString(1) + "\n");
                            stringBuilder.append("Calories taken: " + (cursor.getDouble(2) * cal) + "\n");

                        }
                        showMessage("Data", stringBuilder.toString());
                    }

                    clearData();
                } else {
                    showMessage("Error!","Please enter data to continue");
                }
            }
        });
        btnBackSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, LauncherActivity.class));
                finish();
            }
        });
    }

    public void clearData() {
        txtName.setText("");
        txtUnit.setText("");
    }

    private void init() {
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnBackSecond = (Button) findViewById(R.id.btnBackSecond);
        txtName = (EditText) findViewById(R.id.txtName);
        txtUnit = (EditText) findViewById(R.id.txtUnit);
        myDb = new DatabaseHelper(this);
    }

    public void showMessage(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
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
}

