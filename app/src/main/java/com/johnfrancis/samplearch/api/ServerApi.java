package com.johnfrancis.samplearch.api;

import com.johnfrancis.samplearch.dto.Post;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by john.francis on 14/05/16.
 */
public interface ServerApi {

    @GET("/posts")
    Observable<List<Post>> getPosts();

    @GET("/posts/{id}")
    Observable<Post> getPostById(@Path("id") int id);

    @PUT("/posts/{id}")
    Observable<Post> putPostById(@Path("id") int id, @Body Post post);

}
