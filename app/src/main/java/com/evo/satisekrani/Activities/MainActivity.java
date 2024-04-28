package com.evo.satisekrani.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.evo.satisekrani.Adapters.KategoriAdapter;
import com.evo.satisekrani.Adapters.SiparisAdapter;
import com.evo.satisekrani.Adapters.UrunAdapter;
import com.evo.satisekrani.Common.CaptureAct;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Controllers.DbController;
import com.evo.satisekrani.Controllers.GetDataListener;
import com.evo.satisekrani.Controllers.UcretListener;
import com.evo.satisekrani.Model.KategoriModel;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.SiparisModelWeb;
import com.evo.satisekrani.Model.UrunModel;
import com.evo.satisekrani.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements UcretListener {
    private TextView txKAtegoriAdi, txToplam, txMasa;
    public TextView txMutfak;
    public TextView txYeni;
    private RecyclerView rcKAtegori, rcUrun, rcSiparis;
    double sonMktr = 0;
    ImageButton btnAktar;
    AlertDialog dialogNot = null;
    double geciciSoFiyat;

    List<KategoriModel> listKategori = new ArrayList<>();
    List<UrunModel> listUrun = new ArrayList<>();
    List<SiparisModelWeb> listSiparis = new ArrayList<>();

    private EditText edBarkod;
    double toplam = 0.0;
    double geciciToplam = 0.0;
    SiparisAdapter adapterSiparis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Common.spSecili = Common.spYeni;
        txMasa = findViewById(R.id.txMenuMasa);
        txMasa.setText(Common.seciliMasaAdi);
        txYeni = findViewById(R.id.txYeni);
        txMutfak = findViewById(R.id.txMutfak);

        txYeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secYeni();
            }
        });
        txMutfak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secMutfak();
            }
        });


        txKAtegoriAdi = findViewById(R.id.txMenuKategoriAdi);
        txToplam = findViewById(R.id.txToplam);
        btnAktar = findViewById(R.id.btnAktar);
