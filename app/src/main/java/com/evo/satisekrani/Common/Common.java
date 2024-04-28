package com.evo.satisekrani.Common;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.evo.satisekrani.Activities.MainActivity;
import com.evo.satisekrani.Adapters.BolumAdapter;
import com.evo.satisekrani.Adapters.KategoriAdapter;
import com.evo.satisekrani.Adapters.MasaAdapter;
import com.evo.satisekrani.Adapters.SiparisAdapter;
import com.evo.satisekrani.Adapters.UrunAdapter;
import com.evo.satisekrani.Model.BolumModel;
import com.evo.satisekrani.Model.DepoModel;
import com.evo.satisekrani.Model.KategoriModel;
import com.evo.satisekrani.Model.MasaModel;
import com.evo.satisekrani.Model.SecenekModel;
import com.evo.satisekrani.Model.SiparisModel;
import com.evo.satisekrani.Model.SiparisModelWeb;
import com.evo.satisekrani.Model.UrunModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;

public class Common {
    public static boolean isAktar = false;
    public static boolean isSiparisAktar = false;
    public static DecimalFormat df = new DecimalFormat("#.##");
//    public static final String linkKahveMeydani = "http://kahvemeydani.nixpin.com/App/";
    public static final String linkDemo = "http://app.cafe.nixpin.com/App/";
    public static List<SiparisModelWeb> eskiSiparisler = new ArrayList<>();

    public static int seciliMasaID = -1;
    public static int seciliBolumID = -1;
    public static DepoModel depoModel = null;

    public static byte spSecili = 0;
    public static byte spMutfak = 1;
    public static byte spYeni = 0;

    public static byte typeSp = 0;
    public static byte typeEskiSp = -1;
    public static byte typeSpTrsn = 1;
    public static byte typeSpTrnsAktarilacak = 2;


    public static AlertDialog dialog;
    public static String seciliMasaAdi = "";
    public static List<SiparisModelWeb> seciliMasaSiparis = new ArrayList<>();
    public static List<SiparisModelWeb> aktarilacaklar = new ArrayList<>();
    public static String usr = "";
    public static String pss = "";

    public static int calculateNoOfCollumns(Context context, float collumnWitdhDp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float scrWithDp = metrics.widthPixels/metrics.density;
        return (int) (scrWithDp/collumnWitdhDp+0.5);
    }
    public static void showSpotDialog(Context context){
        dialog = new SpotsDialog.Builder().setContext(context).setMessage("Lütfen Bekleyin..").setCancelable(false).build();
        dialog.show();
    }


}
