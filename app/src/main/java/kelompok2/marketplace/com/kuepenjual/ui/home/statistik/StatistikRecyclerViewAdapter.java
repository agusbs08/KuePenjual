package kelompok2.marketplace.com.kuepenjual.ui.home.statistik;

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
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public class StatistikRecyclerViewAdapter extends RecyclerView.Adapter<StatistikRecyclerViewAdapter.StatistikViewHolder>{

    private ArrayList<PenjualanBarangList> listBarang;
    public StatistikRecyclerViewAdapter(ArrayList<PenjualanBarangList> listBarang){
        this.listBarang = listBarang;
    }
    @NonNull
    @Override
    public StatistikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StatistikViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ranking, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatistikViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class StatistikViewHolder extends RecyclerView.ViewHolder{

        private TextView ranking;
        private ImageView imageBarang;
        private TextView namaBarang;

        public StatistikViewHolder(View view){
            super(view);
            initView(view);
        }

        private void initView(View view){
            ranking = view.findViewById(R.id.tv_noranking);
            imageBarang = view.findViewById(R.id.img_fotobarang_list_search);
            namaBarang = view.findViewById(R.id.tv_namabarang_list_search);
        }

        void bindItem(PenjualanBarangList penjualanBarangList, Integer noranking){
            Barang barang = penjualanBarangList.getBarang();
            noranking +=1;
            ranking.setText(noranking.toString());
            Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(imageBarang);
            namaBarang.setText(barang.getNama());
        }
    }
}
