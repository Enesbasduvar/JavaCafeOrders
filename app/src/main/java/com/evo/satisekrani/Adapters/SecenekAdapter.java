package com.evo.satisekrani.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.evo.satisekrani.Common.Common;
import com.evo.satisekrani.Model.SecenekModel;
import com.evo.satisekrani.R;

import java.util.List;

public class SecenekAdapter extends RecyclerView.Adapter<SecenekAdapter.MyViewHolder> {
    private Context context;
    private List<SecenekModel> list;

    public SecenekAdapter(Context context, List<SecenekModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.secenek_butonu,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.btnSecenek.setText(list.get(position).getAdi());
        if(list.get(position).isSecili()) holder.btnSecenek.setBackgroundColor(Color.parseColor("#f124dcdf"));
        else holder.btnSecenek.setBackgroundColor(Color.parseColor("#FFADBDBE"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btnSecenek;
        public MyViewHolder(View itemView) {
            super(itemView);

            btnSecenek = itemView.findViewById(R.id.btnSecenek);

            btnSecenek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    //Çoklu seçim kısmı
                    //Çoklu seçim kısmı
                    SecenekModel secenek = list.get(pos);
                    if (!secenek.isSecili()) list.get(pos).setSecili(true);
                    else{
                        secenek.setSecili(false);
                    }
                    notifyItemChanged(pos);

                    //Tekli Seçim kısmı
                    //Tekli Seçim kısmı
//                    if (secenek.getGrup_kodu().equals("0") ){
//                        if (!list.get(pos).isSecili()){
//                            for (int i=0; i<list.size(); i++ ) {
//                                list.get(i).setSecili(false);
//                            }
//                            list.get(pos).setSecili(true);
//                        }
//                        notifyDataSetChanged();
//                    }else{
//
//                        list.get(pos).setSecili(!list.get(pos).isSecili());
//                        notifyItemChanged(pos);
//                    }
                }
            });
        }
    }
}
