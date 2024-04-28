package com.evo.satisekrani.Model;

public class BolumModel {

    private int id;
    private String adi;
    private String aciklama;
    private String durum;
    private String aktarim;
    private boolean secili;

    public BolumModel(int id, String adi, String aciklama, String durum, boolean secili) {
        this.id = id;
        this.adi = adi;
        this.aciklama = aciklama;
        this.durum = durum;
        this.aktarim = aktarim;
        this.secili = secili;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getAktarim() {
        return aktarim;
    }

    public void setAktarim(String aktarim) {
        this.aktarim = aktarim;
    }

    public boolean isSecili() {
        return secili;
    }

    public void setSecili(boolean secili) {
        this.secili = secili;
    }

}
