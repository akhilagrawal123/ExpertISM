package android.example.expertism.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupResult {

    @SerializedName("message")
    @Expose
    public String message;
}
