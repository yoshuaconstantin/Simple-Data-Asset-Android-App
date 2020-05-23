package com.joshua.r0th.dataaset.ui.tambah_aset;

public class data_item {
    String Aid;
    String Bnama;
    int Cjumlah;
    String Dhargasatuan;
    String Ftempat;
    String Etotal;
    String Gstatus;

    public data_item(){

    }

    public data_item(String aid, String bnama, int cjumlah, String dhargasatuan, String ftempat, String etotal, String gstatus) {
        Aid = aid;
        Bnama = bnama;
        Cjumlah = cjumlah;
        Dhargasatuan = dhargasatuan;
        Etotal = etotal;
        Ftempat = ftempat;
        Gstatus = gstatus;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getBnama() {
        return Bnama;
    }

    public void setBnama(String bnama) {
        Bnama = bnama;
    }

    public int getCjumlah() {
        return Cjumlah;
    }

    public void setCjumlah(int cjumlah) {
        Cjumlah = cjumlah;
    }

    public String getDhargasatuan() {
        return Dhargasatuan;
    }

    public void setDhargasatuan(String dhargasatuan) {
        Dhargasatuan = dhargasatuan;
    }
    public String getEtotal() {
        return Etotal;
    }

    public void setEtotal(String etotal) {
        Etotal = etotal;
    }

    public String getFtempat() {
        return Ftempat;
    }

    public void setFtempat(String ftempat) {
        Ftempat = ftempat;
    }

    public String getGstatus() {
        return Gstatus;
    }

    public void setGstatus(String gstatus) {
        Gstatus = gstatus;
    }
}
