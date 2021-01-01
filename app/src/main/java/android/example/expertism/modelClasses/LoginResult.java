package android.example.expertism.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("profileCompleted")
    @Expose
    public Boolean profileCompleted;

    public String getToken()
    {
        return token;
    }

    public Boolean getProfileCompleted() {
        return profileCompleted;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("id")
    @Expose
    public String id;

}
