package kelompok2.marketplace.com.kuepenjual.ui.home.statistik;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public interface StatistikView extends BaseView {
    void showListData(ArrayList<PenjualanBarangList> listBarang);
}
