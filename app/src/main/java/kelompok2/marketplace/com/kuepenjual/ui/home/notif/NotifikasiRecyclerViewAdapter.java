package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kelompok2.marketplace.com.kuepenjual.R;

public class NotifikasiRecyclerViewAdapter extends RecyclerView.Adapter<NotifikasiRecyclerViewAdapter.NotifikasiViewHolder>{

    @NonNull
    @Override
    public NotifikasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notifikasi_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifikasiViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NotifikasiViewHolder extends RecyclerView.ViewHolder{

        public NotifikasiViewHolder(View view){
            super(view);
        }

        void bindItem(){

        }
    }
}
