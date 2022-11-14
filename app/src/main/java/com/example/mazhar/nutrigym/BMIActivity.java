package com.example.mazhar.nutrigym;

/**
 * Created by mazhar on 5/6/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        final Button button_calc = (Button) findViewById(R.id.button_calc);
        final EditText field_weight = (EditText) findViewById(R.id.field_weight);
        final EditText field_height = (EditText) findViewById(R.id.field_height);
        final EditText height_inch = (EditText) findViewById(R.id.height_inch);
        final TextView view_result = (TextView) findViewById(R.id.view_result);
        final TextView view_msg = (TextView) findViewById(R.id.view_msg);


        button_calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double weight;
                int height;
                double finalheight;
                double bmi;
                int inch;
                String msg = "";
                view_result.setEnabled(false);
                view_msg.setEnabled(false);


                if (field_height.getText().toString().equals("") || field_weight.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(),"Invalid inputs! Try again :)",Toast.LENGTH_LONG).show();

                } else {

                    weight = Double.parseDouble(field_weight.getText().toString());
                    height = (int) Double.parseDouble(field_height.getText().toString());
                    inch = (int) Double.parseDouble(height_inch.getText().toString());

                    finalheight = (height*0.3048)+(inch*0.0254);

                    bmi = weight / (finalheight * finalheight);

                    view_result.setText(String.valueOf(bmi));

                    if (bmi < 18.5) {
                        msg = "Underweight :(";
                        Toast.makeText(getApplicationContext(),"You need to gain some weight!",Toast.LENGTH_LONG).show();
                    } else if (bmi > 18.5 && bmi < 25.0) {
                        msg = "Normal :)   ";
                        Toast.makeText(getApplicationContext(),"Keep up the current health status!",Toast.LENGTH_LONG).show();
                    } else if (bmi > 25.0) {
                        msg = "Overweight :3";
                        Toast.makeText(getApplicationContext(),"You need to see a dietitian asap!",Toast.LENGTH_LONG).show();
                    }

                    view_msg.setText(msg);

                }
            }
        });
    }
}
