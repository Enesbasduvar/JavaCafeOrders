package com.evo.satisekrani.Model;

public class UrunModel {

    private String birim;
    private String simgesi;
    private String stok_kodu;
    private String min_miktar;
    private String barkod;
    private String adi;
    private String marka;
    private String model;
    private String beden;
    private String renk;
    private String giren;
    private String cikan;
    private String miktar;
    private String kullanilacak_fiyat;
    private String satis_fiyati_1;
    private String satis_fiyati_2;
    private String satis_fiyati_3;
    private String id;
    private String birim_id;
    private String grup_id;

    public UrunModel(String birim, String simgesi, String stok_kodu, String min_miktar, String barkod, String adi, String marka, String model, String beden, String renk, String giren, String cikan, String miktar, String kullanilacak_fiyat, String satis_fiyati_1, String satis_fiyati_2, String satis_fiyati_3, String id, String birim_id, String grup_id) {
        this.birim = birim;
        this.simgesi = simgesi;
        this.stok_kodu = stok_kodu;
        this.min_miktar = min_miktar;
        this.barkod = barkod;
        this.adi = adi;
        this.marka = marka;
        this.model = model;
        this.beden = beden;
        this.renk = renk;
        this.giren = giren;
        this.cikan = cikan;
        this.miktar = miktar;
        this.kullanilacak_fiyat = kullanilacak_fiyat;
        this.satis_fiyati_1 = satis_fiyati_1;
        this.satis_fiyati_2 = satis_fiyati_2;
        this.satis_fiyati_3 = satis_fiyati_3;
        this.id = id;
        this.birim_id = birim_id;
        this.grup_id = grup_id;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getSimgesi() {
        return simgesi;
    }

    public void setSimgesi(String simgesi) {
        this.simgesi = simgesi;
    }

    public String getStok_kodu() {
        return stok_kodu;
    }

    public void setStok_kodu(String stok_kodu) {
        this.stok_kodu = stok_kodu;
    }

    public String getMin_miktar() {
        return min_miktar;
    }

    public void setMin_miktar(String min_miktar) {
        this.min_miktar = min_miktar;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBeden() {
        return beden;
    }

    public void setBeden(String beden) {
        this.beden = beden;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getGiren() {
        return giren;
    }

    public void setGiren(String giren) {
        this.giren = giren;
    }

    public String getCikan() {
        return cikan;
    }

    public void setCikan(String cikan) {
        this.cikan = cikan;
    }

    public String getMiktar() {
        return miktar;
    }

    public void setMiktar(String miktar) {
        this.miktar = miktar;
    }

    public String getKullanilacak_fiyat() {
        return kullanilacak_fiyat;
    }

    public void setKullanilacak_fiyat(String kullanilacak_fiyat) {
        this.kullanilacak_fiyat = kullanilacak_fiyat;
    }

    public String getSatis_fiyati_1() {
        return satis_fiyati_1;
    }

    public void setSatis_fiyati_1(String satis_fiyati_1) {
        this.satis_fiyati_1 = satis_fiyati_1;
    }

    public String getSatis_fiyati_2() {
        return satis_fiyati_2;
    }

    public void setSatis_fiyati_2(String satis_fiyati_2) {
        this.satis_fiyati_2 = satis_fiyati_2;
    }

    public String getSatis_fiyati_3() {
        return satis_fiyati_3;
    }

    public void setSatis_fiyati_3(String satis_fiyati_3) {
        this.satis_fiyati_3 = satis_fiyati_3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirim_id() {
        return birim_id;
    }

    public void setBirim_id(String birim_id) {
        this.birim_id = birim_id;
    }

    public String getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(String grup_id) {
        this.grup_id = grup_id;
    }

    public UrunModel urunuKopyala(UrunModel urunModel){
        return new UrunModel(urunModel.birim,urunModel.simgesi,urunModel.stok_kodu,urunModel.min_miktar,urunModel.barkod,urunModel.adi,urunModel.marka,urunModel.model,urunModel.beden,urunModel.renk,urunModel.giren,urunModel.cikan,urunModel.miktar,urunModel.kullanilacak_fiyat,urunModel.satis_fiyati_1,urunModel.satis_fiyati_2,urunModel.satis_fiyati_3,urunModel.id,urunModel.birim_id,urunModel.grup_id);
    }
}
