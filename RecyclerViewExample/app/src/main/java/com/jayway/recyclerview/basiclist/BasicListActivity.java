package com.jayway.recyclerview.basiclist;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jayway.recyclerview.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by per-erik on 16/11/14.
 */
public class BasicListActivity extends ActionBarActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.jayway.recyclerview.R.layout.activity_recycler_view_example);

        mRecyclerView = (RecyclerView) findViewById(com.jayway.recyclerview.R.id.recyclerview);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(getAdapter());

        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("FoldableListActivity.onRefresh");
                swipeView.setRefreshing(true);
                swipeView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("FoldableListActivity.run");
                        swipeView.setRefreshing(false);
                        addData(10, 5);
                    }
                }, 3000);
            }
        });

        getSupportActionBar().setTitle("Basic List");
    }

    private int mEntityCounter = 0;
    private List<BasicListAdapter.Entity> mData = new ArrayList<BasicListAdapter.Entity>();
    private BasicListAdapter basicListAdapter;

    private RecyclerView.Adapter getAdapter() {
        basicListAdapter = new BasicListAdapter(this);
        addData(15, 0);

        basicListAdapter.setData(new ArrayList<BasicListAdapter.Entity>(mData));
        return basicListAdapter;
    }

    private void addData(int add, int del) {
        for (int i = 0; i < del; ++i) {
            int r = (int) (Math.random() * mData.size());
            mData.remove(r);
        }

        for (int i = 0; i < add; ++i) {
            int r = (int) (Math.random() * mData.size());
            mData.add(r, new BasicListAdapter.Entity("Item " + (++mEntityCounter)));
        }

        basicListAdapter.setData(new ArrayList<BasicListAdapter.Entity>(mData));
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}