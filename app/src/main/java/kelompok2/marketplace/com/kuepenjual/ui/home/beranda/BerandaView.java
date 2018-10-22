package kelompok2.marketplace.com.kuepenjual.ui.home.beranda;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;
import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.PenjualanBarangList;

public interface BerandaView extends BaseView {
    void showListbarang(ArrayList<PenjualanBarangList> listBarang);
}
