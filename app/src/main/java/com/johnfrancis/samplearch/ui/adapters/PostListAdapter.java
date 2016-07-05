package com.johnfrancis.samplearch.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johnfrancis.samplearch.R;
import com.johnfrancis.samplearch.dto.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john.francis on 09/02/16.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    private List<Post> postList;

    public PostListAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public interface PostInteractionListner {
        void postClicked(int id);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_post, viewGroup, false);


        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder postViewHolder, final int position) {
        Post post = postList.get(position);
        postViewHolder.title.setText("Title:" + post.getTitle());
        postViewHolder.body.setText("" + post.getBody());
        postViewHolder.id.setText("" + post.getId());

        postViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public Post getItem(int position) {
        return postList.get(position);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title)
        protected TextView title;
        @BindView(R.id.post_body)
        protected TextView body;
        @BindView(R.id.post_id)
        protected TextView id;

        public PostViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
