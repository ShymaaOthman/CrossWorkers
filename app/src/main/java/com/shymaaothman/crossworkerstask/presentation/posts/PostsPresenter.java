package com.shymaaothman.crossworkerstask.presentation.posts;

import com.google.gson.Gson;
import com.shymaaothman.crossworkerstask.data.server.Constants;
import com.shymaaothman.crossworkerstask.domain.GetPostsUsecase;
import com.shymaaothman.crossworkerstask.domain.base_usecase.BaseUseCaseObserver;
import com.shymaaothman.crossworkerstask.entities.Content;
import com.shymaaothman.crossworkerstask.entities.Post;
import com.shymaaothman.crossworkerstask.entities.Postfields;
import com.shymaaothman.crossworkerstask.entities.Postperiods;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shymaa Othman on 7/25/2018.
 */

public class PostsPresenter implements PostsContract.PostsPresentr {

    private PostsContract.PostsView postsView ;
    private GetPostsUsecase postsUsecase ;

    public PostsPresenter (PostsContract.PostsView postsView,
                           GetPostsUsecase postsUsecase){

        this.postsView = postsView ;
        this.postsUsecase = postsUsecase ;

        postsView.setPresenter(this);
    }

    @Override
    public void getPosts(String filter) {

        if(postsView.isActive()) {
            postsView.setLoadingIndicator(true);
            postsUsecase.execute(filter,
                    AndroidSchedulers.mainThread(),
                    Schedulers.io(),
                    new PostsObserver(postsView));
        }
    }

    @Override
    public void start() {

        getPosts(Constants.DEFAULT_FILTER);
    }

    private class PostsObserver extends BaseUseCaseObserver<List<Post>> {
        public PostsObserver(PostsContract.PostsView postsView) {
            super(postsView);
        }

        @Override
        protected void onSuccess(List<Post> postList) {

            postsView.setLoadingIndicator(false);

            List<Post> postsList_edited = manipulatePostsList(postList);

            postsView.showPostsOnSuccess(postsList_edited);
        }
    }

    private List<Post> manipulatePostsList(List<Post> postsList) {

        for (Post post : postsList) {
            Postfields postfields = post.getPostfields().get(0);
            if (postfields.getPostFieldType_id() == 6) {
                Gson gson = new Gson();
                Content content = gson.fromJson(postfields.getContent(), Content.class);
                postfields.setContentJson(content);
            }

            if (post.getPostperiods() != null) {
                Postperiods postperiods = post.getPostperiods().get(0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date firstDate = new Date();
                Date secondDate = null;
                try {
                    secondDate = sdf.parse(postperiods.getStop().split("T")[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                post.setExpiry_counter(diff);
            }
        }
        return postsList ;
    }
}
