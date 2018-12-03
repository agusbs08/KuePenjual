package kelompok2.marketplace.com.kuepenjual.ui.home.statistik;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.R;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public class StatistikFragment extends Fragment implements StatistikView{

    private BarChart barChart;
    private ArrayList<BarEntry> entries;
    private ArrayList<PenjualanBarangList> listBarang;
    private StatistikPresenter presenter;

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        presenter.getListBarang();
//
//        entries.add(new BarEntry(0f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        entries.add(new BarEntry(4f, 20f));
//        entries.add(new BarEntry(5f, 70f));
//        entries.add(new BarEntry(6f, 60f));
//        entries.add(new BarEntry(7f, 80f));
//        entries.add(new BarEntry(8f, 60f));
//        entries.add(new BarEntry(9f, 50f));
//        entries.add(new BarEntry(10f, 20f));
//        entries.add(new BarEntry(11f, 70f));
//        entries.add(new BarEntry(12f, 60f));
    }

    private void initData(){
        entries = new ArrayList<>();
        listBarang = new ArrayList<>();
    }

    private void initChart(){
        BarDataSet set = new BarDataSet(entries,"Penjualan 2018");
        BarData data = new BarData(set);
        barChart.setData(data);
        set.setColors(ColorTemplate.PASTEL_COLORS);
        //String[] mLabels = new String[] { "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agt", "Sep", "Okt", "Nov", "Des" };
        barChart.animateXY(1500, 1500);
        barChart.setFitBars(true);
        barChart.invalidate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistik, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        barChart = view.findViewById(R.id.chart);
        presenter = new StatistikPresenter(this);
    }

    @Override
    public void showListData(ArrayList<PenjualanBarangList> listBarang) {
        entries.clear();
        listBarang = shorData(listBarang);
        for(Integer i=0;i<10;i++){
            entries.add(new BarEntry(i.floatValue(), listBarang.get(i).getBarang().getJumlahTerjual().floatValue()));
        }
        initChart();
    }

    private ArrayList<PenjualanBarangList> shorData(ArrayList<PenjualanBarangList> listBarang){
        for(int i=0;i<listBarang.size()-1;i++){
            for(int j=0;j<listBarang.size()-1;j++){
                PenjualanBarangList barang = listBarang.get(j);
                PenjualanBarangList tes = listBarang.get(j+1);
                if(barang.getBarang().getJumlahTerjual() < tes.getBarang().getJumlahTerjual()){
                    PenjualanBarangList temp = listBarang.get(j);
                    listBarang.set(j, tes);
                    listBarang.set(j+1, temp);
                }
            }
        }
        return listBarang;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}