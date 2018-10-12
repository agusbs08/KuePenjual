package kelompok2.marketplace.com.kuepenjual.ui.home.statistik;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class StatistikRecyclerViewAdapter extends RecyclerView.Adapter<StatistikRecyclerViewAdapter.StatistikViewHolder>{

    @NonNull
    @Override
    public StatistikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StatistikViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StatistikViewHolder extends RecyclerView.ViewHolder{

        public StatistikViewHolder(View view){
            super(view);
        }
    }
}
