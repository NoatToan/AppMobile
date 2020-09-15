package com.example.kiemdien;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class sendmail {
    public static void sendEmail(Context context, SINHVIEN sv_a) {
        Log.i("Send email", "");

        String[] TO = {sv_a.getGmail()};
        String[] CC = {sv_a.getGmail()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Thông báo cập nhật điểm danh");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Sinh viên "+sv_a.getHoten()+"\n, mã số sinh viên "+sv_a.getMasv()+"\n, các ngày có mặt : \n"+sv_a.getNgaydihoc());


            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Sent email...", "");


    }
}
