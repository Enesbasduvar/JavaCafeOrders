package com.evo.satisekrani.Model;

public class DepoModel {
    private String id;
    private String adi;
    private String aciklama;

    public DepoModel(String id, String adi, String aciklama) {
        this.id = id;
        this.adi = adi;
        this.aciklama = aciklama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
