package com.shymaaothman.crossworkerstask.data.posts;

import com.shymaaothman.crossworkerstask.entities.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Shymaa on 7/23/2018.
 */

public interface PostsRepository {

    Observable<List<Post>> getPosts(String filter);

}
