package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import android.app.Activity;
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

import java.util.List;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;
import kelompok2.marketplace.com.kuepenjual.ui.edit.UpdateActivity;
import kelompok2.marketplace.com.kuepenjual.ui.home.HomeActivity;

public class BerandaRecyclerViewAdapter extends RecyclerView.Adapter<BerandaRecyclerViewAdapter.BerandaViewHolder> {

    private List<PenjualanBarangList> listBarang;
    private Context context;
    private Intent intent;

    public BerandaRecyclerViewAdapter(List<PenjualanBarangList> listBarang, Context context, Intent intent){
        this.listBarang = listBarang;
        this.context = context;
        this.intent = intent;
    }

    @NonNull
    @Override
    public BerandaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BerandaViewHolder(LayoutInflater.from(context).inflate(R.layout.list_beranda, parent, false), context, intent);
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaViewHolder holder, int position) {
        holder.bindItem(listBarang.get(position).getBarang(), listBarang.get(position).getIdPenjualan());
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    class BerandaViewHolder extends RecyclerView.ViewHolder implements BerandaRecyclerViewView{

        private ImageView imageBarang;
        private TextView namaBarang;
        private TextView hargaBarang;
        private Button btnUpdate;
        private Button btnDelete;
        private Context context;
        private BerandaRecyclerViewPresenter presenter;
        private Intent intent;

        BerandaViewHolder(View view, Context context, Intent intent){
            super(view);
            this.context = context;
            this.presenter = new BerandaRecyclerViewPresenter(this);
            this.intent = intent;
            init(view);
        }

        private void init(View view){
            imageBarang = view.findViewById(R.id.img_barang_beranda);
            namaBarang = view.findViewById(R.id.tv_namabarang_beranda);
            hargaBarang = view.findViewById(R.id.tv_hargabarang_beranda);
            btnUpdate = view.findViewById(R.id.btn_edit_beranda);
            btnDelete = view.findViewById(R.id.btn_sampah_beranda);
        }

        public void bindItem(Barang barang, Integer idPenjualan){
            initBtnUpdate(barang);
            initBtnDelete(barang, idPenjualan);
            Picasso.get().load(BuildConfig.BASE_STORAGE + barang.getGambar()).into(imageBarang);
            namaBarang.setText(barang.getNama());
            Integer harga = barang.getHarga().intValue();
            hargaBarang.setText("RP" + harga.toString());
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
