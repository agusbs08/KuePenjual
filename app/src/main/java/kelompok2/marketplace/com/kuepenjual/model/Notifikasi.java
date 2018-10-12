package kelompok2.marketplace.com.kuepenjual.model;

import com.google.gson.annotations.SerializedName;

public class Notifikasi {
    @SerializedName("id")
    private Integer id;

    @SerializedName("pembeli")
    private Pembeli pembeli;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("tanggal_notif")
    private String tanggalNotif;
}
