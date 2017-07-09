package com.example.pulkit.barcodescanner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity{
    private ZXingScannerView zXingScannerView;

    public static final String TAG = "PERMS";
    public static final int PERM_REQ_CODE = 111;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //ZXingScannerView
        zXingScannerView=new ZXingScannerView(this);
        zXingScannerView.setResultHandler(new ZXingScannerResultHandler());
        setContentView(zXingScannerView);
        zXingScannerView.startCamera();

        int perm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if (perm == PackageManager.PERMISSION_DENIED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,  Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Give the damn permission", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, PERM_REQ_CODE);
        }


    }

//    public void scan(View view){
//        zXingScannerView=new ZXingScannerView(this);
//        zXingScannerView.setResultHandler(new ZXingScannerResultHandler());
//        setContentView(zXingScannerView);
//        zXingScannerView.startCamera();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler{

        @Override
        public void handleResult(Result result) {
            String resultcode = result.getText();
            Toast.makeText(MainActivity.this,resultcode, Toast.LENGTH_LONG).show();
           // setContentView(R.layout.activity_main);
            ///tv.setText(resultcode);
            zXingScannerView.stopCamera();
        }

    }
}
