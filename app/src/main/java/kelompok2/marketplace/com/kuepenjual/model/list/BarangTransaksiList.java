package kelompok2.marketplace.com.kuepenjual.model.list;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.DetailTransaksi;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;

public class BarangTransaksiList {
    @SerializedName("detail_transaksi")
    private DetailTransaksi detailTransaksi;

    @SerializedName("penjual")
    private Penjual penjual;

    @SerializedName("list_barang")
    private ArrayList<Barang> listBarang;

    public DetailTransaksi getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(DetailTransaksi detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }

    public Penjual getPenjual() {
        return penjual;
    }

    public void setPenjual(Penjual penjual) {
        this.penjual = penjual;
    }

    public ArrayList<Barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(ArrayList<Barang> listBarang) {
        this.listBarang = listBarang;
    }
}
