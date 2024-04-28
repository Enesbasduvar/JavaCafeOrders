package com.evo.satisekrani.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.evo.satisekrani.Activities.MainActivity;
import com.evo.satisekrani.Controllers.DbController;
import com.evo.satisekrani.Controllers.GetDataListener;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.UrunModel;
import com.evo.satisekrani.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UrunAdapter extends RecyclerView.Adapter<UrunAdapter.MyViewHolder> {
    private Context context;
    private List<UrunModel> arrayList= new ArrayList<>();

    public UrunAdapter(Context context, List<UrunModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int pos) {
        View v = LayoutInflater.from(context).inflate(R.layout.urun_view,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, final int pos) {
        holder.txUrunAd.setText(arrayList.get(pos).getAdi());
        holder.txUrunFiyat.setText(arrayList.get(pos).getSatis_fiyati_1()+" TL");
//        holder.imgUrun.setImageResource(R.drawable.uruns);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txUrunAd;
        TextView txUrunFiyat;
        ImageView imgUrun;
        CardView cardUrunler;
        MyViewHolder( View itemView) {
            super(itemView);
            txUrunAd = itemView.findViewById(R.id.txUrunAd);
            txUrunFiyat = itemView.findViewById(R.id.txUrunFiyat);
            imgUrun = itemView.findViewById(R.id.imgUrun);
            cardUrunler = itemView.findViewById(R.id.cardUrun);


            cardUrunler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DbController.stokSecenekAra(context,arrayList.get(getAdapterPosition()));

                }
            });
        }
    }
}