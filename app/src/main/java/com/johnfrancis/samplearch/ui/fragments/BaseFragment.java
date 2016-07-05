package com.johnfrancis.samplearch.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.johnfrancis.samplearch.api.ApiManager;
import com.johnfrancis.samplearch.utils.BusProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john.francis on 15/05/16.
 */
public class BaseFragment extends Fragment {


    public ApiManager apiManager;
    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiManager = new ApiManager(getActivity());

    }

    public View onCreateView(View view) {
        unbinder = ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }

}
