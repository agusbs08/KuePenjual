package kelompok2.marketplace.com.kuepenjual.base;

import kelompok2.marketplace.com.kuepenjual.db.retrofit.DataService;
import kelompok2.marketplace.com.kuepenjual.db.retrofit.RetrofitInstanceClient;
import retrofit2.Retrofit;

public class BasePresenterNetwork {
    public Retrofit retrofit;
    public DataService service;

    public BasePresenterNetwork(){
        this.retrofit = RetrofitInstanceClient.getInstance().getRetrofit();
        this.service = retrofit.create(DataService.class);
    }
}
