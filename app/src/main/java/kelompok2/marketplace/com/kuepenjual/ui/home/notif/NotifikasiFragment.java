package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.common.UserState;
import kelompok2.marketplace.com.kuepenjual.model.list.BarangTransaksiList;

public class NotifikasiFragment extends Fragment implements NotifikasiView{

    private ArrayList<BarangTransaksiList> barangTransaksiLists;
    private RecyclerView recyclerView;
    private NotifikasiRecyclerViewAdapter adapter;
    private NotifikasiPresenter presenter;
    private ProgressBar pb;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hideLoading();
        presenter.getListTransaksi(UserState.getInstance().getPenjual().getId());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifikasi, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        pb = view.findViewById(R.id.pb_notif);
        recyclerView = view.findViewById(R.id.recyclerView);
        barangTransaksiLists = new ArrayList<>();
        adapter = new NotifikasiRecyclerViewAdapter(barangTransaksiLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        presenter = new NotifikasiPresenter(this);
    }

    @Override
    public void showListTransaksi(ArrayList<BarangTransaksiList> barangTransaksiLists) {
        this.barangTransaksiLists.clear();
        this.barangTransaksiLists.addAll(barangTransaksiLists);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }
}
