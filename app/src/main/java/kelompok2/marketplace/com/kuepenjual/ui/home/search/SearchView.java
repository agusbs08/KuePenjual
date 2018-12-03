package kelompok2.marketplace.com.kuepenjual.ui.home.search;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public interface SearchView extends BaseView{
    void showListBarang(ArrayList<PenjualanBarangList> listBarang);
}
