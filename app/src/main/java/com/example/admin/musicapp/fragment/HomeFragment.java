/*package com.example.admin.musicapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.musicapp.*;
import java.util.Vector;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private OnFragmentInteractionListener mListener;


    public HomeFragment() {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
*/

/*
package com.example.admin.musicapp.fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.musicapp.*;
import java.util.Vector;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class HomeFragment extends FragmentActivity {

    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideos = new Vector<YoutubeVideo>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);




        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new RecyclerView.LayoutManager(
        ) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return null;
            }
        });


        //Load Videos

        youtubeVideos.add(new YoutubeVideo("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/O3Z_LKxPJXQ\" frameborder=\"0\" gesture=\"media\" allow=\"encrypted-media\" allowfullscreen></iframe>"));


        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);

        return view;
    }
}
*/

package com.example.admin.musicapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.musicapp.*;

import com.example.admin.musicapp.*;

import java.util.ArrayList;
import java.util.List;

import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.Constant;
import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.VideoDataSource;
import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.YoutubeVideo;
import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.YoutubeVideoAdapter;
import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.rxbus.RxBus;
import com.example.admin.musicapp.youtuberecyclerview.src.main.java.com.susyimes.youtuberecyclerview.rxbus.SusAction;
import rx.functions.Action1;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mYoutubeVideoRecyclerView;
    private YoutubeVideoAdapter mYoutubeVideoAdapter;
    List<Integer> liststate;
    private List<YoutubeVideo> list;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_vedio, container, false);
        mYoutubeVideoRecyclerView= v.findViewById(R.id.youtube_recyclerview);
        initData();

        initLayoutManager();
        initAdapter();
        initRxBus();

        return v;
    }

    private void initRxBus() {
        RxBus.getDefault().toObserverable(SusAction.class).subscribe(new Action1<SusAction>() {
            @Override
            public void call(SusAction susAction) {
                Log.i("statefrag",susAction.getAction2()+"");
                liststate.set(Constant.fullscreenposition,0);
                mYoutubeVideoAdapter.notifyDataSetChanged();
                mYoutubeVideoAdapter.notifyItemChanged(Constant.fullscreenposition);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    private void initData() {
        list=new ArrayList<>();
        list= VideoDataSource.getVideoList();
    }

    private void initAdapter() {
        Log.i("state","initadapter");
        liststate=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            liststate.add(i,-1);}
        mYoutubeVideoAdapter = new YoutubeVideoAdapter(getContext(),list,getActivity().getSupportFragmentManager(),liststate);
        mYoutubeVideoRecyclerView.setAdapter(mYoutubeVideoAdapter);
    }

    public void initLayoutManager() {
        Log.i("state","initmanager");
       final RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        mYoutubeVideoRecyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onDestroy() {
        Log.i("state","ondestroy");
        if (mYoutubeVideoAdapter != null) {
            mYoutubeVideoAdapter.releaseLoaders();
            mYoutubeVideoAdapter = null;
        }
        super.onDestroy();
    }
}



