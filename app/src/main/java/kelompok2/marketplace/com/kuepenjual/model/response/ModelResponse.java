package kelompok2.marketplace.com.kuepenjual.model.response;

import com.google.gson.annotations.SerializedName;

public class ModelResponse<MODEL> {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private MODEL model;

    public ModelResponse(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }
}
