package com.yuzhou.viewer.task;

import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yuzhou.viewer.model.ImageFactory;
import com.yuzhou.viewer.model.ImageItem;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by yuzhou on 2015/07/26.
 */
public class InstagramRestClient
{
    private static InstagramInfo info = new InstagramInfo();

    private AsyncHttpClient client = new AsyncHttpClient();
    private EventBus eventBus;

    public InstagramRestClient(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    public void fetchPopularMedia()
    {
        String url = String.format("https://api.instagram.com/v1/media/popular?client_id=%s", info.getClientId());

        client.get(url, new JsonHttpResponseHandler()
        {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response)
            {
                Log.i("IG_VIEWER", "status code=" + statusCode + ", response=" + response + ", error=" + throwable.getMessage());
                throwable.printStackTrace();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                if (response == null) {
                    Log.i("IG_VIEWER", "Response no context");
                }

                List<ImageItem> items = ImageFactory.create(response);
                eventBus.post(items);
            }
        });
    }

}
