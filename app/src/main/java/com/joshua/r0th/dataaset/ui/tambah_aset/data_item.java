package com.joshua.r0th.dataaset.ui.tambah_aset;

public class data_item {
    String Anama;
    String Bjenis;
    String Cjumlah;

    public data_item(){

    }

    public data_item(String anama, String bjenis, String cjumlah) {
        Anama = anama;
        Bjenis = bjenis;
        Cjumlah = cjumlah;
    }

    public String getAnama() {
        return Anama;
    }

    public void setAnama(String anama) {
        Anama = anama;
    }

    public String getBjenis() {
        return Bjenis;
    }

    public void setBjenis(String bjenis) {
        Bjenis = bjenis;
    }

    public String getCjumlah() {
        return Cjumlah;
    }

    public void setCjumlah(String cjumlah) {
        Cjumlah = cjumlah;
    }
}
