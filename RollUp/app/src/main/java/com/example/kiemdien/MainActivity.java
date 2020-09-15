package com.example.kiemdien;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private SurfaceView cameraView;
private TextView barcodeInfo;
private BarcodeDetector barcodeDetector;
private CameraSource cameraSource;
private String masv;
private String mama="";
private EditText masvedit;
private ImageView hinhanh;
private ListView listviewtemp;
public static SINHVIEN sinhvienhienhanh;
public static List<SINHVIEN> dssvMain= new ArrayList<SINHVIEN>();
final int RequestCameraPermissionID=1001;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File fileJson = new File(this.getFilesDir()+ "/dssv.json");
            if( !fileJson.exists() ) {
                try {
                     dssvMain = readJSON.readCompanyJSONFile(this);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             }
            else {
                try {
                    dssvMain = readJSON.readJSONFileInSmartPhone(this);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView=(SurfaceView)findViewById(R.id.camera_view);
        barcodeInfo=(TextView) findViewById(R.id.code_info);

        //get mssv tu chuviettay






        findViewById(R.id.btnTextReg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NhanDienChu.class);
                startActivity(i);
            }
        });


        //Show lên màn hình thông tin

        masvedit= (EditText) findViewById(R.id.editText);

        hinhanh=(ImageView) findViewById(R.id.imageView);
        listviewtemp=(ListView)findViewById(R.id.listView_thongtin) ;
        findViewById(R.id.button_Lay_thong_tin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    masv = masvedit.getText().toString();
                    masv = masv.trim();
                    if
                    (xuLyThongTin.diemDanh(MainActivity.this, masv, dssvMain)) {
                        xuLyThongTin.diemDanh(MainActivity.this, masv, dssvMain);
                        showThongTin(masv);
                        Toast toast=Toast.makeText(MainActivity.this,"Đã điểm danh ngày hôm nay",Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    else {
                        Toast toast=Toast.makeText(MainActivity.this,"Không tồn tại mã sinh viên",Toast.LENGTH_SHORT);
                    toast.show();
                    }

            }
        });
        listviewtemp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//gmail =4
                Log.v("long clicked","pos: " + position);
                if(position==0) sendmail.sendEmail(MainActivity.this,sinhvienhienhanh);
                return false;
            }
        });
        //Sự kiện khi nhấp vào edittext để gõ gì đó

        masvedit.addTextChangedListener(new TextWatcher(){

                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                            }

                                            @Override
                                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                                            }

                                            @Override
                                            public void afterTextChanged(Editable s) {

                                            }
                                        }
        );


        barcodeDetector =new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource=new CameraSource
                .Builder(this,barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this
                                ,new String[]{Manifest.permission.CAMERA}
                                ,RequestCameraPermissionID);
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
            final SparseArray<Barcode> barcodes=detections.getDetectedItems();
            if( barcodes.size() != 0 ){
                barcodeInfo.post(new Runnable() {
                    @Override
                    public void run() {
                        //barcodeInfo.setText(barcodes.valueAt(0).displayValue);
                        //masv=((TextView) findViewById(R.id.code_info)).getText().toString();
                        masv=barcodes.valueAt(0).displayValue;
                        xuLyThongTin.diemDanh(MainActivity.this,masv,dssvMain);

                        showThongTin(masv);
                        Toast toast=Toast.makeText(MainActivity.this,"Đã điểm danh ngày hôm nay",Toast.LENGTH_SHORT);
                        toast.show();


                    }
                });
            }
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Intent ib=this.getIntent();
        if(ib.hasExtra("masv"))
        {
            mama=ib.getStringExtra("masv");
            Log.d("nhan","nhan dc:"+ib.getStringExtra("masv"));
        }
        masvedit.setText(mama);
    }

    public void showThongTin(String masv)
        {
            if (masv != null || masv != "") {
                //Show lên màn hình thông tin

                SINHVIEN sv_a = new SINHVIEN();
                sv_a = xuLyThongTin.xulyHienthi(masv, dssvMain);
               // hovaten.setText(sv_a.getHoten());
               // ngaysinh.setText(sv_a.getNgaysinh());
               // diachi.setText(sv_a.getAddress());
                 List<String> thongtin=new ArrayList<String>();
                 //Them ngay co mat
                thongtin.add("Giữ để gửi mail");
                thongtin.add("Mã số sinh viên: "+sv_a.getMasv());
                thongtin.add("Họ tên: "+sv_a.getHoten());
                thongtin.add("Ngày sinh: "+sv_a.getNgaysinh());
                thongtin.add("Địa chỉ: "+sv_a.getAddress());
                thongtin.add("Gmail: "+sv_a.getGmail());
                thongtin.add("Các ngày có mặt:");
                String[] danhsachngaydiemdanh=sv_a.getNgaydihoc().split(",");
                    for(int a=0;a<danhsachngaydiemdanh.length;a++)
                        thongtin.add(danhsachngaydiemdanh[a]);
                 ArrayAdapter<String> arrthongtin = new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_list_item_1,thongtin);
                 listviewtemp.setAdapter(arrthongtin);
                Context c = getApplicationContext();
                int id = c.getResources().getIdentifier("mipmap/img" + sv_a.getHinhanh(), null, c.getPackageName());
                hinhanh.setImageResource(id);
            }


        }




}
