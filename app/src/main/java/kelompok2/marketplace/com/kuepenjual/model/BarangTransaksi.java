package kelompok2.marketplace.com.kuepenjual.model;

import com.google.gson.annotations.SerializedName;

public class BarangTransaksi {

    @SerializedName("id_pembeli")
    private Integer idPembeli;

    @SerializedName("id_transaksi")
    private Integer idTransaksi;

    @SerializedName("quantity")
    private Integer kuantitas;

    @SerializedName("barang")
    private Barang barang;

    public Integer getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(Integer idPembeli) {
        this.idPembeli = idPembeli;
    }

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Integer getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(Integer kuantitas) {
        this.kuantitas = kuantitas;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
}
