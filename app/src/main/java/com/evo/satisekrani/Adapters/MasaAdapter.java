package com.evo.satisekrani.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.StringRequest;
import com.evo.satisekrani.Activities.MainActivity;
import com.evo.satisekrani.Activities.MasaActivity;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Model.MasaModel;
import com.evo.satisekrani.R;

import java.util.ArrayList;

public class MasaAdapter extends RecyclerView.Adapter<MasaAdapter.MyViewHolder> {
    private ArrayList<MasaModel> list;
    private Context context;

    public MasaAdapter(Context context, ArrayList<MasaModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.masalar_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, final int pos) {
        if (list.get(pos).getAdi_soyadi().contains("asa")) holder.txMasaNo.setText(list.get(pos).getAdi_soyadi());
        else holder.txMasaNo.setText("Masa "+list.get(pos).getAdi_soyadi());

        if (list.get(pos).getHareket().equals("0")){
//            holder.cardMasalar.setCardBackgroundColor(Color.parseColor("#009688"));
            holder.cardMasalar.setCardBackgroundColor(Color.parseColor("#B3B3B3"));
        }
        else {
//            holder.cardMasalar.setCardBackgroundColor(Color.RED);
            holder.cardMasalar.setCardBackgroundColor(Color.parseColor("#a83a32"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardMasalar;
        TextView txMasaNo;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardMasalar = itemView.findViewById(R.id.cardMasalarV);
            txMasaNo = itemView.findViewById(R.id.txMasalarV);

            cardMasalar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
//                    intent.putExtra("masa_adi",list.get(pos).getAdi());
//                    intent.putExtra("bolum_adi",list.get(pos).getBolum_adi());
                    if (Common.isAktar || Common.isSiparisAktar) {
                        ((MasaActivity) context).aktar(Common.seciliMasaAdi, String.valueOf(Common.seciliMasaID),list.get(pos).getAdi_soyadi(),String.valueOf(list.get(pos).getId()));
                    }
                    else {
                        MasaActivity.masa_id = list.get(pos).getId();
                        Intent intent = new Intent(context, MainActivity.class);
                        Common.seciliMasaID = list.get(pos).getId();
                        Common.seciliMasaAdi = "Masa "+list.get(pos).getAdi_soyadi();
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
