package kelompok2.marketplace.com.kuepenjual.model;

import com.google.gson.annotations.SerializedName;

public class IsiKeranjang {

    @SerializedName("id")
    private Integer id;

    @SerializedName("barang")
    private Barang barang;

    @SerializedName("keranjang")
    private Keranjang keranjang;
}
