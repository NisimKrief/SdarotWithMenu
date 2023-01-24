package com.example.sdarotwithmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Main activity 2.
 * this is the Main activity cause its the list and the context menu as well as the long click.
 */
public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnCreateContextMenuListener {
    /**
     * The Lv.
     */
    ListView lv;
    /**
     * The X 1 tv.
     */
    TextView X1Tv, /**
     * The Type tv.
     */
    TypeTv;
    /**
     * The Num.
     */
    double num, /**
     * The Kfitza.
     */
    kfitza, /**
     * The Sum.
     */
    sum;
    /**
     * The Math or geo.
     */
    boolean MathOrGeo;
    /**
     * The Bhira.
     */
    String bhira = "";
    /**
     * The Index.
     */
    int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv = (ListView) findViewById(R.id.lv);
        X1Tv = (TextView) findViewById(R.id.X1Tv);
        TypeTv = (TextView) findViewById(R.id.TypeTv);
        sum = 0;

        Intent gi = getIntent();
        num = gi.getDoubleExtra("n", 0);
        kfitza = gi.getDoubleExtra("nn", 0);
        MathOrGeo = gi.getBooleanExtra("MathOrGeo", false);
        X1Tv.setText(""+num);
        double Sidra[] = new double[20];
        Sidra[0] = num;
        lv.setOnCreateContextMenuListener(this);
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
        index = i;

        return false;
    }

    /**
     * Mekatsher string.
     *
     * @param st1 the st 1
     * @return the string
     */
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo){
        menu.setHeaderTitle("Choose");
        menu.add("Index");
        menu.add("Sum");
    }
    public boolean onContextItemSelected(MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("Index")){
            TypeTv.setText("Index = ");
            X1Tv.setText(Integer.toString(index));
        }
        if(st.equals("Sum")) {
            TypeTv.setText("Sn = ");
            if (MathOrGeo == false){
                sum = ((index+1) * ((2 * num) + (index) * (kfitza)))/2;


            }
            else{
                sum = num * ((Math.pow(kfitza, (index + 1)) - 1)/(kfitza - 1));
            }
            bhira = (""+sum);
            bhira = Mekatsher(bhira);
            X1Tv.setText(""+bhira);
        }
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        String st2=item.getTitle().toString();
        if(st2.equals("Credits")){
            Intent ji = new Intent(this,MainActivity3.class);
            startActivity(ji);
        }

        return true;
    }

}