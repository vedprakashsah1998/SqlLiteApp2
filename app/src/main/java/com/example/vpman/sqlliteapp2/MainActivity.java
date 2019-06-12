package com.example.vpman.sqlliteapp2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText ed,ed1,ed2,ed3;
    Button bn,bn1,bn2,bn3;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.et);
        ed1=findViewById(R.id.et1);
        ed2=findViewById(R.id.et2);
        ed3=findViewById(R.id.et3);
        listView=findViewById(R.id.lv);
        sqLiteDatabase=openOrCreateDatabase("userdb", Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("create table if not exists emp(id number,name varchar(30),job varchar(30),salary number)");
        Toast.makeText(getApplicationContext(),"Suceefully Created table",Toast.LENGTH_LONG).show();
        bn=findViewById(R.id.b);
        bn1=findViewById(R.id.b1);
        bn2=findViewById(R.id.b2);
        bn3=findViewById(R.id.b3);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv=new ContentValues();
                cv.put("id",Integer.parseInt(ed.getText().toString()));
                cv.put("name",ed1.getText().toString());
                cv.put("job",ed2.getText().toString());
                cv.put("salary",Integer.parseInt(ed3.getText().toString()));
                long l=sqLiteDatabase.insert("emp",null,cv);
                if (l>0)
                {
                    Toast.makeText(getApplicationContext(),"Successfully inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed inserted",Toast.LENGTH_LONG).show();
                }
            }
        });

        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deleterows=sqLiteDatabase.delete("emp","id=?",new String[]{ed.getText().toString()});
                Toast.makeText(getApplicationContext(),deleterows+"Rows Deleted",Toast.LENGTH_LONG).show();
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Cursor c=sqLiteDatabase.query("emp",null,"id=?",new  String[]{ed.getText().toString()},null,null,null)  ;
                while (c.moveToNext())
                {
                    ed1.setText(c.getString(1));
                    ed2.setText(c.getString(2));
                    ed3.setText(c.getString(3));
                }
            }
        });
    }
}
