package com.example.kiemdien;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonWriter;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class readJSON {


    public static List<SINHVIEN> readCompanyJSONFile(Context context) throws IOException, JSONException {
        String jsonText;
        List<SINHVIEN> dssv=readJsonfile(context,R.raw.dssv);
        // Đọc nội dung text của file dssv.json
/*

             jsonText=readText(context, R.raw.dssv);

        // Đối tượng JSONObject gốc mô tả toàn bộ tài liệu JSON.
        JSONObject jsonRoot = new JSONObject(jsonText);

        // JSONObject jsonStudent = jsonRoot.getJSONObject("students");
        List<SINHVIEN> dssv = new ArrayList<SINHVIEN>();
        JSONArray danhSach = jsonRoot.getJSONArray("students");
        for (int i = 0; i < danhSach.length(); i++) {
            SINHVIEN sv = new SINHVIEN();
            sv.setMasv(danhSach.getJSONObject(i).getString("mssv"));
            sv.setHoten(danhSach.getJSONObject(i).getString("name"));
            sv.setNgaysinh(danhSach.getJSONObject(i).getString("ngaysinh"));
            sv.setAddress(danhSach.getJSONObject(i).getString("address"));
            sv.setHinhanh(danhSach.getJSONObject(i).getString("hinhanh"));
            sv.setNgaydihoc(danhSach.getJSONObject(i).getString("ngaydihoc"));
            dssv.add(sv);
        }

*/
        // Đọc file company.json và chuyển thành đối tượng java.


        //   JSONArray jsonArray = jsonRoot.getJSONArray("ngaydihoc");


        // for(int i=0;i < jsonArray.length();i++) {
        //     if(jsonArray.getString(i)!=null || jsonArray.getString(i)!="")
        //     ngaycomat[i] = jsonArray.getString(i);
        // }
        // sv.ngaydihoc=ngaycomat;
        return dssv;
    }

    public static List<SINHVIEN> readJSONFileInSmartPhone(Context context) throws IOException, JSONException {


        List<SINHVIEN> dssv=new ArrayList<SINHVIEN>();
        dssv=readJsonfile(context);
        // Đối tượng JSONObject gốc mô tả toàn bộ tài liệu JSON.
        //JSONObject jsonRoot = new JSONObject(sb.toString());

        // JSONObject jsonStudent = jsonRoot.getJSONObject("students");


        // Đọc file company.json và chuyển thành đối tượng java.


        //   JSONArray jsonArray = jsonRoot.getJSONArray("ngaydihoc");


        // for(int i=0;i < jsonArray.length();i++) {
        //     if(jsonArray.getString(i)!=null || jsonArray.getString(i)!="")
        //     ngaycomat[i] = jsonArray.getString(i);
        // }
        // sv.ngaydihoc=ngaycomat;
        return dssv;
    }


    public static void modifyCompanyJSONFile(Context context, SINHVIEN sv,List<SINHVIEN> dssvHienhanh) throws IOException, JSONException {

        for(SINHVIEN sv_a: dssvHienhanh)
        {
            if(sv_a.getMasv().equals(sv.getMasv()))
            {
                sv_a.setNgaydihoc(sv.getNgaydihoc());
            }
        }

        StringWriter output=new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(output);
        jsonWriter.beginArray();
        for(SINHVIEN sinhvien:dssvHienhanh) {
            jsonWriter.beginObject();
            jsonWriter.name("name").value(sinhvien.getHoten());
            jsonWriter.name("mssv").value(sinhvien.getMasv());
            jsonWriter.name("ngaydihoc").value(sinhvien.getNgaydihoc());
            jsonWriter.name("ngaysinh").value(sinhvien.getNgaysinh());
            jsonWriter.name("address").value(sinhvien.getAddress());
            jsonWriter.name("hinhanh").value(sinhvien.getHinhanh());
            jsonWriter.name("gmail").value(sinhvien.getGmail());
            // end root
            jsonWriter.endObject();
        }
        jsonWriter.endArray();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("dssv.json", Context.MODE_PRIVATE));
        outputStreamWriter.write(output.toString());
        outputStreamWriter.flush();
        outputStreamWriter.close();
        File fileJson = new File(context.getFilesDir()+"/dssv.json");
    }
    // Đọc nội dung text của một file nguồn.
    private static String readText(Context context, int resId) throws IOException {

        InputStream is = context.getResources().openRawResource(resId);

        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public static List<SINHVIEN> readJsonfile(Context context,int resID)
    {
        InputStream is = context.getResources().openRawResource(resID);
        List<SINHVIEN> dssv = new ArrayList<SINHVIEN>();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(is,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            reader.beginArray();

                while(reader.hasNext()){
                    reader.beginObject();
                    SINHVIEN sv=new SINHVIEN();
                        while(reader.hasNext()) {

                            String name = reader.nextName();
                            if (name.equals("name"))
                                sv.setHoten(reader.nextString());
                            else if (name.equals("mssv"))
                                sv.setMasv(reader.nextString());
                            else if (name.equals("ngaysinh"))
                                sv.setNgaysinh(reader.nextString());
                            else if (name.equals("address"))
                                sv.setAddress(reader.nextString());
                            else if (name.equals("hinhanh"))
                                sv.setHinhanh(reader.nextString());
                            else if (name.equals("ngaydihoc"))
                                sv.setNgaydihoc(reader.nextString());
                            else if(name.equals("gmail"))
                                sv.setGmail(reader.nextString());
                            else reader.skipValue();
                        }
                        dssv.add(sv);
                    reader.endObject();
                }

            reader.endArray();


            //reader.endObject();
            return dssv;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dssv;
    }

    public static List<SINHVIEN> readJsonfile(Context context)
    {
        //InputStream inputStream=context.getExternalFilesDir("dssv.json");
      //  try {
       //      inputStream = context.openFileInput("dssv.json");
       // } catch (FileNotFoundException e) {
       //     e.printStackTrace();
       // }
        //Make your FilePath and File
        String yourFilePath = context.getFilesDir() + "/" + "dssv.json";
        File yourFile = new File(yourFilePath);
        //Make an InputStream with your File in the constructor
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(yourFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<SINHVIEN>dssv = new ArrayList<SINHVIEN>();
        JsonReader reader = null;
        reader = new JsonReader(new InputStreamReader(inputStream));
        try {
            reader.beginArray();

            while(reader.hasNext()){
                reader.beginObject();
                SINHVIEN sv=new SINHVIEN();
                while(reader.hasNext()) {

                    String name = reader.nextName();
                    if (name.equals("name"))
                        sv.setHoten(reader.nextString());
                    else if (name.equals("mssv"))
                        sv.setMasv(reader.nextString());
                    else if (name.equals("ngaysinh"))
                        sv.setNgaysinh(reader.nextString());
                    else if (name.equals("address"))
                        sv.setAddress(reader.nextString());
                    else if (name.equals("hinhanh"))
                        sv.setHinhanh(reader.nextString());
                    else if (name.equals("ngaydihoc"))
                        sv.setNgaydihoc(reader.nextString());
                    else if(name.equals("gmail"))
                        sv.setGmail(reader.nextString());
                    else reader.skipValue();
                }
                dssv.add(sv);
                reader.endObject();
            }

            reader.endArray();


            //reader.endObject();
            return dssv;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dssv;
    }




}
