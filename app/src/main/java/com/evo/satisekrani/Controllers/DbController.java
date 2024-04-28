package com.evo.satisekrani.Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.evo.satisekrani.Activities.MainActivity;

import com.evo.satisekrani.Adapters.KategoriAdapter;
import com.evo.satisekrani.Adapters.MasaAdapter;
import com.evo.satisekrani.Adapters.SecenekAdapter;
import com.evo.satisekrani.Adapters.SiparisAdapter;
import com.evo.satisekrani.Adapters.UrunAdapter;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Model.DepoModel;
import com.evo.satisekrani.Model.KategoriModel;
import com.evo.satisekrani.Model.MasaModel;
import com.evo.satisekrani.Model.SecenekModel;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.SiparisModelWeb;
import com.evo.satisekrani.Model.UrunModel;
import com.evo.satisekrani.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;

public class DbController {
    static Context mCtx;
    static List<SiparisModelWeb> siparislerListesi;
    static GetDataListener dListener;

    private static double ucretToplam = 0;
    private int sayac = 0, sy = 0, sss = 0;
    private static final List<SiparisModelWeb> listAltSecenekler = new ArrayList<>();

    private static AlertDialog dialogScnk;


    static final ArrayList<MasaModel> arrayListMasalar = new ArrayList<>();

    public static void login(Context context, String username, String pass, MyLoginListener loginListener) {

        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    loginListener.onSuccessLogin(response, username, pass);


                } catch (JSONException e) {
                    loginListener.onErrorLogin(e.getMessage());
                }
            }

            @Override
            public void onErrorData(String error) {
                loginListener.onErrorLogin(error);
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", pass);
        new MySingleTone(context, dataListener, params, "bolum");
    }

    public static void siparisCek(final Context context, final String masa_id, final List<SiparisModelWeb> siparisler, final RecyclerView rcSiparisEski, SiparisAdapter adapterYeni) {
//        Paper.init(context);

        siparisler.clear();
        List<SiparisModelWeb> eskiSiparisler = new ArrayList<>();
        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                ucretToplam = 0;
                try {
                    listAltSecenekler.clear();
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("gecHareket");

                    JSONArray depoArray = object.getJSONArray("depo");
                    JSONObject objectDepo = depoArray.getJSONObject(0);
                    Common.depoModel = new DepoModel(objectDepo.getString("id")
                            , objectDepo.getString("adi")
                            , objectDepo.getString("aciklama"));
                    Log.d("SCEK", "onSuccessData: "+response);

                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String fatura_id = object.getString("fatura_id");
                        String stok_id = object.getString("stok_id");
                        String ust_id = object.getString("ust_id");
                        String stok_kodu = object.getString("stok_kodu");
                        String barkod = object.getString("barkod");
                        String stok_adi = object.getString("stok_adi");
                        String miktar = object.getString("miktar");
                        String birim_id = object.getString("birim_id");
                        String birim_adi = object.getString("birim_adi");
                        String fiyat = object.getString("fiyat");
                        String iskonto = object.getString("iskonto");
                        String iskonto_fiyat = object.getString("iskonto_fiyat");
                        String kdv = object.getString("kdv");
                        String kdv_dahil = object.getString("kdv_dahil");
                        String kdv_fiyat = object.getString("kdv_fiyat");
                        String tutar = object.getString("tutar");
                        String para_id = object.getString("para_id");
                        String depo_id = object.getString("depo_id");
                        String depo_adi = object.getString("depo_adi");
                        String evrak = object.getString("evrak");
                        String islem = object.getString("islem");
                        String ek_islem = object.getString("ek_islem");
                        String user_id = object.getString("user_id");
                        String yazdir = object.getString("yazdir");
                        String aciklama = object.getString("aciklama");
                        String simgesi = object.getString("simgesi");

                        SiparisModelWeb spmdl = new SiparisModelWeb(id
                                , fatura_id
                                , stok_id
                                , ust_id
                                , stok_kodu
                                , barkod
                                , stok_adi
                                , Double.parseDouble(miktar)
                                , birim_id
                                , birim_adi
                                , Double.parseDouble(fiyat)
                                , Double.parseDouble(iskonto)
                                , Double.parseDouble(iskonto_fiyat)
                                , Double.parseDouble(kdv)
                                , Double.parseDouble(kdv_dahil)
                                , Double.parseDouble(kdv_fiyat)
                                , Double.parseDouble(tutar)
                                , Double.parseDouble(tutar)
                                , para_id
                                , depo_id
                                , depo_adi
                                , evrak
                                , islem
                                , ek_islem
                                , user_id
                                , yazdir
                                , aciklama
                                , simgesi
                                , ""
                        );
                        if (!ust_id.equals("0")){
                            listAltSecenekler.add(spmdl);
                        }
                        else if (yazdir.equals("0")) {
                            siparisler.add(spmdl);
                            ucretToplam += Double.parseDouble(tutar);

                        } else {
                            eskiSiparisler.add(spmdl);
                        }
                    }
                    Common.eskiSiparisler.clear();
                    Common.eskiSiparisler.addAll(eskiSiparisler);
                    if (Common.spSecili == Common.spMutfak) {
                        rcSiparisEski.setAdapter(new SiparisAdapter(context, eskiSiparisler, Common.typeEskiSp));

                        for (SiparisModelWeb siparis : eskiSiparisler){
                            String icerk = "";
                            for (SiparisModelWeb altSecenek : listAltSecenekler){
                                if (altSecenek.getUst_id().equals(siparis.getId())){
                                    icerk += altSecenek.getStok_adi()+" (+"+altSecenek.getFiyat()+" TL)";

                                }
                            }
                            siparis.setIcerik(icerk.trim());
                        }

                    } else {
                        for (SiparisModelWeb siparis : siparisler){
                            String icerk = "";
                            for (SiparisModelWeb altSecenek : listAltSecenekler){
                                if (altSecenek.getUst_id().equals(siparis.getId())){
                                    icerk += altSecenek.getStok_adi()+" (+"+altSecenek.getFiyat()+" TL)\n";
                                    ucretToplam+=altSecenek.getFiyat();
                                }
                            }
                            siparis.setIcerik(icerk.trim());
                        }
                        rcSiparisEski.setAdapter(adapterYeni);
                        ((MainActivity) context).ucretEkle(ucretToplam);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "İŞLEM HATASI : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorData(String error) {
                ucretToplam = 0;
                Toast.makeText(context, "İŞLEM BAŞARISIZ : " + error, Toast.LENGTH_SHORT).show();
                Log.d("SSS", "onErrorData: " + error);

            }
        };
        Map<String, String> params = new HashMap<>();
