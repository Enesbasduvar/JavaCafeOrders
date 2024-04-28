package com.evo.satisekrani.Model;

public class SiparisModel {

    private int siparisID;
    private int stok_id;
    private String urunAd;
    private double fiyat;
    private double sonFiyat;
    private double miktar;
//    private int bolum_id;
//    private int masa_id;
    private String saat;
    private String icerik;
    private String not;

    public SiparisModel( int stok_id, String urunAd, double fiyat, double sonFiyat, double miktar, String saat, String icerik, String not) {
        this.stok_id = stok_id;
        this.urunAd = urunAd;
        this.fiyat = fiyat;
        this.sonFiyat = sonFiyat;
        this.miktar = miktar;
        this.saat = saat;
        this.icerik = icerik;
        this.not = not;
    }

//    public SiparisModel(int siparisID, int stok_id, String urunAd, double fiyat, double miktar, int bolum_id, int masa_id, String saat, String not) {
//        this.siparisID = siparisID;
//        this.stok_id = stok_id;
//        this.urunAd = urunAd;
//        this.fiyat = fiyat;
//        this.miktar = miktar;
//        this.bolum_id = bolum_id;
//        this.masa_id = masa_id;
//        this.saat = saat;
//        this.not = not;
//    }

//    public int getSiparisID() {
//        return siparisID;
//    }

    public int getStok_id() {
        return stok_id;
    }

    public void setStok_id(int stok_id) {
        this.stok_id = stok_id;
    }

    public String getUrunAd() {
        return urunAd;
    }

    public void setUrunAd(String urunAd) {
        this.urunAd = urunAd;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

//    public int getBolum_id() {
//        return bolum_id;
//    }

//    public void setBolum_id(int bolum_id) {
//        this.bolum_id = bolum_id;
//    }

//    public int getMasa_id() {
//        return masa_id;
//    }
//
//    public void setMasa_id(int masa_id) {
//        this.masa_id = masa_id;
//    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

//    public void setSiparisID(int siparisID) {
//        this.siparisID = siparisID;
//    }


    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        if (!not.isEmpty()) {
            this.not = not;
        } else this.not = "Yok";
    }

    public double getSonFiyat() {
        return sonFiyat;
    }

    public void setSonFiyat(double sonFiyat) {
        this.sonFiyat = sonFiyat;
    }

    public static SiparisModel kopyala(SiparisModel model){
        return new SiparisModel(model.stok_id,model.urunAd,model.fiyat,model.sonFiyat,model.miktar,model.saat,model.icerik,model.not);
    }
}
