package com.example.sdarotwithmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Main activity1.

 *  @author	Nisim Doron Krief <nisimandroi@gmail.com>
 *  @version	1.0
 *  @since	3/01/2022 (the date of the package the class was added)
 *  Program that uses list and context menu in order to calculate sdarot
 *  this main activity get the netonim from the user */
public class MainActivity extends AppCompatActivity {
    /**
     * The Tb.
     */
    ToggleButton tb;
    /**
     * The Mispar.
     */
    EditText mispar, /**
     * The Kofetz.
     */
    kofetz;
    /**
     * The St.
     */
    String st;
    /**
     * The Sifra.
     */
    double sifra, /**
     * The Kfitza.
     */
    kfitza;


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

    /**
     * Done.
     *
     * @param view the view
     */
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

    /**
     * Bodek boolean.
     *
     * @return the boolean
     */
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

    /**
     * Onoroff.
     *
     * @param view the view
     */
    public void onoroff(View view) {
        if(tb.isChecked()){
            tb.setText("Geometrical");
        }
        else{
            tb.setText("Mathematical");

        }
    }
}