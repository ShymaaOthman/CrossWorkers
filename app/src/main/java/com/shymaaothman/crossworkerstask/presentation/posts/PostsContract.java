package com.shymaaothman.crossworkerstask.presentation.posts;

import com.shymaaothman.crossworkerstask.entities.Post;
import com.shymaaothman.crossworkerstask.presentation.base.BasePresenter;
import com.shymaaothman.crossworkerstask.presentation.base.BaseView;

import java.util.List;

/**
 * Created by shimaa on 7/25/2018.
 */

public class PostsContract {

    public interface PostsPresentr extends BasePresenter{

       void getPosts(String filter);
    }

    public interface PostsView extends BaseView<PostsPresentr>{

       void showPostsOnSuccess(List<Post>postList);

       void showSnackbar(String message);

    }
}
