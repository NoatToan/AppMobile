package com.example.kiemdien;

public class SINHVIEN {
    private String hoten;
    private String masv;
    private String ngaysinh;
    private String address;
    private String ngaydihoc;
    private String hinhanh;
    private String gmail;
    public  String getGmail(){return gmail;}
    public void setGmail(String gmail){this.gmail=gmail;}
    public String getHinhanh(){
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }
    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setNgaydihoc(String ngaydihoc) {this.ngaydihoc=ngaydihoc;}

    public String getNgaydihoc() {     return ngaydihoc; }
}
