package com.example.admin.dinnerroulette;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    static ArrayList<String> menu = new ArrayList<String>();

    final Context context3 = this;
    //static int n=0;
    static byte[] buffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            InputStream inputStream = openFileInput("file.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            String ret = stringBuilder.toString();
            String[] menu1 = ret.split(";");
            for(int i =0;i<menu1.length;i++)
            {
                if(menu1[i].equals(""))
                {

                }
                else
                {menu.add(menu1[i]);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*menu.add("Pho");
        menu.add("Hu tieu");
        menu.add("Banh Mi");*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button)((findViewById(R.id.button)));


        button1.setOnClickListener(new View.OnClickListener() {
            String text = "";

            public void onClick(View v) {
                String ret="";
                //submititem(v,v.getContext());
                /*try {
                    InputStream inputStream = openFileInput("file.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        stringBuilder.append(receiveString);
                    }

                    inputStream.close();
                    ret = stringBuilder.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                    //String[] shit = ret.split(";");
                    if(menu.size()==0)
                    {
                        printStarter(v,"List is empty, please add something!");
                    }
                    else{
                    text = menu.get((int)(Math.random()*(menu.size())));
                    String meal = text;
                    printStarter(v, meal);}
                //button1.setText(n);
            }
        });

        Button button2 = (Button)((findViewById(R.id.button2)));


        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ret="";
                submititem(v,v.getContext());
            }
        });

        Button button3 = (Button)((findViewById(R.id.button3)));


        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clearList(v);
            }
        });

    }
    public void printStarter(View view, String meal) {
        //setContentView(R.layout.activity_main);
        TextView tv1 = (TextView)findViewById(R.id.txtview);
        tv1.setText(meal);
        //String an = (Integer.toString(n));
        //tv1.setText(an);
    }
    public void clearList(View view)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("file.txt", Context.MODE_PRIVATE));

            outputStreamWriter.write("");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while(menu.size()!=0)
        {
            menu.remove(0);
        }
        TextView tv1 = (TextView)findViewById(R.id.txtview);
        tv1.setText("List cleared");
    }
    public void submititem(View view, Context context) {
        EditText user = (EditText)findViewById(R.id.editText);
        String and = user.getText().toString();
        for(int i = 0;i<menu.size();i++)
        {
            if(menu.get(i).equals(and))
            {
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(context3);

// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("already added");
                builder.setTitle("ERROR");

// 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                return;
            }
        }
        if(and.equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context3);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Type something in!");
            builder.setTitle("ERROR");

// 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        menu.add(and);
        AlertDialog.Builder builderw = new AlertDialog.Builder(context3);

// 2. Chain together various setter methods to set the dialog characteristics
        builderw.setMessage("added successfully");
        builderw.setTitle("THANK YOU");

// 3. Get the AlertDialog from create()
        AlertDialog dialog1 = builderw.create();
        dialog1.show();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("file.txt", Context.MODE_PRIVATE));

            for(int i=0;i<menu.size();i++)
            {
                outputStreamWriter.write(menu.get(i)+";" + "\n");
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
