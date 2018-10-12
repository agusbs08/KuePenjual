package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class BerandaRecyclerViewAdapter extends RecyclerView.Adapter<BerandaRecyclerViewAdapter.BerandaViewHolder> {

    @NonNull
    @Override
    public BerandaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BerandaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BerandaViewHolder extends RecyclerView.ViewHolder{

        BerandaViewHolder(View view){
            super(view);
        }
    }
}
