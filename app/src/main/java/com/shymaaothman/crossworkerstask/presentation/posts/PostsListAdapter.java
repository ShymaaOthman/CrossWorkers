package com.shymaaothman.crossworkerstask.presentation.posts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shymaaothman.crossworkerstask.R;
import com.shymaaothman.crossworkerstask.entities.Content;
import com.shymaaothman.crossworkerstask.entities.Post;
import com.shymaaothman.crossworkerstask.entities.Postfields;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Shymaa Othman on 25/7/2018.
 */

public class PostsListAdapter extends RecyclerView.Adapter<PostsListAdapter.PostsListViewHolder>  {
    private Context mContext;
    private List<Post> postsList;

    class PostsListViewHolder extends RecyclerView.ViewHolder {

        TextView title, price , discount ,expiry_counter;
        ImageView thumb ;

        PostsListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            discount = view.findViewById(R.id.discount);
            expiry_counter = view.findViewById(R.id.expiry_counter);
            thumb = view.findViewById(R.id.thumb);

         }
    }

    public PostsListAdapter(Context mContext, List<Post> postsList) {
        this.mContext = mContext;
        this.postsList = postsList;
    }

    @Override
    public PostsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_cardview, parent, false);
        return new PostsListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PostsListViewHolder holder, final int position) {
      Post post = postsList.get(position);
      holder.title.setText(post.getPostname());

        if(post.getPostfields()!=null) {
            Postfields postfields = post.getPostfields().get(0);
            if (postfields.getContentJson() != null) {
                Content content = postfields.getContentJson();
                holder.price.setText(content.getPrice());
                holder.discount.setText(content.getDiscount());
            }
        }

        Picasso.with(mContext).load(post.getThumbnail().getUrl()).fit().centerCrop()
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.bg)
                    .into(holder.thumb);

        holder.expiry_counter.setText("Expires in "+post.getExpiry_counter()+" days !");

}

    @Override
    public long getItemId(int position) {
        return postsList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

   
}
