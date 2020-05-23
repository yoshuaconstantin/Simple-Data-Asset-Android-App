package com.joshua.r0th.dataaset.ui.request;

import com.google.firebase.database.Exclude;

public class data_item_requser {
    String Anamainput;
    String BnamaAset;
    String Cjumlah;
    String Dpenempatan;
    String Estatusawal;
    private String mkey ;

    public data_item_requser(){

    }

    public data_item_requser(String anamainput, String bnamaAset, String cjumlah, String dpenempatan, String estatusawal) {
        Anamainput = anamainput;
        BnamaAset = bnamaAset;
        Cjumlah = cjumlah;
        Dpenempatan = dpenempatan;
        Estatusawal = estatusawal;
    }

    public String getAnamainput() {
        return Anamainput;
    }

    public void setAnamainput(String anamainput) {
        Anamainput = anamainput;
    }

    public String getBnamaAset() {
        return BnamaAset;
    }

    public void setBnamaAset(String bnamaAset) {
        BnamaAset = bnamaAset;
    }

    public String getCjumlah() {
        return Cjumlah;
    }

    public void setCjumlah(String cjumlah) {
        Cjumlah = cjumlah;
    }

    public String getDpenempatan() {
        return Dpenempatan;
    }

    public void setDpenempatan(String dpenempatan) {
        Dpenempatan = dpenempatan;
    }

    public String getEstatusawal() {
        return Estatusawal;
    }

    public void setEstatusawal(String estatusawal) {
        Estatusawal = estatusawal;
    }

    @Exclude
    public String getMkey() {
        return mkey;
    }
    @Exclude
    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

}
