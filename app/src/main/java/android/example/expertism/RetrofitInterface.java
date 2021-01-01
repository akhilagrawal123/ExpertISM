package android.example.expertism;

import android.example.expertism.modelClasses.Blog;
import android.example.expertism.modelClasses.GetBlogsByCategoryResult;
import android.example.expertism.modelClasses.LoginResult;
import android.example.expertism.modelClasses.MainComment;
import android.example.expertism.modelClasses.SignupResult;
import android.example.expertism.modelClasses.User;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.PublishedApi;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @POST("/users/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/users/signup")
    Call<SignupResult> executeSignup(@Body HashMap<String, String> map);

    @PATCH("/users/{userId}")
    Call<HashMap<String,String>> executeUpdateProfile(@Header("Authorization") String authorization, @Path("userId") String id, @Body HashMap<String, Object> profileBody);

    @GET("/users/{userId}")
    Call<HashMap<String, User>> executeGetProfile(@Header("Authorization") String authorization, @Path("userId") String id);

    @GET("/users/getInterests/allCat")
    Call<HashMap<String, ArrayList<String>>> executeAllSubCategories(@Header("Authorization") String authorization);

    @PATCH("/users/addBookmarks/{userId}")
    Call<HashMap<String, String>> executeAddBookmark(@Header("Authorization") String authorization, @Path("userId") String id, @Body HashMap<String, String> bookmark);

    @POST("/blogs/")
    Call<HashMap<String, String>> executeCreateBlog(@Header("Authorization") String authorization, @Body HashMap<String, String> createBlogMap);

    @GET("/blogs/{category}")
    Call<GetBlogsByCategoryResult> executeGetBlogByCategory(@Header("Authorization") String authorization, @Path("category") String cat);

    @GET("/blogs/id/{blogId}")
    Call<HashMap<String, Blog>> executeGetBlogById(@Header("Authorization") String authorization, @Path("blogId") String blogId);

    @GET("/blogs/personalised/{userId}")
    Call<HashMap<String, ArrayList<PreviewAdapter.PreviewItem>>> executePersonalisedBlogs(@Header("Authorization") String authorization, @Path("userId") String userId);

    @GET("/blogs/bookmarks/{userId}")
    Call<HashMap<String, ArrayList<PreviewAdapter.PreviewItem>>> executeBookmarkedBlogs(@Header("Authorization") String authorization, @Path("userId") String userId);

    @GET("/blogs/myBlogs/{userId}")
    Call<HashMap<String, ArrayList<PreviewAdapter.PreviewItem>>> executeMyBlogs(@Header("Authorization") String authorization, @Path("userId") String userId);

    @GET("/blogs/createBlog/{category}")
    Call<HashMap<String, ArrayList<String>>> executeGetSubCategoriesListForCreateBlog(@Header("Authorization") String authorization, @Path("category") String  cat);

    @POST("/comments/{blogId}")
    Call<HashMap<String, String>> executePostComment(@Header("Authorization") String authorization,@Path("blogId") String blogId, @Body HashMap<String, String > commentContent);

    @POST("/comments/reply/{commentId}")
    Call<HashMap<String, String>> executePostReply(@Header("Authorization") String authorization,@Path("commentId") String commentId, @Body HashMap<String, String > replyContent);

    @GET("/comments/{blogId}")
    Call<HashMap<String, ArrayList<MainComment>>> executeGetAllComments(@Header("Authorization") String authorization, @Path("blogId") String blogId);

}
