package com.example.kiemdien;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class xuLyThongTin {
    public static SINHVIEN xulyHienthi(String masosv, List<SINHVIEN> danhsachsv)
    {

            for(SINHVIEN sinhvien_a :danhsachsv)
                if(masosv.equals(sinhvien_a.getMasv())) {
                    //Hiển thị
                    MainActivity.sinhvienhienhanh=sinhvien_a;
                    return sinhvien_a;
                }
                return null;
    }


    public static boolean diemDanh(Context context, String masv, List<SINHVIEN> danhsachsinhvien)
    {
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        boolean diemdanh=false;
        for(SINHVIEN sv:danhsachsinhvien)
        {
            if(masv.equals((sv.getMasv()))) diemdanh = true;
        }
        String ngaydiemdanh=formatter.format(date);
        if(diemdanh) {
            SINHVIEN sinhvien_a_diemDanh = xulyHienthi(masv, danhsachsinhvien);
            if (!sinhvien_a_diemDanh.getNgaydihoc().contains(ngaydiemdanh))
                sinhvien_a_diemDanh.setNgaydihoc(sinhvien_a_diemDanh.getNgaydihoc() + "," + ngaydiemdanh);

            capNhat(context, sinhvien_a_diemDanh, danhsachsinhvien);
        }
        if(diemdanh)
        return true;
        else return false;
    }
    public static void capNhat(Context context,SINHVIEN sv_a,List<SINHVIEN> danhsachsinhvien)
    {
        for(SINHVIEN sv:danhsachsinhvien)
        {
            if(sv.getMasv()==sv_a.getMasv())
                sv=sv_a;
        }
        try {
            readJSON.modifyCompanyJSONFile(context,sv_a,danhsachsinhvien);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
