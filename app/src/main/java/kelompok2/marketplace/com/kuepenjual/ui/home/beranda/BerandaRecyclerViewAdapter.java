package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public class BerandaRecyclerViewAdapter extends RecyclerView.Adapter<BerandaRecyclerViewAdapter.BerandaViewHolder> {

    private List<PenjualanBarangList> listBarang;
    private Context context;

    public BerandaRecyclerViewAdapter(List<PenjualanBarangList> listBarang, Context context){
        this.listBarang = listBarang;
        this.context = context;
    }

    @NonNull
    @Override
    public BerandaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BerandaViewHolder(LayoutInflater.from(context).inflate(R.layout.list_barang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position).getBarang());
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class BerandaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageBarang;
        private TextView namaBarang;

        BerandaViewHolder(View view){
            super(view);
            init(view);
        }

        private void init(View view){
            imageBarang = view.findViewById(R.id.img_barang_list_barang);
            namaBarang = view.findViewById(R.id.tv_nama_list_barang);
        }

        public void bindItem(Barang barang){
            Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(imageBarang);
            namaBarang.setText(barang.getNama());
        }
    }
}
