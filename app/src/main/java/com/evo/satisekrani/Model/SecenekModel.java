package com.evo.satisekrani.Model;

public class SecenekModel {
    private String id;
    private String stok_id;
    private String grup_kodu;
    private String adi;
    private String fiyat;
    private String secenek;
    private String stok;
    private boolean secili;

    public SecenekModel(String id, String stok_id, String grup_kodu, String adi, String fiyat, String secenek, String stok, boolean secili) {
        this.id = id;
        this.stok_id = stok_id;
        this.grup_kodu = grup_kodu;
        this.adi = adi;
        this.fiyat = fiyat;
        this.secenek = secenek;
        this.stok = stok;
        this.secili = secili;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStok_id() {
        return stok_id;
    }

    public String getGrup_kodu() {
        return grup_kodu;
    }

    public String getAdi() {
        return adi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public String getSecenek() {
        return secenek;
    }

    public String getStok() {
        return stok;
    }

    public boolean isSecili() {
        return secili;
    }

    public void setSecili(boolean secili) {
        this.secili = secili;
    }
}
