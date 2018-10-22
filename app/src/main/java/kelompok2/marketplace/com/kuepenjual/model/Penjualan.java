package kelompok2.marketplace.com.kuepenjual.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Penjualan {

    @SerializedName("penjual")
    private Penjual penjual;

    @SerializedName("list_barang")
    private ArrayList<PenjualanBarangList> listPenjualanBarang;


    public Penjual getPenjual() {
        return penjual;
    }

    public void setPenjual(Penjual penjual) {
        this.penjual = penjual;
    }

    public ArrayList<PenjualanBarangList> getListPenjualanBarang() {
        return listPenjualanBarang;
    }

    public void setListPenjualanBarang(ArrayList<PenjualanBarangList> listPenjualanBarang) {
        this.listPenjualanBarang = listPenjualanBarang;
    }
}
