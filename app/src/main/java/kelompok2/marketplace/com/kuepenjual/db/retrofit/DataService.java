package kelompok2.marketplace.com.kuepenjual.db.retrofit;

import java.io.File;

import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.Penjualan;
import kelompok2.marketplace.com.kuepenjual.model.Resep;
import kelompok2.marketplace.com.kuepenjual.model.response.DataResponse;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    @Headers("Content-Type: application/json")
    @GET("api/penjualan/{id}")
    public Call<ModelResponse<Penjualan>> getBarangsBeranda(@Path("id") Integer idUser);

    @GET("api/resep")
    public Call<DataResponse<Resep>> getListResep();

    @GET("api/barang")
    public Call<DataResponse<Barang>> getBarangByKeyword(@Query("keyword") String inputKeyword);

    @FormUrlEncoded
    @POST("api/penjual/login")
    public Call<ModelResponse<Penjual>> getUser(@Field("email_pen") String email,
                                                @Field("password_pen") String password);

    @GET("api/penjual")
    public Call<DataResponse<Penjual>> getListPenjual();

    @FormUrlEncoded
    @POST("api/penjual/getbyemail")
    public Call<ModelResponse<Penjual>> getUserFromEmail(@Field("email_pen") String email);

    @FormUrlEncoded
    @POST("api/penjual")
    public Call<ModelResponse<Penjual>> registerPenjual(@Field("username_pen") String username,
                                                        @Field("password_pen") String password,
                                                        @Field("email_pen") String email,
                                                        @Field("notlp_pen") String noHp);

    @Multipart
    @POST("api/barang")
    public Call<ModelResponse<Barang>> createBarang(@Part("id_kategori") RequestBody idKategori,
                                                    @Part("harga_barang") RequestBody hargabarang,
                                                    @Part("potongan_harga") RequestBody diskon,
                                                    @Part("stok_barang") RequestBody stok,
                                                    @Part("nama_barang") RequestBody nama,
                                                    @Part("merk_barang") RequestBody merk,
                                                    @Part MultipartBody.Part file,
                                                    @Part("rating") RequestBody rating,
                                                    @Part("jumlah_terjual") RequestBody jumlahTerjual);

    @Multipart
    @POST("api/barang/{id_barang}")
    public Call<ModelResponse<Barang>> updateBarang(
                                                @Part("_method") RequestBody reqMethod,
                                                @Part("id_kategori") RequestBody idKategori,
                                                 @Part("harga_barang") RequestBody hargabarang,
                                                 @Part("potongan_harga") RequestBody diskon,
                                                 @Part("stok_barang") RequestBody stok,
                                                 @Part("nama_barang") RequestBody nama,
                                                 @Part("merk_barang") RequestBody merk,
                                                 @Part MultipartBody.Part file,
                                                 @Part("rating") RequestBody rating,
                                                 @Part("jumlah_terjual") RequestBody jumlahTerjual,
                                                 @Path("id_barang") Integer idBarang);

    @Multipart
    @POST("api/barang/{id_barang}")
    public Call<Void> updateBarangVoid(
            @Part("_method") RequestBody reqMethod,
            @Part("id_kategori") RequestBody idKategori,
            @Part("harga_barang") RequestBody hargabarang,
            @Part("potongan_harga") RequestBody diskon,
            @Part("stok_barang") RequestBody stok,
            @Part("nama_barang") RequestBody nama,
            @Part("merk_barang") RequestBody merk,
            @Part MultipartBody.Part file,
            @Part("rating") RequestBody rating,
            @Part("jumlah_terjual") RequestBody jumlahTerjual,
            @Path("id_barang") Integer idBarang);

    @FormUrlEncoded
    @PATCH("api/barang/{id_barang}")
    public Call<Void> updateBarangnoRequest(
          //  @Field("_method") String reqMethod,
            @Field("id_kategori") Integer idKategori,
            @Field("harga_barang") Integer hargabarang,
            @Field("potongan_harga") Integer diskon,
            @Field("stok_barang") Integer stok,
            @Field("nama_barang") String nama,
            @Field("merk_barang") String merk,
            //@Part MultipartBody.Part file,
            @Field("rating") Integer rating,
            @Field("jumlah_terjual") Integer jumlahTerjual,
            @Field("id_barang") Integer idBarang);

    @FormUrlEncoded
    @POST("api/penjualan")
    public Call<ModelResponse<Penjualan>> createPenjualan(@Field("id_penjual") Integer idPenjual,
                                                          @Field("id_barang") Integer idBarang);
    @DELETE("api/penjualan/{id_penjualan}")
    public Call<ModelResponse<Barang>> deletePenjualan(@Path("id_penjualan") Integer idPenjualan);

    @DELETE("api/barang/{id_barang}")
    public Call<ModelResponse<Barang>> deleteBarang(@Path("id_barang") Integer idBarang);
}
