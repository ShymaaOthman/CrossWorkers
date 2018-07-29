package com.shymaaothman.crossworkerstask.domain;

import com.shymaaothman.crossworkerstask.data.posts.PostsRepository;
import com.shymaaothman.crossworkerstask.entities.Post;
import com.shymaaothman.crossworkerstask.domain.base_usecase.BaseUseCase;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Shymaa Othman on 7/23/2018.
 */

public class GetPostsUsecase extends BaseUseCase<String ,List<Post>> {

    private PostsRepository postsRepository ;

    public GetPostsUsecase(PostsRepository postsRepository ){
        this.postsRepository = postsRepository ;
    }

    @Override
    protected Observable<List<Post>> createObservableUseCase(String filter) {

        Observable<List<Post>> listObservable =  postsRepository.getPosts(filter);

        return listObservable;
    }
}
