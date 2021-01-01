package android.example.expertism.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainComment {

    @SerializedName("author")
    @Expose
    public String author;

    public String text;

    public ArrayList<ReplyComment> replies;
}
