package kelompok2.marketplace.com.kuepenjual.db.retrofit;

import kelompok2.marketplace.com.kuepenjual.model.Barang;
import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;
import kelompok2.marketplace.com.kuepenjual.model.Resep;
import kelompok2.marketplace.com.kuepenjual.model.response.DataResponse;
import kelompok2.marketplace.com.kuepenjual.model.response.ModelResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataService {

    @Headers("Content-Type: application/json")
    @GET("api/barang")
    public Call<DataResponse<Barang>> getBarangsBeranda();

    @GET("api/resep")
    public Call<DataResponse<Resep>> getListResep();

    @GET("api/barang")
    public Call<DataResponse<Barang>> getBarangByKeyword(@Query("keyword") String inputKeyword);

    @FormUrlEncoded
    @POST("api/pembeli/login")
    public Call<ModelResponse<Pembeli>> getUser(@Field("email_pem") String email,
                                                @Field("password_pem") String password);

    @GET("api/penjual")
    public Call<DataResponse<Penjual>> getListPenjual();

    @FormUrlEncoded
    @POST("api/pembeli/getbyemail")
    public Call<ModelResponse<Pembeli>> getUserFromEmail(@Field("email_pem") String email);

    @FormUrlEncoded
    @POST("api/pembeli")
    public Call<ModelResponse<Pembeli>> registerPembeli(@Field("username_pem") String username,
                                                        @Field("password_pem") String password,
                                                        @Field("email_pem") String email,
                                                        @Field("notlp_pem") String noHp);

}
