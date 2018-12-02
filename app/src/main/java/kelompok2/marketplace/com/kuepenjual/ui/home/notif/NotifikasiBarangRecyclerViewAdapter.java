package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kelompok2.marketplace.com.kuepenjual.R;

public class NotifikasiBarangRecyclerViewAdapter extends RecyclerView.Adapter<NotifikasiBarangRecyclerViewAdapter.NotifikasiBarangViewHolder> {

    @NonNull
    @Override
    public NotifikasiBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiBarangViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notifikasi_item_barang, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiBarangViewHolder holder, int position) {
        holder.bindItem();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotifikasiBarangViewHolder extends RecyclerView.ViewHolder{
        public NotifikasiBarangViewHolder(View view){
            super(view);
        }

        void bindItem(){

        }
    }
}
