package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import android.content.Context;
import android.content.Intent;
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
import kelompok2.marketplace.com.kuepenjual.ui.edit.UpdateActivity;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder> {

    private ArrayList<PenjualanBarangList> listBarang;
    private Context context;
    public SearchRecyclerViewAdapter(ArrayList<PenjualanBarangList> listBarang,Context context){
        this.listBarang = listBarang;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_search, parent, false), context);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position));
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private TextView namaBarang;
        private ImageView imageBarang;

        public SearchViewHolder(View view, Context context){
            super(view);
            this.context = context;
            initView(view);
        }

        private void initView(View view){
            imageBarang = view.findViewById(R.id.img_fotobarang_list_search);
            namaBarang = view.findViewById(R.id.tv_namabarang_list_search);
        }

        void bindItem(PenjualanBarangList penjualanBarangList){
            final Barang barang = penjualanBarangList.getBarang();
            Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(imageBarang);
            namaBarang.setText(barang.getNama());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("barang", barang);
                    context.startActivity(intent);
                }
            });
        }
    }
}
