package kelompok2.marketplace.com.kuepenjual.db.retrofit;

import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.Penjualan;
import kelompok2.marketplace.com.kuepenjual.model.Resep;
import kelompok2.marketplace.com.kuepenjual.model.response.DataResponse;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    @Headers("Content-Type: application/json")
    @GET("api/penjualan/{id}")
    public Call<ModelResponse<Penjualan>> getBarangsBeranda(@Path("id") String idUser);

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

}
