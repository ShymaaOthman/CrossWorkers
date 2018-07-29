package com.shymaaothman.crossworkerstask.presentation.posts;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shymaaothman.crossworkerstask.R;
import com.shymaaothman.crossworkerstask.data.server.Constants;
import com.shymaaothman.crossworkerstask.data.server.GenerericResponse;
import com.shymaaothman.crossworkerstask.di.Injection;
import com.shymaaothman.crossworkerstask.entities.Post;
import com.shymaaothman.crossworkerstask.presentation.utils.InternetConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends Activity implements PostsContract.PostsView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        injectDependency();
        initViews();
    }


    PostsContract.PostsPresentr postsPresentr ;
    private void injectDependency() {

        postsPresentr = Injection.providePostsPresenter(this);
        ButterKnife.bind(this);

    }


    @BindView(R.id.posts_recyclerview)
    RecyclerView posts_recyclerview ;
    @BindView(R.id.progressBar)
    ProgressBar progressBar ;
    @BindView(R.id.swipPostsList)
    SwipeRefreshLayout swipPostsLayout ;
    List<Post> postsList ;
    PostsListAdapter postsListAdapter ;

    private void initViews() {
        postsList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        posts_recyclerview.setLayoutManager(mLayoutManager);
        swipPostsLayout.setOnRefreshListener(new OnRefreshPostsList());


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(InternetConnectionDetector.isConnectingToInternet(PostsActivity.this) == true)
            postsPresentr.start();
        else
            showSnackbar(PostsActivity.this.getResources().getString(R.string.check_internet));

    }

    @Override
    public void setPresenter(PostsContract.PostsPresentr presenter) {

        postsPresentr = presenter ;

    }

    @Override
    public void onServerError(GenerericResponse resp) {

      showSnackbar(resp.getMessage());
    }

    @Override
    public void onUnknownError(String message) {
        showSnackbar(message);
    }

    @Override
    public void setLoadingIndicator(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void showPostsOnSuccess(List<Post> postList) {

        postsList = postList ;
        postsListAdapter = new PostsListAdapter(this,postsList);
        posts_recyclerview.setAdapter(postsListAdapter);
    }

    private class OnRefreshPostsList implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
            if(InternetConnectionDetector.isConnectingToInternet(PostsActivity.this) == true)
            postsPresentr.getPosts(Constants.DEFAULT_FILTER);
            else
                showSnackbar(PostsActivity.this.getResources().getString(R.string.check_internet));
        }
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(posts_recyclerview,message, Snackbar.LENGTH_LONG).show();

    }
}