//                params.put("bolum_id", String.valueOf());
        params.put("cari_id", String.valueOf(masa_id));
        new MySingleTone(context, dataListener, params, "satisGecHareketSon");

    }

    public static void siparisGonderWeb(Context context, SiparisModelWeb siparis, List<SecenekModel> secenekList) {

        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);

//                    JSONArray array = object.getJSONArray("sonuc");
                    String id = object.getString("id");
                    siparis.setId(id);

                    if (secenekList.size() > 0) {
                        //SECENEK GÖSTER
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View view = LayoutInflater.from(context).inflate(R.layout.secenek_view, null);

                        RecyclerView rcSecenek = view.findViewById(R.id.rcSecenekler);
                        rcSecenek.setLayoutManager(new GridLayoutManager(context, 4));
                        rcSecenek.setHasFixedSize(true);

                        Button btnTamam = view.findViewById(R.id.btnSecenekKaydet);
                        Button btnIptal = view.findViewById(R.id.btnSecenekIptal);
                        rcSecenek.setAdapter(new SecenekAdapter(context, secenekList));

                        builder.setView(view);

                        btnTamam.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StringBuilder icerik = new StringBuilder();
                                double tFyt = siparis.getSonTutar();
                                if (secenekList.size() > 0) {
                                    for (SecenekModel mdl : secenekList) {
                                        double fyt = Double.parseDouble(mdl.getFiyat());
//                                    Common.seciliSecenekler.clear();
                                        if (mdl.isSecili()) {
                                            ekleSiparisSecenek(context, siparis, mdl);
                                            icerik.append(mdl.getAdi()).append(fyt > 0 ? "(+" + fyt + " TL)\n" : "");
                                            tFyt += fyt;
//                                        Common.seciliSecenekler.add(mdl);
                                        }

                                    }

                                    Toast.makeText(context, "" + icerik.toString().trim() + "\nFiyat:" + (tFyt - siparis.getFiyat()), Toast.LENGTH_SHORT).show();

                                    siparis.setSonTutar(tFyt);
                                    siparis.setUst_id(id);
                                    siparis.setIcerik(icerik.toString().trim());

                                    ((MainActivity) context).siparisEkle(siparis, true, tFyt);
                                }

                                dialogScnk.dismiss();
                            }
                        });
                        btnIptal.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogScnk.dismiss();
                            }
                        });
                        dialogScnk = builder.show();
                    }


                    ((MainActivity) context).siparisEkle(siparis, false, 0);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "İŞLEM HATASI: " + response + " \n\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(context, "İŞLEM BAŞARISIZ: " + error, Toast.LENGTH_SHORT).show();
            }
        };
        Map<String, String> params = new HashMap<>();

        params.put("cari_id", String.valueOf(Common.seciliMasaID));
        params.put("kdv_dahil", String.valueOf(siparis.getKdv_dahil()));
        params.put("para_id", "1");
        params.put("id", siparis.getStok_id());
        params.put("kur", "1");

        new MySingleTone(context, dataListener, params,  "satisGecHareketEkle");

    }

    public static void siparisSil(Context context, String siparisID, String fatura_id, GetDataListener listener) {
        Map<String, String> params = new HashMap<>();

        if (siparisID != null) {
            params.put(":id", siparisID);
            new MySingleTone(context, listener, params,  "gecHareketSil");

        } else if (fatura_id != null) {
            params.put(":id", fatura_id);
            params.put("cari_id", String.valueOf(Common.seciliMasaID));
            params.put("islemTuru", "satis");
            new MySingleTone(context, listener, params,  "gecHareketSilTumu");
        }
    }

    public static void updateMiktar(Context context, String id, String miktar, GetDataListener dataListener) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("miktar", miktar);
        new MySingleTone(context, dataListener, params, "updateGecHareketRun");
    }
    public static void updateNot(Context context, GetDataListener listener ,String id, String not){
        Map<String, String> params = new HashMap<>();
        params.put("id",id);
        params.put("aciklama",not);

        new MySingleTone(context,listener,params,"updateGecHareketAciklamaRun");
    }
    public static void masaCek(final Context context, final int bolumId, final RecyclerView mRecyc, String user, String pass) {
        arrayListMasalar.clear();
        Common.showSpotDialog(context);

        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("masa");
                    Log.d("DENEME", "masaCek: " + response);
                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String firma_adi = object.getString("firma_adi");
                        String adi_soyadi = object.getString("adi_soyadi");
                        String cari_kodu = object.getString("cari_kodu");
                        String grup_id = object.getString("grup_id");
                        String grup = object.getString("grup");
                        String hareket = object.getString("hareket");
                        //Masa duruum değişmezse notfy calısmamalıdır normalde

                        arrayListMasalar.add(new MasaModel(Integer.parseInt(id)
                                , firma_adi
                                , adi_soyadi
                                , cari_kodu
                                , grup_id
                                , grup
                                , hareket));

                    }

                    mRecyc.setAdapter(new MasaAdapter(context, arrayListMasalar));
                } catch (JSONException e) {
                    arrayListMasalar.clear();

//                adapterMasalar.notifyDataSetChanged();
                    e.printStackTrace();
                    Toast.makeText(context, "Masa bulunamadı" + response, Toast.LENGTH_LONG).show();
                }
                Common.dialog.dismiss();
            }

            @Override
            public void onErrorData(String error) {
                arrayListMasalar.clear();
                mRecyc.setAdapter(new MasaAdapter(context, arrayListMasalar));
                Common.dialog.dismiss();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("grup_id", String.valueOf(bolumId));
        params.put("username", user);
        params.put("password", pass);
        new MySingleTone(context, dataListener, params,  "masaGetir");
    }

    public static void kategoriCek(final Context context, final RecyclerView rcKategoriler, List<KategoriModel> listKategori) {
        listKategori.clear();

        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("stokGrupList");

                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String adi = object.getString("adi");
                        String aciklama = object.getString("aciklama");
                        //int yazici_id = object.getInt("yazici_id");
                        listKategori.add(new KategoriModel(id, adi, aciklama));
                    }

                    rcKategoriler.setAdapter(new KategoriAdapter(context, listKategori));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "HATALI İŞLEM : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(context, "BAŞARISIZ İŞLEM: " + error, Toast.LENGTH_SHORT).show();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("username", Common.usr);
        params.put("password", Common.pss);

        new MySingleTone(context, dataListener, params,  "stokGrupList");

    }

    public static void urunCek(final Context context, final RecyclerView rcUrunler, final String group_id, List<UrunModel> listUrun) {
        Common.showSpotDialog(context);

        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("stokList");

                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);

                        String grup_id = object.getString("grup_id");
                        if (!grup_id.equals(group_id)) {
                            continue;
                        }

                        String birim = object.getString("birim");
                        String simgesi = object.getString("simgesi");
                        String stok_kodu = object.getString("stok_kodu");
                        String min_miktar = object.getString("min_miktar");
                        String barkod = object.getString("barkod");
                        String adi = object.getString("adi");
                        String marka = object.getString("marka");
                        String model = object.getString("model");
                        String beden = object.getString("beden");
                        String renk = object.getString("renk");
                        String giren = object.getString("giren");
                        String cikan = object.getString("cikan");
                        String miktar = object.getString("miktar");
                        String kullanilacak_fiyat = object.getString("kullanilacak_fiyat");

                        String satis_fiyati_1 = object.getString("satis_fiyati_1");
                        String satis_fiyati_2 = object.getString("satis_fiyati_2");
                        String satis_fiyati_3 = object.getString("satis_fiyati_3");
                        String id = object.getString("id");
                        String birim_id = object.getString("birim_id");

                        listUrun.add(new UrunModel(birim, simgesi, stok_kodu, min_miktar, barkod, adi, marka, model, beden, renk, giren, cikan, miktar, kullanilacak_fiyat, satis_fiyati_1, satis_fiyati_2, satis_fiyati_3, id, birim_id, grup_id));
                    }

                    rcUrunler.setAdapter(new UrunAdapter(context, listUrun));

                } catch (JSONException e) {
                    Toast.makeText(context, "HATALI İŞLEM : " + e.getMessage() + " " + response, Toast.LENGTH_LONG).show();
                }
                Common.dialog.dismiss();
            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(context, "BAŞARISIZ İŞLEM: " + error, Toast.LENGTH_SHORT).show();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("username", Common.usr);
        params.put("password", Common.pss);

        new MySingleTone(context, dataListener, params, "stokList");
    }

    public static void stokSecenekAra(Context context, UrunModel urun) {
        List<SecenekModel> secenekList = new ArrayList<>();
        secenekList.clear();

        Common.showSpotDialog(context);
        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("stokSecenek");
                    for (int i = 0; i < array.length(); i++) {
                        object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String stok_id = object.getString("stok_id");
                        String grup_kodu = object.getString("grup_kodu");// 0 ise radio, 1 ise checkbox
                        String adi = object.getString("adi");
                        String fiyat = object.getString("fiyat");
                        String secenek = object.getString("secenek");
                        String stok = object.getString("stok");

                        secenekList.add(new SecenekModel(id, stok_id, grup_kodu, adi, fiyat, secenek, stok, false));

                    }
                    ekleSiparis(context, urun, "", secenekList);
                } catch (JSONException e) {
//                    Toast.makeText(context, "HATALI İŞLEM: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                Common.dialog.dismiss();
            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(context, "BAŞARISIZ İŞLEM: " + error, Toast.LENGTH_SHORT).show();
                Common.dialog.dismiss();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("username", Common.usr);
        params.put("password", Common.pss);
        params.put("id", urun.getId());
        params.put("hareket_id", "178");

        new MySingleTone(context, dataListener, params,  "stokSecenekAra");
    }

    private static void ekleSiparis(Context context, UrunModel urun, String icerik, List<SecenekModel> secenekList) {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String saat = sdf.format(new Date());

        SiparisModelWeb siparis = new SiparisModelWeb(""
                , ""
                , urun.getId() //stok id
                , ""
                , urun.getStok_kodu()
                , urun.getBarkod()
                , urun.getAdi()
                , 1.00
                , urun.getBirim_id()
                , urun.getBirim()
                , Double.parseDouble(urun.getSatis_fiyati_1())
                , 0
                , 0
                , 1
                , 1
                , 1
                , Double.parseDouble(urun.getSatis_fiyati_1())
                , Double.parseDouble(urun.getSatis_fiyati_1())
                , "1"
                , Common.depoModel.getId()
                , Common.depoModel.getAdi()
                , ""
                , ""
                , ""
                , ""
                , "0"
                , ""
                , urun.getSimgesi()
                , icerik
        );
        siparisGonderWeb(context, siparis, secenekList);
    }

    public static void mutfagaGonder(Context context, List<SiparisModelWeb> siparisler, GetDataListener dataListener) {
        mCtx = context;
        siparislerListesi = siparisler;
        dListener = dataListener;
        if (siparislerListesi.size()>0) {
            Map<String, String > params = new HashMap<>();
            params.put("cari_id", String.valueOf(Common.seciliMasaID));

            new MySingleTone(context,dataListener,params,"satisMutfak");
        }
        else Toast.makeText(context, "Boş sipariş gönderilemez", Toast.LENGTH_SHORT).show();
        
//        Map<String, String> params = new HashMap<>();
//        params.put("faturad_id", fatura_id);
//
//        new MySingleTone(context, dataListener, params, Common.link + "/mutfaGonder");
    }

    //// Sockete gönder
    //// Sockete gönder
    static class BackTask  extends AsyncTask <String, Void, Void>{

        static Socket socket;
        static   PrintWriter printWriter;
        static  String ip = "192.168.1.37";
        @Override
        protected Void doInBackground(String... strings) {
            try {
                socket = new Socket(ip,6000);
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.write(Common.seciliMasaAdi+" Siparişleri:,");
                for (SiparisModelWeb sm : siparislerListesi){
                    printWriter.write(sm.getStok_adi()+"  "+sm.getMiktar()+"  Adet/Porsiyon,");

                }
                printWriter.flush();
                printWriter.close();
//                Log.d("YAZMA", "Gönderildi");
                dListener.onSuccessData("Gönderildi");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mCtx, "Gönderildi", Toast.LENGTH_LONG).show();

                    }
                });
            } catch (IOException e) {
//                e.printStackTrace();
//                Log.d("YAZMA", "doInBackground: "+e.getMessage());
                dListener.onErrorData("Yazdırılmadı: "+e.getMessage());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mCtx, "Yazıcıya Gönderilemedi: "+e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }

            return null;
        }
    }
    private void masaKontrol(final Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Paper.book().read("url").toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject object = jsonArray.getJSONObject(0);
                    boolean ok = object.getBoolean("hareketvar");
                    if (ok) {
                        //  Toast.makeText(context, "Masa Ekranı Yenilendi", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "CATCH: " + response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    Toast.makeText(context, "Sunucuya Erişilemedi, Ip adresinizi kontrol edin ", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context, "Ip adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(context, "Ip adresinizi veya İnternet bağlantınızı kontrol edin", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(context, "Bağlantı zaman aşımına uğradı. Ip adresinizi kontrol edin", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(context, "Hata: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("masakontrolekle", "masakontrolekle");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void ekleSiparisSecenek(Context context, SiparisModelWeb siparisModelWeb, SecenekModel secenek) {
        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                // dönen id ne işimize yarayacak
                try {
                    JSONObject object = new JSONObject(response);
                    String secenek_id = object.getString("secenek_id");
//                    secenek.setId(secenek_id);
                    Toast.makeText(context, "Eklenen Seçenek id: " + secenek_id, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, e.getMessage() + "\n Sonuc: " + response, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(context, "İşlem Başarısız. " + error, Toast.LENGTH_SHORT).show();
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("id", secenek.getId());
        params.put("cari_id", String.valueOf(Common.seciliMasaID));
        params.put("para_id", siparisModelWeb.getPara_id());
        params.put("ust_id", siparisModelWeb.getId());

        new MySingleTone(context, dataListener, params,   "satisGecAltHareketEkle");

    }

    public void toplamCek(String fatura_id) {

    }

    public static void masaTasi(Context context, String yeniMasaID, GetDataListener dataListener) {
        Map<String, String> params = new HashMap<>();
        params.put("eskiMasa", String.valueOf(Common.seciliMasaID));
        params.put("yeniMasa", yeniMasaID);

        new MySingleTone(context, dataListener, params,  "masaAktarRun");

    }

    public static void siparisTasi(Context context, GetDataListener lstnr,String yeniMasaID, List<SiparisModelWeb> aktarilacaklar) {

//        Map<String, JSONObject> params = new HashMap<>();
        JSONArray array = new JSONArray();
        for (SiparisModelWeb sm : aktarilacaklar){
            array.put(sm.getId());
        }
        JSONObject object = new JSONObject();
        try {
            object.put("siparis",array);
//            JSONArray jArray = new JSONArray(object);
//            params.put("siparis",object);
            new MySingleTone().sendJsonArray(context,lstnr, object,"siparisAktarRun",yeniMasaID);

        } catch (JSONException e) {
            e.printStackTrace();
            lstnr.onErrorData(e.getMessage());
            Toast.makeText(context, "HATA: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
