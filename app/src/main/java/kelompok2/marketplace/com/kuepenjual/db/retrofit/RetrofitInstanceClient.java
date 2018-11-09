package kelompok2.marketplace.com.kuepenjual.db.retrofit;

import kelompok2.marketplace.com.kuepenjual.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceClient {
    private static RetrofitInstanceClient instance = null;
    private Retrofit retrofit;

    private RetrofitInstanceClient(){
        init();
    }

    private void init(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        retrofit = new Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public static RetrofitInstanceClient getInstance(){
        if(instance == null){
            instance = new RetrofitInstanceClient();
        }
        return instance;
    }
}
