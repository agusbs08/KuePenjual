package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;

public class NotifikasiBarangRecyclerViewAdapter extends RecyclerView.Adapter<NotifikasiBarangRecyclerViewAdapter.NotifikasiBarangViewHolder> {

    private ArrayList<Barang> listBarang;

    public NotifikasiBarangRecyclerViewAdapter(ArrayList<Barang> listBarang){
        this.listBarang = listBarang;
    }

    @NonNull
    @Override
    public NotifikasiBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiBarangViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notifikasi_item_barang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiBarangViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position));
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    private ImageView imageBarang;
    private TextView namaBarang;
    private TextView hargaBarang;

    class NotifikasiBarangViewHolder extends RecyclerView.ViewHolder{
        public NotifikasiBarangViewHolder(View view){
            super(view);
            initView(view);
        }

        private void initView(View view){
            imageBarang = view.findViewById(R.id.iv_foto_notifikasi);
            namaBarang = view.findViewById(R.id.tv_namaitem_notifikasi);
            hargaBarang = view.findViewById(R.id.tv_harga_notifikasi);
        }

        void bindItem(Barang barang){
            Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(imageBarang);
            namaBarang.setText(barang.getNama());
            hargaBarang.setText(barang.getHarga().toString());
        }
    }
}
