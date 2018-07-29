package com.shymaaothman.crossworkerstask.data.posts;

import java.util.List;
import com.shymaaothman.crossworkerstask.entities.Post;

import io.reactivex.Observable;

/**
 * Created by Shymaa Othman on 7/23/2018.
 */

public interface PostsDataSource {

    Observable<List<Post>> getPosts(String filter);
}
