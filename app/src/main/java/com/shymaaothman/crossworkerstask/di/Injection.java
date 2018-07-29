package com.shymaaothman.crossworkerstask.di;

import android.support.annotation.NonNull;

import com.shymaaothman.crossworkerstask.data.posts.PostsDataSource;
import com.shymaaothman.crossworkerstask.data.posts.PostsRepository;
import com.shymaaothman.crossworkerstask.data.posts.PostsRepositoryImp;
import com.shymaaothman.crossworkerstask.data.posts.remote.PostsRemoteDataSource;
import com.shymaaothman.crossworkerstask.domain.GetPostsUsecase;
import com.shymaaothman.crossworkerstask.presentation.posts.PostsContract;
import com.shymaaothman.crossworkerstask.presentation.posts.PostsPresenter;

/**
 * Created by Shymaa Othman on 7/26/2018.
 */

public class Injection {

    @NonNull
    private static PostsDataSource providePostsDataSource(){

        return PostsRemoteDataSource.getInstance();
    }
    @NonNull
    private static PostsRepository providePostsRepository(){

        return PostsRepositoryImp.getInstance(providePostsDataSource());
    }
    @NonNull
    public static GetPostsUsecase providePostsUsecse(){

        return new GetPostsUsecase(providePostsRepository());
    }

    @NonNull
    public static PostsContract.PostsPresentr providePostsPresenter(PostsContract.PostsView view){

        return new PostsPresenter(view, providePostsUsecse());
    }

}
