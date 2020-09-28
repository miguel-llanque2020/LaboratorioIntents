package com.example.laboratoriointents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5;
    Button btn1,btn2;
    ImageView img1;

    public static final int CAMERA_PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.tvdni);
        et2=(EditText)findViewById(R.id.tvnombre);
        et3=(EditText)findViewById(R.id.tvapellido);
        et4=(EditText)findViewById(R.id.tvtelefono);
        et5=(EditText)findViewById(R.id.tvlink);
        btn1=(Button)findViewById(R.id.btndjuntar);
        btn2=(Button)findViewById(R.id.btnenviar);
        img1=(ImageView)findViewById(R.id.image);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni=et1.getText().toString();
                String no=et2.getText().toString();
                String ape=et3.getText().toString();
                String tele=et4.getText().toString();
                String link=et5.getText().toString();
                Enviar(dni,no,ape,tele,link);
            }
        });

    }


    private void Enviar(String dni, String no, String ape, String tele,String link) {
        if (TextUtils.isEmpty(dni)){
            et1.setError("campo requerido");
        }
        else{
            Intent intent= new Intent(MainActivity.this,RecibidoActivity.class);
            img1.setDrawingCacheEnabled(true);
            img1.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) img1.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] path = baos.toByteArray();
            Bundle bundle = new Bundle();
            //  bundle.putString("keyalu",keyalumno);
            bundle.putString("d1",dni);
            bundle.putString("d2",no);
            bundle.putString("d3",ape);
            bundle.putString("d4",tele);
            bundle.putString("d5",link);
            bundle.putByteArray("img",path);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == CAMERA_PIC_REQUEST){
            if(resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                img1.setImageBitmap(bitmap);
            }
        }
    }

}