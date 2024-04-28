package com.evo.satisekrani.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.evo.satisekrani.Adapters.BolumAdapter;
import com.evo.satisekrani.Common.Ayarlar;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Controllers.DbController;
import com.evo.satisekrani.Controllers.GetDataListener;
import com.evo.satisekrani.Controllers.MyLoginListener;
import com.evo.satisekrani.Model.BolumModel;
import com.evo.satisekrani.Model.MasaModel;
import com.evo.satisekrani.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import io.paperdb.Paper;

public class MasaActivity extends AppCompatActivity {
    //    public static CountDownTimer countTimer;
    private RecyclerView rcMasalar;
    RecyclerView.LayoutManager layoutManagerMasalar;

    public TextView txBaslik;
    private TextView txMasaBaslik;
    Button btnCikis;
    AlertDialog dlg;

    ArrayList<MasaModel> masalar = new ArrayList<>();

    private RecyclerView rcBolumler;
    private final ArrayList<BolumModel> arrayListBolumler = new ArrayList<>();
    public static int bolum_id = -1;
    public static int masa_id = -1;
    ImageButton imgAyarMain;
    MyLoginListener loginListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masa);
        Paper.init(this);
        init();

//        countTimer = new CountDownTimer(1000*60*60*24, 5000) {
//            @Override
//            public void onTick(long l) {
//                if (bolum_id!=545){
//                    masaKontrolEt();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                Toast.makeText(MasaActivity.this, "Timer durdu", Toast.LENGTH_SHORT).show();
//
//            }
//        };
    }

    private void init() {

        if (!Paper.book().contains("ip")) {
            Paper.book().write("ip", "Admin.php");
        }
        btnCikis = findViewById(R.id.btnCikis);
        txMasaBaslik = findViewById(R.id.txMasaBaslik);
        rcMasalar = findViewById(R.id.rcHomeMasalar);
        int nmOfClmn = Common.calculateNoOfCollumns(this, 110);
        layoutManagerMasalar = new GridLayoutManager(this, nmOfClmn);// DÜZENLEEE
        rcMasalar.setLayoutManager(layoutManagerMasalar);

        rcBolumler = findViewById(R.id.rcBolumler);
        rcBolumler.setLayoutManager(new LinearLayoutManager(this));
        rcBolumler.setHasFixedSize(true);
        txBaslik = findViewById(R.id.txHomeBaslik);
        imgAyarMain = findViewById(R.id.imgAyarMain);

        imgAyarMain.setOnClickListener(view ->
                new Ayarlar().ipAdresiKaydet(MasaActivity.this, Paper.book().read("ip").toString()));

        btnCikis.setOnClickListener(view -> {
            AlertDialog.Builder dialogg = new AlertDialog.Builder(MasaActivity.this);
            dialogg.setTitle("ÇIKIŞ");
            dialogg.setMessage("Çıkış yapmak istediğinizden emin misiniz?");
            dialogg.setPositiveButton("Çıkış Yap", (dialogInterface, i) -> {
                cikisYap();
            }).setNegativeButton("İptal", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogg.show();
        });

        if (Paper.book().contains("username")) {
            String usr = Paper.book().read("username").toString();
            String pass = Paper.book().read("pass").toString();

            Toast.makeText(this, "Hoşgeldin:" + usr, Toast.LENGTH_SHORT).show();

            girisYap(usr, pass, false);
        } else {
            girisYap(null, null, true);
        }
    }

    private void bolumCek(String user, String pass, String res) {
        arrayListBolumler.clear();
        if (res == null) DbController.login(MasaActivity.this, user, pass, null);
        else {
            try {
                JSONObject obj = new JSONObject(res);
                JSONArray array = obj.getJSONArray("masaGrup");

//                    Toast.makeText(context, "SONUÇ : "+array, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    int id = object.getInt("id");
                    String adi = object.getString("adi");
                    String aciklama = object.getString("aciklama");


                    arrayListBolumler.add(new BolumModel(id, adi, aciklama, "", false));
                }
//                    Toast.makeText(context, ""+arrayListBolumler.get(0).getAdi(), Toast.LENGTH_SHORT).show();

                rcBolumler.setHasFixedSize(true);
                rcBolumler.setLayoutManager(new LinearLayoutManager(MasaActivity.this));
                rcBolumler.setAdapter(new BolumAdapter(MasaActivity.this, arrayListBolumler));
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "" + res, Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void masalarCek(final int bolumID, String user, String pass) {
        masalar.clear();
        Common.seciliBolumID = bolumID;
        DbController.masaCek(MasaActivity.this, bolumID, rcMasalar, user, pass);
        if (Common.isAktar) {
            txMasaBaslik.setText(">>" + Common.seciliMasaAdi + " aktaracağınız Masayı Seçin");
        } else if (Common.isSiparisAktar) {
            txMasaBaslik.setText(">>" + Common.seciliMasaAdi + " Siparişlerinin aktarılacağı masayı seçin.");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Common.isAktar = false;
        Common.isSiparisAktar = false;
//        if (countTimer!=null) {
//            //cstart=false; // Calısmıyor / Lazım olabilir
//            countTimer.cancel();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Common.seciliBolumID != -1) masalarCek(Common.seciliBolumID, Common.usr, Common.pss);

//        if (countTimer!=null) {
//            // cstart=true;//Calısıyor / Lazım olabilir
//            countTimer.start();
//        }
    }

    public void ipKaydet(String adres) {

        String url = "http://" + adres;

        Paper.book().write("ip", adres);
        Paper.book().write("url", url);
//        Toast.makeText(MasaActivity.this, "Adres kaydedildi.", Toast.LENGTH_SHORT).show();
    }

    public void girisYap(String usernm, String passs, boolean showd) {

        loginListener = new MyLoginListener() {
            @Override
            public void onSuccessLogin(String response, String user, String pass) {
                try {
                    JSONObject object = new JSONObject(response);
                    Log.d("DENEME", "onSuccessLogin: " + response);
                    // Eğer veri geldiyse

                    Paper.book().write("username", user);
                    Paper.book().write("pass", pass);
                    Common.usr = user;
                    Common.pss = pass;

                    bolumCek(Common.usr, Common.pss, response);
                    if (Common.dialog != null) Common.dialog.dismiss();
                    if (dlg != null) dlg.dismiss();

                } catch (JSONException e) {
                    if (dlg != null) dlg.dismiss();

                    e.printStackTrace();

                    Log.d("DENEME", "tt: " + e.getMessage());
                    Toast.makeText(MasaActivity.this, response, Toast.LENGTH_SHORT).show();
                    Common.dialog.dismiss();
                    girisYap(null, null, false);

                }
            }

            @Override
            public void onErrorLogin(String error) {

                if (dlg!=null) dlg.dismiss();
                if (Common.dialog != null) Common.dialog.dismiss();
                girisYap(null, null, false);
                Toast.makeText(MasaActivity.this, "HATA: \n" + error, Toast.LENGTH_SHORT).show();
            }
        };

        if (usernm != null && passs != null) {
            DbController.login(MasaActivity.this, usernm, passs, loginListener);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = LayoutInflater.from(this).inflate(R.layout.login_layout, null);
            EditText edUser = view.findViewById(R.id.edUserName);
            EditText edPass = view.findViewById(R.id.edPass);
            Button btnGiris = view.findViewById(R.id.btnGiris);


            btnGiris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String user = (usernm != null) ? usernm : edUser.getText().toString();
                    String pass = (passs != null) ? passs : edPass.getText().toString();

                    if (user.length() < 2) edUser.setError("Çok kısa");
                    else if (pass.length() < 2) edPass.setError("Çok kısa");
                    else {
                        Common.showSpotDialog(MasaActivity.this);

                        DbController.login(MasaActivity.this, user, pass, loginListener);

                    }
                }
            });
            builder.setCancelable(false);
            builder.setView(view);
            dlg = builder.show();


        }
    }

    public void aktar(String eskiMasaAdi, String eskiMasaID, String yeniMasaAdi, String yeniMasaID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Masa Taşı");
        builder.setMessage("Eski masa: " + eskiMasaAdi + " \n" + "Yeni masa: " + yeniMasaAdi + "\nAktarım işlemini Onaylıyor musunuz?");

        builder.setPositiveButton("ONAYLA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Common.showSpotDialog(MasaActivity.this);
                GetDataListener dtListener = new GetDataListener() {
                    @Override
                    public void onSuccessData(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
//                                String result = object.getString("result");
                            boolean result = object.getBoolean("sonuc");
                            if (result) {
                                Toast.makeText(MasaActivity.this, "Aktarma işlemi tamamlandı." + result, Toast.LENGTH_SHORT).show();
                                Common.isAktar = false;
                                Common.isSiparisAktar = false;
                                txMasaBaslik.setText("Masa Seçin");
                                Common.dialog.dismiss();
                                masalarCek(Common.seciliBolumID, Common.usr, Common.pss);
                            } else {
                                Common.dialog.dismiss();
                                Toast.makeText(MasaActivity.this, "İşlem başarısız. Tekrar deneyin.\n" + response, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Common.dialog.dismiss();
                            Toast.makeText(MasaActivity.this, "Aktarım hatası. " + e.getMessage() + " \n" + response, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onErrorData(String error) {
                        Toast.makeText(MasaActivity.this, "Aktarım başarısız..  \n" + error, Toast.LENGTH_SHORT).show();
                        Common.dialog.dismiss();
                    }
                };
                //  Sipariş aktar
                if (Common.isSiparisAktar) {
                    DbController.siparisTasi(MasaActivity.this
                            , dtListener
                            , yeniMasaID
                            , Common.aktarilacaklar);
                } else {
                    //Masa Aktar
                    DbController.masaTasi(MasaActivity.this, yeniMasaID, dtListener);
                }
            }
        }).setNegativeButton("IPTAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txMasaBaslik.setText("Masa Seçin");
                Common.isAktar = false;
                startActivity(new Intent(MasaActivity.this, MainActivity.class));
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void cikisYap() {
        Paper.book().destroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (Common.isAktar) {
            startActivity(new Intent(MasaActivity.this, MainActivity.class));
            finish();
        } else super.onBackPressed();
    }
}
