package com.example.sdarotwithmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton tb;
    EditText mispar, kofetz;
    String st;
    double sifra, kfitza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (ToggleButton) findViewById(R.id.tB1);
        mispar = (EditText) findViewById(R.id.eTa1);
        kofetz = (EditText) findViewById(R.id.eTD);

        tb.setText("Mathematical");
        mispar.setHint("enter the first nunber: ");
        kofetz.setHint("enter the seder kfitza: ");




    }

    public void Done(View view) {
        if(bodek() == true) {
            st = mispar.getText().toString();
            sifra = Double.parseDouble(st);
            st = kofetz.getText().toString();
            kfitza = Double.parseDouble(st);
            Intent si = new Intent(this, MainActivity2.class);
            si.putExtra("n", sifra);
            si.putExtra("nn", kfitza);
            si.putExtra("MathOrGeo", tb.isChecked());
            startActivity(si);
        }
        else{
            mispar.setText("");
            kofetz.setText("");
            Toast.makeText(MainActivity.this, "You must enter a number", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean bodek(){
        st  = kofetz.getText().toString();
        if (st.matches("-?\\d+(\\.\\d+)?")) {
            st  = mispar.getText().toString();
            if (st.matches("-?\\d+(\\.\\d+)?")) {
                return true;
            }
        }
        return false;
    }

    public void onoroff(View view) {
        if(tb.isChecked()){
            tb.setText("Geometrical");
        }
        else{
            tb.setText("Mathematical");

        }
    }
}