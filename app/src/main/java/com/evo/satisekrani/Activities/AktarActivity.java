package com.evo.satisekrani.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.evo.satisekrani.Adapters.SiparisAdapter;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.SiparisModelWeb;
import com.evo.satisekrani.R;

import java.util.ArrayList;
import java.util.List;

public class AktarActivity extends AppCompatActivity {
    private final List<SiparisModelWeb> siparisler =new ArrayList<>();
    private final List<SiparisModelWeb> aktarilacaklar =new ArrayList<>();

    private SiparisAdapter adapterSp;
    private SiparisAdapter adapterAkt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktar);

        init();
    }

    private void init() {
        Common.aktarilacaklar.clear();
        siparisler.addAll(Common.seciliMasaSiparis);

        RecyclerView rcSiparisler = findViewById(R.id.rcTranserSiparis);
        rcSiparisler.setHasFixedSize(true);
        RecyclerView rcAktarilacaklar = findViewById(R.id.rcTransferAktarilacaklar);
        rcAktarilacaklar.setHasFixedSize(true);

        adapterAkt = new SiparisAdapter(this,aktarilacaklar,Common.typeSpTrnsAktarilacak);
        rcAktarilacaklar.setAdapter(adapterAkt);
        touchHelperLeft.attachToRecyclerView(rcAktarilacaklar);


        adapterSp = new SiparisAdapter(this,siparisler,Common.typeSpTrsn);
        rcSiparisler.setAdapter(adapterSp);
        touchHelperRight.attachToRecyclerView(rcSiparisler);

    }
    ItemTouchHelper.SimpleCallback tcbRight = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();
            double mktr = siparisler.get(pos).getMiktar();
//            double copyMktr =

            SiparisModelWeb aktarilacakSiparis = SiparisModelWeb.kopyala(siparisler.get(pos));
            aktarilacakSiparis.setMiktar(1.0);
            aktarilacakSiparis.setFiyat(siparisler.get(pos).getFiyat()/mktr);
            aktarilacaklar.add(aktarilacakSiparis);
            adapterAkt.notifyItemInserted(aktarilacaklar.size()-1);

            if (mktr>=2){
                siparisler.get(pos).setMiktar(mktr-1);
                adapterSp.notifyItemChanged(pos);
                Toast.makeText(AktarActivity.this, "1 adet aktarıldı", Toast.LENGTH_SHORT).show();
            }else{
                siparisler.remove(pos);
                adapterSp.notifyItemRemoved(pos);
            }
        }
    };
    ItemTouchHelper.SimpleCallback tcbLeft = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {



            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();
            siparisler.add(aktarilacaklar.get(pos));
            adapterSp.notifyItemInserted(siparisler.size()-1);
//            adapterAkt.notifyDataSetChanged();
            aktarilacaklar.remove(pos);
            adapterAkt.notifyItemRemoved(pos);
        }
    };
    ItemTouchHelper touchHelperRight = new ItemTouchHelper(tcbRight);
    ItemTouchHelper touchHelperLeft = new ItemTouchHelper(tcbLeft);

    public void click_iptal(View view){
        finish();
    }
    public void click_kaydet(View view){
        Common.isSiparisAktar = true;
        Common.aktarilacaklar = aktarilacaklar;
        startActivity(new Intent(AktarActivity.this, MasaActivity.class));
        finish();
    }
}