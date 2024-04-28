package com.evo.satisekrani.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Activities.MainActivity;
import com.evo.satisekrani.Controllers.DbController;
import com.evo.satisekrani.Controllers.GetDataListener;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.SiparisModelWeb;
import com.evo.satisekrani.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SiparisAdapter extends RecyclerView.Adapter<SiparisAdapter.MyViewHolder> {
    private final Context context;
    private final List<SiparisModelWeb> arrayList;
    private byte type = Common.typeSp;

    // private static boolean sileBasildi = false;
    public SiparisAdapter(Context context, List<SiparisModelWeb> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public SiparisAdapter(Context context, List<SiparisModelWeb> arrayList, byte type) {
        this.context = context;
        this.arrayList = arrayList;
        this.type = type;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.siparis_view, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int pos) {
        SiparisModelWeb siparis = arrayList.get(pos);

        holder.txUrunAd.setText(siparis.getStok_adi());
        holder.txFiyat.setText(Common.df.format(siparis.getFiyat()));
        holder.txSaat.setText("");
        holder.edMiktar.setText(String.valueOf(siparis.getMiktar()));
        holder.txNot.setText(siparis.getAciklama().equals("") ? "NOT: Yok" : "NOT: " + siparis.getAciklama());
        if (siparis.getIcerik().equals("")) holder.txIcerik.setVisibility(View.GONE);
        else {
            holder.txIcerik.setVisibility(View.VISIBLE);
            holder.txIcerik.setText(arrayList.get(pos).getIcerik());
        }
        if (arrayList.get(pos).getYazdir().equals("1")){
            holder.imgSiparisNot.setVisibility(View.GONE);
            holder.imgSiparisSil.setVisibility(View.GONE);
            holder.edMiktar.setEnabled(false);
        }

        if (type!=Common.typeSp){
            holder.imgSiparisNot.setVisibility(View.GONE);
            holder.imgSiparisSil.setVisibility(View.GONE);
            holder.edMiktar.setEnabled(false);
        }
//        else{
//            holder.imgSiparisNot.setVisibility(View.VİS);
//            holder.imgSiparisSil.setVisibility(View.GONE);
//            holder.edMiktar.setEnabled(false);
//        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txUrunAd, txFiyat, txSaat, txNot, txIcerik;
        ImageView imgSiparisSil, imgSiparisNot;
        EditText edMiktar;


        MyViewHolder(View itemView) {
            super(itemView);
            txUrunAd = itemView.findViewById(R.id.txSiparisUrun);
            txFiyat = itemView.findViewById(R.id.txSiparisFiyat);
            imgSiparisSil = itemView.findViewById(R.id.imgSiparisSil);
            txSaat = itemView.findViewById(R.id.txSiparisSaat);
            edMiktar = itemView.findViewById(R.id.edMiktarPicker);
            imgSiparisNot = itemView.findViewById(R.id.imgSiparisNote);
            txNot = itemView.findViewById(R.id.txSiparisNote);
            txIcerik = itemView.findViewById(R.id.txSiparisIcerik);


            edMiktar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pozisyon = getAdapterPosition();
                    ((MainActivity) context).miktarDuzelt(arrayList.get(pozisyon).getFiyat()
                            , arrayList.get(pozisyon).getSonTutar()
                            , arrayList.get(pozisyon).getMiktar()
                            , pozisyon);
                }
            });

            imgSiparisSil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pozisyon = getAdapterPosition();

                    // Eğer mutfağa gönderilmediyse
                    Common.showSpotDialog(context);
                    GetDataListener dataListener = new GetDataListener() {
                        @Override
                        public void onSuccessData(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
//                                    Toast.makeText(context, ""+object.getString("sonuc"), Toast.LENGTH_SHORT).show();
                                ((MainActivity) context).ucretHesapla(false, arrayList.get(pozisyon).getSonTutar());
                                arrayList.remove(pozisyon);

                                notifyItemRemoved(pozisyon);
                                notifyItemRangeChanged(pozisyon, arrayList.size());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(context, "İşlem hatası. \nsonuc: " + response, Toast.LENGTH_SHORT).show();
                            }
                            Common.dialog.dismiss();
                        }

                        @Override
                        public void onErrorData(String error) {
                            Common.dialog.dismiss();
                            Toast.makeText(context, "İşlem başarısız. " + error, Toast.LENGTH_SHORT).show();
                        }
                    };

                    DbController.siparisSil(context, arrayList.get(pozisyon).getId(), null, dataListener);


                    //                notifyItemRangeRemoved(pos,arrayList.size());
//                    notifyItemRangeChanged(pos,arrayList.size());
//                }
//                else {
//                    Toast.makeText(context, "Bu siparişi iptal etmek için yetkiniz yok.", Toast.LENGTH_SHORT).show();
                    //new VeriCekGonder().siparisIptal(context,arrayList.get(pos).getSiparisID(),pos,SiparisAdapter.this);
//                }

                }
            });
            imgSiparisNot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).notEkle(getAdapterPosition());
                }
            });


        }
    }
}
