package com.evo.satisekrani.Model;

import com.android.volley.toolbox.StringRequest;

public class MasaModel {
    private int id;
    private String firma_adi;
    private String adi_soyadi;
    private String cari_kodu;
    private String grup_id;
    private String grup;
    private String hareket;

    public MasaModel(int id, String firma_adi, String adi_soyadi, String cari_kodu, String grup_id, String grup, String hareket) {
        this.id = id;
        this.firma_adi = firma_adi;
        this.adi_soyadi = adi_soyadi;
        this.cari_kodu = cari_kodu;
        this.grup_id = grup_id;
        this.grup = grup;
        this.hareket = hareket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirma_adi() {
        return firma_adi;
    }

    public void setFirma_adi(String firma_adi) {
        this.firma_adi = firma_adi;
    }

    public String getAdi_soyadi() {
        return adi_soyadi;
    }

    public void setAdi_soyadi(String adi_soyadi) {
        this.adi_soyadi = adi_soyadi;
    }

    public String getCari_kodu() {
        return cari_kodu;
    }

    public void setCari_kodu(String cari_kodu) {
        this.cari_kodu = cari_kodu;
    }

    public String getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(String grup_id) {
        this.grup_id = grup_id;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getHareket() {
        return hareket;
    }

    public void setHareket(String hareket) {
        this.hareket = hareket;
    }
}

