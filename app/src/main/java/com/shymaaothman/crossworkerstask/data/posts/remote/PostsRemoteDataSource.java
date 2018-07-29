package com.shymaaothman.crossworkerstask.data.posts.remote;

import com.shymaaothman.crossworkerstask.data.posts.PostsDataSource;
import com.shymaaothman.crossworkerstask.data.server.APIClient;
import com.shymaaothman.crossworkerstask.data.server.Constants;
import com.shymaaothman.crossworkerstask.entities.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Shymaa Othman on 7/23/2018.
 */

public class PostsRemoteDataSource implements PostsDataSource {

    private static PostsRemoteDataSource postsRemoteDataSource ;
    public static PostsDataSource getInstance(){

        if(postsRemoteDataSource == null)
         postsRemoteDataSource = new PostsRemoteDataSource();

        return postsRemoteDataSource ;
    }

    @Override
    public Observable<List<Post>> getPosts(String filter) {

        Map<String,String> headers= new HashMap<>();
        headers.put(Constants.HEADER_NAME,Constants.HEADER_TOKEN);

        Observable<List<Post>>listObservable = APIClient.getEndPoints(headers).getPosts(filter);

        return listObservable;
    }
}
