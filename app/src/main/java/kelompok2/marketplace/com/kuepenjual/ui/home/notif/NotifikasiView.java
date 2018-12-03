package kelompok2.marketplace.com.kuepenjual.ui.home.notif;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;
import kelompok2.marketplace.com.kuepenjual.model.list.BarangTransaksiList;

public interface NotifikasiView extends BaseView {
    void showListTransaksi(ArrayList<BarangTransaksiList> barangTransaksiLists);
}
