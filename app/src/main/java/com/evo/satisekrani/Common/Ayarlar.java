package com.evo.satisekrani.Common;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.evo.satisekrani.Activities.MasaActivity;
import com.evo.satisekrani.R;

public class Ayarlar {
    private String ipAdresimiz = "";

    public void ipAdresiKaydet(final Context context, String ip) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v = LayoutInflater.from(context).inflate(R.layout.url_view, null, false);
        final EditText edIpAdres = v.findViewById(R.id.edIpAdress);
        if (!ip.equals("")) {
            edIpAdres.setText(ip);
        }
        Button btnIpTamam = v.findViewById(R.id.txIpTamam);
        Button btnIptal = v.findViewById(R.id.txIpIptal);
        builder.setView(v);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.show();

        btnIpTamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edIpAdres.getText().toString().isEmpty()) {
                    edIpAdres.setError("Zorunlu");
                } else {
                    dialog.dismiss();
                    ipAdresimiz = edIpAdres.getText().toString();
                    Toast.makeText(context, "Ip adresi değiştirildi!", Toast.LENGTH_SHORT).show();

                    new MasaActivity().ipKaydet(ipAdresimiz);
                }
            }
        });
        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
