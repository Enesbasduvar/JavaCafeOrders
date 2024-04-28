package com.evo.satisekrani.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.evo.satisekrani.Activities.MainActivity;
import com.evo.satisekrani.Model.KategoriModel;
import com.evo.satisekrani.R;
import java.util.ArrayList;
import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {
    private Context context;

    private List<KategoriModel> arrayList = new ArrayList<>();

    public KategoriAdapter(Context context, List<KategoriModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int pos) {
        View v = LayoutInflater.from(context).inflate(R.layout.kategori_view,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, final int pos) {
        holder.txKategoriAd.setText(arrayList.get(pos).getAdi());
        //Glide veya Picasso
//        /**/holder.imgKategori.setImageResource(R.drawable.fmenu);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgKategori;
        private TextView txKategoriAd;
        private LinearLayout linearKategori;
        public MyViewHolder( View itemView) {
            super(itemView);
            imgKategori = itemView.findViewById(R.id.imgKategori);
            txKategoriAd = itemView.findViewById(R.id.txKategoriAD);
            linearKategori = itemView.findViewById(R.id.linearKategori);

            linearKategori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).urunCek(arrayList.get(getAdapterPosition()).getAdi(),arrayList.get(getAdapterPosition()).getId(),"");
                }
            });
        }
    }
}