//        txIskonto = findViewById(R.id.txIskonto);
//        txIade = findViewById(R.id.txIade);
        edBarkod = findViewById(R.id.edBarkod);
        edBarkod.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (edBarkod.getText().toString().length() > 1) {
                        urunCek("", "", edBarkod.getText().toString());
                    }
                    return false;
                }
                return false;
            }
        });

        rcKAtegori = findViewById(R.id.rcMenuKategori);
        rcKAtegori.setHasFixedSize(true);

        rcUrun = findViewById(R.id.rcMenuYemek);
        rcUrun.setHasFixedSize(true);
        int nmOfCol = Common.calculateNoOfCollumns(this, 180);
        rcUrun.setLayoutManager(new GridLayoutManager(this, nmOfCol));

        rcSiparis = findViewById(R.id.rcSiparisler);
        rcSiparis.setHasFixedSize(true);
        kategoriCek();
        adapterSiparis = new SiparisAdapter(this, listSiparis);
        siparisCek();
    }
    ///Eski Siparişleri Çek
    private void siparisCek() {
        DbController.siparisCek(this, String.valueOf(Common.seciliMasaID), listSiparis, rcSiparis, adapterSiparis);
    }

    public void click_btnAktar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Masa Aktar");
        builder.setTitle("Aktarılacak sipariş(ler)i veya miktarını seçiniz.");
        builder.setNegativeButton("Tüm siparişleri aktar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Common.isAktar = true;
                startActivity(new Intent(MainActivity.this, MasaActivity.class));
                finish();
            }
        }).setPositiveButton("Kısmi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Common.seciliMasaSiparis.clear();
                Common.seciliMasaSiparis.addAll(Common.eskiSiparisler);
                startActivity(new Intent(MainActivity.this, AktarActivity.class));
            }
        });
        builder.show();

    }

    public void click_siparisTamam(View view) {
        if (listSiparis.size() < 1) {
            Toast.makeText(this, "Sepet Boş", Toast.LENGTH_SHORT).show();
            return;
        }
        Common.showSpotDialog(this);
        GetDataListener dataListener = new GetDataListener() {
            @Override
            public void onSuccessData(String response) {
                Common.dialog.dismiss();
                Toast.makeText(MainActivity.this, "Siparişler gönderildi", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onErrorData(String error) {
                Toast.makeText(MainActivity.this, "Sipariş gönderilemedi: " + error, Toast.LENGTH_SHORT).show();
                Common.dialog.dismiss();
            }
        };

        DbController.mutfagaGonder(this, listSiparis, dataListener);
//        DbController.siparisGonderWeb(this,Common.seciliBolumID, Common.seciliMasaID,);
    }

    public void kategoriCek() {

        listKategori.clear();

        DbController.kategoriCek(this, rcKAtegori, listKategori);
//        urunCek("","");
    }

    public void urunCek(String kategori, String grup_id, String barkod) {
//        barkodOku();
        if (barkod.length() > 1) {
            /// barkodu sorgula ve çek
            edBarkod.setText(barkod);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(barkod + " barkodlu ürün bulunamadı.");
            builder.setPositiveButton("Tekrar Okut", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    barkodOku();
                }
            }).setNegativeButton("Bitir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();

            dialog.show();
        } else {
            listUrun.clear();
            txKAtegoriAdi.setText(kategori);

            DbController.urunCek(this, rcUrun, grup_id, listUrun);
        }
    }

    public void secYeni() {
        if (Common.spSecili != Common.spYeni) {
            Common.spSecili = Common.spYeni;

            siparisCek();

            txYeni.setBackgroundColor(Color.parseColor("#3F51B5"));
            txMutfak.setBackgroundColor(Color.parseColor("#DA999988"));

            txMutfak.setTextColor(Color.parseColor("#353535"));
            txYeni.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    public void secMutfak() {
        if (Common.spSecili != Common.spMutfak) {
            Common.spSecili = Common.spMutfak;

            siparisCek();

            txMutfak.setBackgroundColor(Color.parseColor("#3F51B5"));
            txYeni.setBackgroundColor(Color.parseColor("#DA999988"));

            txYeni.setTextColor(Color.parseColor("#353535"));
            txMutfak.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    public void siparisEkle(SiparisModelWeb siparis, boolean guncelle, double tutarEkle) {
        if (guncelle) {
            listSiparis.set(listSiparis.size() - 1, siparis);
            adapterSiparis.notifyItemChanged(listSiparis.size() - 1);
            ucretEkle(siparis.getSonTutar() - siparis.getFiyat());
        } else {
            listSiparis.add(siparis);
            ucretHesapla(true, siparis.getSonTutar());
            if (adapterSiparis == null) {
                adapterSiparis = new SiparisAdapter(this, listSiparis);
                rcSiparis.setAdapter(adapterSiparis);
            } else {
                if (Common.spSecili == Common.spMutfak) {
                    rcSiparis.setAdapter(adapterSiparis);
                }
                adapterSiparis.notifyItemInserted(listSiparis.size() - 1);
            }
            secYeni();
        }
    }

    public void click_SiparisTemizle(View view) {
        if (Common.spSecili == Common.spYeni) {
            if (listSiparis.size() > 0) {
                Common.showSpotDialog(MainActivity.this);
                GetDataListener dListener = new GetDataListener() {
                    @Override
                    public void onSuccessData(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(MainActivity.this, "Temizlendi", Toast.LENGTH_SHORT).show();
                            toplam = 0.00;
                            txToplam.setText("Toplam: " + String.valueOf(toplam) + " TL");
                            listSiparis.clear();
                            if (adapterSiparis != null) adapterSiparis.notifyDataSetChanged();
                            else {
                                adapterSiparis = new SiparisAdapter(MainActivity.this, listSiparis);
                                rcSiparis.setAdapter(adapterSiparis);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "İşlem hatası: " + e.getMessage() + "\n" + response, Toast.LENGTH_SHORT).show();
                        }
                        Common.dialog.dismiss();

                    }

                    @Override
                    public void onErrorData(String error) {
                        Common.dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Hata: " + error, Toast.LENGTH_SHORT).show();
                    }
                };
                DbController.siparisSil(this, null, listSiparis.get(0).getFatura_id(), dListener);
            } else Toast.makeText(this, "Silinecek sipariş yok", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Mutfağa gönderilen siparişler silinemez.", Toast.LENGTH_SHORT).show();
        }

    }

    public void ucretHesapla(boolean ekle, double fyt) {
        if (ekle) {
            toplam += fyt;
            txToplam.setText("Toplam: " + Common.df.format(toplam) + " TL");
        } else {
            toplam -= fyt;
            txToplam.setText("Toplam: " + Common.df.format(toplam) + "TL");
        }
    }

    public void click_barkodOku(View view) {
        barkodOku();
    }

    private void barkodOku() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Barkod Aranıyor...");
        integrator.initiateScan();
    }

    public void miktarDuzelt(double fytt, double sonFiyat, double mktr, int pos) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        View vie = LayoutInflater.from(this).inflate(R.layout.miktar_view, null);
        ImageButton btnArttir = vie.findViewById(R.id.btnArttir);
        ImageButton btnAzalt = vie.findViewById(R.id.btnAzalt);
        EditText edMktrView = vie.findViewById(R.id.edMktarView);
        edMktrView.setText(String.valueOf(mktr));

        sonMktr = mktr;
        geciciToplam = toplam;

        btnArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonMktr = Double.parseDouble(edMktrView.getText().toString());
                sonMktr += 0.5;

                geciciSoFiyat = fytt * sonMktr;
                geciciToplam = geciciToplam + (geciciSoFiyat - (fytt * (sonMktr - 0.5)));
                edMktrView.setText(String.valueOf(sonMktr));
            }
        });
        btnAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonMktr > 1) {
                    sonMktr -= 0.5;
                    geciciToplam = geciciToplam - (geciciSoFiyat - (fytt * sonMktr));
                    geciciSoFiyat = fytt * sonMktr;
                    edMktrView.setText(String.valueOf(sonMktr));
                }
            }
        });

        build.setPositiveButton("KAYDET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GetDataListener dtListener = new GetDataListener() {
                    @Override
                    public void onSuccessData(String response) {
                        listSiparis.get(pos).setMiktar(sonMktr);
                        listSiparis.get(pos).setSonTutar(geciciSoFiyat);
                        adapterSiparis.notifyItemChanged(pos);
                        Toast.makeText(MainActivity.this, "Miktar değiştirildi", Toast.LENGTH_SHORT).show();
                        toplam = geciciToplam;
                        geciciToplam = 0;
                        txToplam.setText("Toplam: " + Common.df.format(toplam) + " TL");
                    }

                    @Override
                    public void onErrorData(String error) {
                        Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                };

                DbController.updateMiktar(MainActivity.this, listSiparis.get(pos).getId(), String.valueOf(sonMktr), dtListener);
            }
        }).setNegativeButton("IPTAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sonMktr = 0;
                geciciToplam = 0;
                dialog.dismiss();
            }
        });
        build.setView(vie);
        build.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                urunCek("", "", result.getContents());

            } else {
                Toast.makeText(this, "IPTAL EDILDI", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "NuLL", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void notEkle(int pozisyon) {
        AlertDialog.Builder buildernot = new AlertDialog.Builder(this);
        View vi = LayoutInflater.from(this).inflate(R.layout.not_view, null);
        EditText edNot = vi.findViewById(R.id.edtNot);
        edNot.setText(!listSiparis.get(pozisyon).getAciklama().equals("yok") ? listSiparis.get(pozisyon).getAciklama() : "");

        Button btnGunclle = vi.findViewById(R.id.btnNotGuncelle);
        Button btnIptal = vi.findViewById(R.id.btnNotIptal);
        buildernot.setView(vi);
        buildernot.setCancelable(true);
        btnGunclle.setOnClickListener(v -> {
            String not = edNot.getText().toString();
            if (not.isEmpty()) edNot.setError("Not giriniz");
            else {
                GetDataListener listener = new GetDataListener() {
                    @Override
                    public void onSuccessData(String response) {
                        Toast.makeText(MainActivity.this, "Not eklendi. ", Toast.LENGTH_SHORT).show();
                        listSiparis.get(pozisyon).setAciklama(not);

                        adapterSiparis.notifyItemChanged(pozisyon);
                        dialogNot.dismiss();
                    }

                    @Override
                    public void onErrorData(String error) {
                        Toast.makeText(MainActivity.this, "HATA: " + error, Toast.LENGTH_SHORT).show();
                    }
                };
                DbController.updateNot(MainActivity.this, listener, listSiparis.get(pozisyon).getId(), not);
            }
        });

        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogNot.dismiss();
            }
        });

        dialogNot = buildernot.show();
    }

    @Override
    public void ucretEkle(double ekle) {
        toplam += ekle;
        txToplam.setText("Toplam: " + Common.df.format(toplam) + " TL");
    }

    @Override
    public void ucretCikar(double cikar) {
        toplam -= cikar;
        txToplam.setText("Toplam: " + Common.df.format(toplam) + "TL");
    }

}