package com.evo.satisekrani.Model;

public class SiparisModelWeb {
    private String id;
    private String fatura_id;
    private String stok_id;
    private String ust_id;
    private String stok_kodu;
    private String barkod;
    private String stok_adi;
    private double miktar;
    private String birim_id;
    private String birim_adi;
    private double fiyat;
    private double iskonto;
    private double iskonto_fiyat;
    private double kdv;
    private double kdv_dahil;
    private double kdv_fiyat;
    private double tutar;
    private double sonTutar;

    private String para_id;
    private String depo_id;
    private String depo_adi;
    private String evrak;
    private String islem;
    private String ek_islem;
    private String user_id;
    private String yazdir;
    private String aciklama;
    private String simgesi;
    private String icerik;

    public SiparisModelWeb(String id, String fatura_id, String stok_id, String ust_id, String stok_kodu, String barkod, String stok_adi, double miktar, String birim_id, String birim_adi, double fiyat, double iskonto, double iskonto_fiyat, double kdv, double kdv_dahil, double kdv_fiyat, double tutar, double sonTutar, String para_id, String depo_id, String depo_adi, String evrak, String islem, String ek_islem, String user_id, String yazdir, String aciklama, String simgesi, String icerik) {
        this.id = id;
        this.fatura_id = fatura_id;
        this.stok_id = stok_id;
        this.ust_id = ust_id;
        this.stok_kodu = stok_kodu;
        this.barkod = barkod;
        this.stok_adi = stok_adi;
        this.miktar = miktar;
        this.birim_id = birim_id;
        this.birim_adi = birim_adi;
        this.fiyat = fiyat;
        this.iskonto = iskonto;
        this.iskonto_fiyat = iskonto_fiyat;
        this.kdv = kdv;
        this.kdv_dahil = kdv_dahil;
        this.kdv_fiyat = kdv_fiyat;
        this.tutar = tutar;
        this.sonTutar = sonTutar;
        this.para_id = para_id;
        this.depo_id = depo_id;
        this.depo_adi = depo_adi;
        this.evrak = evrak;
        this.islem = islem;
        this.ek_islem = ek_islem;
        this.user_id = user_id;
        this.yazdir = yazdir;
        this.aciklama = aciklama;
        this.simgesi = simgesi;
        this.icerik = icerik;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatura_id() {
        return fatura_id;
    }

    public void setFatura_id(String fatura_id) {
        this.fatura_id = fatura_id;
    }

    public String getStok_id() {
        return stok_id;
    }

    public void setStok_id(String stok_id) {
        this.stok_id = stok_id;
    }

    public String getUst_id() {
        return ust_id;
    }

    public void setUst_id(String ust_id) {
        this.ust_id = ust_id;
    }

    public String getStok_kodu() {
        return stok_kodu;
    }

    public void setStok_kodu(String stok_kodu) {
        this.stok_kodu = stok_kodu;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getStok_adi() {
        return stok_adi;
    }

    public void setStok_adi(String stok_adi) {
        this.stok_adi = stok_adi;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public String getBirim_id() {
        return birim_id;
    }

    public void setBirim_id(String birim_id) {
        this.birim_id = birim_id;
    }

    public String getBirim_adi() {
        return birim_adi;
    }

    public void setBirim_adi(String birim_adi) {
        this.birim_adi = birim_adi;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public double getIskonto() {
        return iskonto;
    }

    public void setIskonto(double iskonto) {
        this.iskonto = iskonto;
    }

    public double getIskonto_fiyat() {
        return iskonto_fiyat;
    }

    public void setIskonto_fiyat(double iskonto_fiyat) {
        this.iskonto_fiyat = iskonto_fiyat;
    }

    public double getKdv() {
        return kdv;
    }

    public void setKdv(double kdv) {
        this.kdv = kdv;
    }

    public double getKdv_dahil() {
        return kdv_dahil;
    }

    public void setKdv_dahil(double kdv_dahil) {
        this.kdv_dahil = kdv_dahil;
    }

    public double getKdv_fiyat() {
        return kdv_fiyat;
    }

    public void setKdv_fiyat(double kdv_fiyat) {
        this.kdv_fiyat = kdv_fiyat;
    }

    public double getTutar() {
        return tutar;
    }

    public void setTutar(double tutar) {
        this.tutar = tutar;
    }

    public double getSonTutar() {
        return sonTutar;
    }

    public void setSonTutar(double sonTutar) {
        this.sonTutar = sonTutar;
    }

    public String getPara_id() {
        return para_id;
    }

    public void setPara_id(String para_id) {
        this.para_id = para_id;
    }

    public String getDepo_id() {
        return depo_id;
    }

    public void setDepo_id(String depo_id) {
        this.depo_id = depo_id;
    }

    public String getDepo_adi() {
        return depo_adi;
    }

    public void setDepo_adi(String depo_adi) {
        this.depo_adi = depo_adi;
    }

    public String getEvrak() {
        return evrak;
    }

    public void setEvrak(String evrak) {
        this.evrak = evrak;
    }

    public String getIslem() {
        return islem;
    }

    public void setIslem(String islem) {
        this.islem = islem;
    }

    public String getEk_islem() {
        return ek_islem;
    }

    public void setEk_islem(String ek_islem) {
        this.ek_islem = ek_islem;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getYazdir() {
        return yazdir;
    }

    public void setYazdir(String yazdir) {
        this.yazdir = yazdir;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getSimgesi() {
        return simgesi;
    }

    public void setSimgesi(String simgesi) {
        this.simgesi = simgesi;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
    public static SiparisModelWeb kopyala(SiparisModelWeb modelWeb){
        return new SiparisModelWeb(modelWeb.id
                ,modelWeb.fatura_id
                ,modelWeb.stok_id
                ,modelWeb.ust_id
                ,modelWeb.stok_kodu
                ,modelWeb.barkod,
                modelWeb.stok_adi
                ,modelWeb.miktar
                ,modelWeb.birim_id
                ,modelWeb.birim_adi
                ,modelWeb.fiyat
                ,modelWeb.iskonto
                ,modelWeb.iskonto_fiyat
                ,modelWeb.kdv
                ,modelWeb.kdv_dahil
                ,modelWeb.kdv_fiyat
                ,modelWeb.tutar
                ,modelWeb.sonTutar
                ,modelWeb.para_id
                ,modelWeb.depo_id
                ,modelWeb.depo_adi
                ,modelWeb.evrak
                ,modelWeb.islem
                ,modelWeb.ek_islem
                ,modelWeb.user_id
                ,modelWeb.yazdir
                ,modelWeb.aciklama
                ,modelWeb.simgesi
                ,modelWeb.icerik);
    }
}
