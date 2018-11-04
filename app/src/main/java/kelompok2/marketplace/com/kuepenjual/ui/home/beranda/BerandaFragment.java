package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public class BerandaFragment extends Fragment implements BerandaView{

    private RecyclerView recyclerView;
    private BerandaRecyclerViewAdapter adapter;
    private List<PenjualanBarangList> listBarang = new ArrayList<>();
    private BerandaPresenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getListBarang();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beranda, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView){
        adapter = new BerandaRecyclerViewAdapter(listBarang, getContext());
      //  recyclerView = rootView.findViewById(R.id.rv_barang_beranda);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        presenter = new BerandaPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showListbarang(ArrayList<PenjualanBarangList> listBarang) {
        this.listBarang.clear();
        this.listBarang.addAll(listBarang);
        adapter.notifyDataSetChanged();
    }
}
