package com.johnfrancis.samplearch.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnfrancis.samplearch.R;
import com.johnfrancis.samplearch.dto.Post;
import com.johnfrancis.samplearch.logger.Log;
import com.johnfrancis.samplearch.ui.adapters.PostListAdapter;

import java.util.List;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SampleFragment extends BaseFragment {


    PostListAdapter postAdapter;

    @BindView(R.id.post_list_view)
    RecyclerView postListView;


    public SampleFragment() {
        // Required empty public constructor
    }

    public static SampleFragment newInstance() {
        SampleFragment sampleFragment = new SampleFragment();
        return sampleFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = super.onCreateView(inflater.inflate(R.layout.fragment_sample, container, false));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        postListView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        postListView.setLayoutManager(llm);

        apiManager
                .getPostList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("post completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("post error");
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        Log.d("post onNext");
                        postAdapter = new PostListAdapter(posts);
                        postListView.setAdapter(postAdapter);
                    }
                });

    }


}
