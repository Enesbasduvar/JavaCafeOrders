package com.evo.satisekrani.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.evo.satisekrani.Activities.MasaActivity;
import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Model.BolumModel;
import com.evo.satisekrani.R;

import java.util.ArrayList;

public class BolumAdapter extends RecyclerView.Adapter<BolumAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<BolumModel> arrayList;
    public BolumAdapter(Context context, ArrayList<BolumModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bolumler_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int pos) {
        holder.txBolum.setText(arrayList.get(pos).getAdi());
        holder.imgBolum.setImageResource(R.drawable.restaurant);

        for (int i =0; i<arrayList.size();i++){
            if (MasaActivity.bolum_id !=arrayList.get(pos).getId())
                //Seçili ise
//                holder.cardBolum.setCardBackgroundColor(Color.parseColor("#FFADBDBE"));
            holder.cardBolum.setCardBackgroundColor(Color.parseColor("#d1d0cf"));
            else //                holder.cardBolum.setCardBackgroundColor(Color.parseColor("#f124dcdf"));
            holder.cardBolum.setCardBackgroundColor(Color.parseColor("#76798c"));

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardBolum;
        TextView txBolum;
        ImageView imgBolum;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardBolum=itemView.findViewById(R.id.cardBolumler);
            txBolum = itemView.findViewById(R.id.txBolumler);
            imgBolum = itemView.findViewById(R.id.imgBolumler);

            cardBolum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
//                 new Common().masaCek(context,arrayList.get(pos).getId(),"",null);
                    ((MasaActivity)context).txBaslik.setText(arrayList.get(pos).getAdi());
//                ((MasaActivity)context).masalarCek(arrayList.get(pos).getId());

                    ((MasaActivity) context).masalarCek(arrayList.get(pos).getId(), Common.usr, Common.pss);

                    MasaActivity.bolum_id= arrayList.get(pos).getId();
                    arrayList.get(pos).setSecili(true);

                    notifyDataSetChanged();
                }
            });
        }
    }
}
