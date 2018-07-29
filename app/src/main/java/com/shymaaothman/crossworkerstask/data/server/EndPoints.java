package com.shymaaothman.crossworkerstask.data.server;

import com.shymaaothman.crossworkerstask.entities.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Shymaa Othman on 7/24/2018.
 */

public interface EndPoints {


    @GET("posts") //?include=postfields,postperiods,thumbnail
    Observable<List<Post>> getPosts(@Query("include") String filter);

}
