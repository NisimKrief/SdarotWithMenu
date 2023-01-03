package com.example.sdarotwithmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    ListView lv;
    TextView X1Tv, dTv,nTv, SnTv;
    double num, kfitza, sum;
    boolean MathOrGeo;
    String bhira = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = (ListView) findViewById(R.id.lv);
        X1Tv = (TextView) findViewById(R.id.X1Tv);
        sum = 0;

        Intent gi = getIntent();
        num = gi.getDoubleExtra("n", 0);
        kfitza = gi.getDoubleExtra("nn", 0);
        MathOrGeo = gi.getBooleanExtra("MathOrGeo", false);
        X1Tv.setText(""+num);
        double Sidra[] = new double[20];
        Sidra[0] = num;
        if (MathOrGeo == false) {
            for (int i = 1; i < 20; i++) {
                Sidra[i] = Sidra[i - 1] + kfitza;
            }
        }
        else {
            for (int i = 1; i < 20; i++) {
                Sidra[i] = Sidra[i - 1] * kfitza;
            }
        }
        String StringSidra[] = new String[20];
        for (int i = 0; i < 20; i++) {
            StringSidra[i] = ""+Sidra[i];
            StringSidra[i] = Mekatsher(StringSidra[i]);
        }
        lv.setOnItemLongClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, StringSidra);
        lv.setAdapter(adp);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (MathOrGeo == false){
            sum = ((i+1) * ((2 * num) + (i) * (kfitza)))/2;
            X1Tv.setText(""+sum);

        }
        else{
            sum = num * ((Math.pow(kfitza, (i + 1)) - 1)/(kfitza - 1));
        }
        bhira = (""+sum);
        bhira = Mekatsher(bhira);

        return false;
    }
    public static String Mekatsher(String st1){
        int count = 0;
        boolean EChecker = false;
        String ozer = "";
        for(int i = 0; i < st1.length(); i++){
            if (st1.charAt(i) == 'E'){
                EChecker = true;
            }
        }
        if (EChecker == true) {
            for (int i = st1.indexOf('.'); i <= st1.indexOf('E'); i++) {
                count++;
            }
            ozer = st1.substring(st1.indexOf('E') + 1, st1.length());
            count += Integer.parseInt(ozer);
            count -= 2;
            st1 = st1.substring(0, 4);
            ozer = "E"+count;
            st1 += ozer;

        }


        return st1;
    }
}