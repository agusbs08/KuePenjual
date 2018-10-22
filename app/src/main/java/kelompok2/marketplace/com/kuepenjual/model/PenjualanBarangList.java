package kelompok2.marketplace.com.kuepenjual.model;

import com.google.gson.annotations.SerializedName;

public class PenjualanBarangList {
    @SerializedName("id_penjualan")
    private Integer idPenjualan;

    @SerializedName("barang")
    private Barang barang;

    public Integer getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(Integer idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
}
