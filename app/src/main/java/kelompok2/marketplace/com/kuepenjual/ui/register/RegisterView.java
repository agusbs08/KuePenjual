package kelompok2.marketplace.com.kuepenjual.ui.register;

import kelompok2.marketplace.com.kuepenjual.base.BaseView;

public interface RegisterView extends BaseView {
    void showError();
    void actionRegisterSuccess(Integer id);
    void actionRegisterFailed();
}
