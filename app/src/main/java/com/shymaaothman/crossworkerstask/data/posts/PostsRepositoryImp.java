package com.shymaaothman.crossworkerstask.data.posts;

import com.shymaaothman.crossworkerstask.data.posts.remote.PostsRemoteDataSource;
import com.shymaaothman.crossworkerstask.entities.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Shymaa on 7/23/2018.
 */

public class PostsRepositoryImp implements PostsRepository {

    private static PostsDataSource postsDataSource ;
    private static PostsRepository postsRepository ;

    private PostsRepositoryImp(PostsDataSource postsDataSource){

        this.postsDataSource = postsDataSource ;
    }

    public static PostsRepository getInstance(PostsDataSource postsDataSource){

        if(postsRepository == null)
        postsRepository = new PostsRepositoryImp(postsDataSource);

        return postsRepository ;
    }

    @Override
    public Observable<List<Post>> getPosts(String filter) {

        Observable<List<Post>> listObservable = postsDataSource.getPosts(filter);



        return listObservable;
    }
}
