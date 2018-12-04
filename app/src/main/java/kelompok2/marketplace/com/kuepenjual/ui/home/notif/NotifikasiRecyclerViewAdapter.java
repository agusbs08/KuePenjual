package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.BarangTransaksi;
import kelompok2.marketplace.com.kuepenjual.model.list.BarangTransaksiList;

public class NotifikasiRecyclerViewAdapter extends RecyclerView.Adapter<NotifikasiRecyclerViewAdapter.NotifikasiViewHolder>{


    private ArrayList<BarangTransaksiList> barangTransaksiLists;

    public NotifikasiRecyclerViewAdapter(ArrayList<BarangTransaksiList> barangTransaksiLists){
        this.barangTransaksiLists = barangTransaksiLists;
    }

    @NonNull
    @Override
    public NotifikasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notifikasi_item, parent, false) );
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiViewHolder holder, int position) {
        holder.bindItem(barangTransaksiLists.get(position));
    }

    @Override
    public int getItemCount() {
        return barangTransaksiLists.size();
    }

    class NotifikasiViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recyclerView;
        private NotifikasiBarangRecyclerViewAdapter adapter;
        private Context context;
        private TextView namaPembeli;
        private TextView totalHarga;

        public NotifikasiViewHolder(View view){
            super(view);
            this.context = view.getContext();
            initView(view);
        }

        private void initView(View view){
            recyclerView = view.findViewById(R.id.recyclerView);
            namaPembeli = view.findViewById(R.id.tv_namapembeli_notifikasi);
            totalHarga = view.findViewById(R.id.tv_totalharga_notif);
        }

        void bindItem(BarangTransaksiList barangTransaksiList){
            if(adapter == null){
                adapter = new NotifikasiBarangRecyclerViewAdapter(barangTransaksiList.getListBarang());
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(adapter);
            }
            namaPembeli.setText(barangTransaksiList.getPembeli().getNama());
            totalHarga.setText("Rp " + getTotalHarga(barangTransaksiList));
        }

        private String getTotalHarga(BarangTransaksiList barangTransaksiList){
            Float total = new Float(0);
            for(BarangTransaksi barangTransaksi : barangTransaksiList.getListBarang()){
                total += barangTransaksi.getBarang().getHarga();
            }
            Integer harga =total.intValue();
            return harga.toString();
        }
    }
}
