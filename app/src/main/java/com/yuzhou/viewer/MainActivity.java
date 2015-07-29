package com.yuzhou.viewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.yuzhou.viewer.model.ImageItem;
import com.yuzhou.viewer.task.InstagramRestClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    private EventBus eventBus;
    private ListView listView;
    private ImageAdapter adapter;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventBus = new EventBus();
        eventBus.register(this);

        adapter = new ImageAdapter(this, new ArrayList<ImageItem>());
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        new InstagramRestClient(eventBus).fetchPopularMedia();

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new InstagramRestClient(eventBus).fetchPopularMedia();
            }
        });
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(List<ImageItem> items)
    {
        swipeLayout.setRefreshing(false);
        adapter.clear();
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
