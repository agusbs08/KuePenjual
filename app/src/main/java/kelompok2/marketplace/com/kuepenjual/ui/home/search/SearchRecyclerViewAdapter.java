package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;
import kelompok2.marketplace.com.kuepenjual.ui.edit.UpdateActivity;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchViewHolder> {

    private ArrayList<PenjualanBarangList> listBarang;
    private Context context;
    private Intent intent;

    public SearchRecyclerViewAdapter(ArrayList<PenjualanBarangList> listBarang,Context context, Intent intent){
        this.listBarang = listBarang;
        this.context = context;
        this.intent = intent;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_search, parent, false), context,intent);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position), listBarang.get(position).getIdPenjualan());
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements SearchRecyclerViewView{

        private Context context;
        private TextView namaBarang;
        private ImageView imageBarang;
        private TextView hargaBarang;
        private Button btnUpdate;
        private Button btnDelete;
        private Intent intent;
        private SearchRecyclerViewPresenter presenter;


        public SearchViewHolder(View view, Context context, Intent intent){
            super(view);
            this.context = context;
            this.intent = intent;
            presenter = new SearchRecyclerViewPresenter(this);
            initView(view);
        }

        private void initView(View view){
            imageBarang = view.findViewById(R.id.img_fotobarang_list_search);
            namaBarang = view.findViewById(R.id.tv_namabarang_list_search);
            hargaBarang = view.findViewById(R.id.tv_hargabarang_list_search);
            btnUpdate = view.findViewById(R.id.btn_edit_list_search);
            btnDelete = view.findViewById(R.id.btn_sampah_list_search);
        }

        void bindItem(PenjualanBarangList penjualanBarangList, Integer idPenjualan){
            final Barang barang = penjualanBarangList.getBarang();
            initBtnUpdate(barang);
            initBtnDelete(barang, idPenjualan);
            Integer harga = barang.getHarga().intValue();
            hargaBarang.setText("Rp " + harga.toString());
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

        private void initBtnUpdate(final Barang barang){
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("barang", barang);
                    context.startActivity(intent);
                }
            });
        }

        private void initBtnDelete(final Barang barang, final Integer idPenjualan){
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Apa kamu yakin menghapus " + barang.getNama() + "?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            presenter.deletePenjualan(idPenjualan, barang.getId());
                        }
                    });
                    builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });
        }

        @Override
        public void refreshLayout() {
            Toast.makeText(context, "Barang Sudah Dihapus", Toast.LENGTH_SHORT).show();
            intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        }
    }
}
