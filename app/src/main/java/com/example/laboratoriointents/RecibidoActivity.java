package com.example.laboratoriointents;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class RecibidoActivity extends AppCompatActivity {


    ImageView img2;
    String d1,d2,d3,d4,d5;
    TextView t1,t2,t3,t4,t5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibido);

        img2=(ImageView)findViewById(R.id.img2);
        t1=(TextView)findViewById(R.id.tv1);
        t2=(TextView)findViewById(R.id.tv2);
        t3=(TextView)findViewById(R.id.tv3);
        t4=(TextView)findViewById(R.id.tv4);
        t5=(TextView)findViewById(R.id.tv5);
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        // getIntent().getStringExtra("tipotarea");
        t1.setText(getIntent().getStringExtra("d1"));
        t2.setText(getIntent().getStringExtra("d2"));
        t3.setText(getIntent().getStringExtra("d3"));
        t4.setText(getIntent().getStringExtra("d4"));
        t5.setText(getIntent().getStringExtra("d5"));
        Log.e("da",getIntent().getStringExtra("d1"));

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img2.setImageBitmap(bmp);
    }
}