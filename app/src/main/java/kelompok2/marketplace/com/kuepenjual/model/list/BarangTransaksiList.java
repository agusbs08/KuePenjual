package kelompok2.marketplace.com.kuepenjual.model.list;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.BarangTransaksi;
import kelompok2.marketplace.com.kuepenjual.model.DetailTransaksi;
import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;

public class BarangTransaksiList {
    @SerializedName("detail_transaksi")
    private DetailTransaksi detailTransaksi;

    @SerializedName("pembeli")
    private Pembeli pembeli;

    @SerializedName("list_barang")
    private ArrayList<BarangTransaksi> listBarang;

    public DetailTransaksi getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(DetailTransaksi detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }

    public Pembeli getPembeli() {
        return pembeli;
    }

    public void setPembeli(Pembeli pembeli) {
        this.pembeli = pembeli;
    }

    public ArrayList<BarangTransaksi> getListBarang() {
        return listBarang;
    }

    public void setListBarang(ArrayList<BarangTransaksi> listBarang) {
        this.listBarang = listBarang;
    }
}
