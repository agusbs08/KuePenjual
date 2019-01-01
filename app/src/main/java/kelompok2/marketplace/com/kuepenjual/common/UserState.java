package kelompok2.marketplace.com.kuepenjual.common;

import android.content.SharedPreferences;

import kelompok2.marketplace.com.kuepenjual.model.Pembeli;
import kelompok2.marketplace.com.kuepenjual.model.Penjual;

public class UserState {
    private static UserState instance = null;

    private Integer idUser;
    private Penjual penjual;
    private UserState(){
        init();
    }

    private void init(){
        idUser = 0;
        penjual = new Penjual();
    }

    public static UserState getInstance(){
        if(instance == null){
            instance = new UserState();
        }
        return instance;
    }

    public Penjual getPenjual() {
        return penjual;
    }

    public void setPenjual(Penjual penjual) {
        this.penjual = penjual;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
