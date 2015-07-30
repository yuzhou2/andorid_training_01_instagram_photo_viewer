package com.yuzhou.viewer;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuzhou.viewer.model.Image;
import com.yuzhou.viewer.model.ImageItem;
import com.yuzhou.viewer.model.User;

import java.util.List;

/**
 * Created by yuzhou on 2015/07/26.
 */
public class ImageAdapter extends ArrayAdapter<ImageItem>
{
    public ImageAdapter(Context context, List<ImageItem> photos)
    {
        super(context, R.layout.item_image, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imgImage);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.imgCaption);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.imgLikes);
            viewHolder.profile = (ImageView) convertView.findViewById(R.id.imgProfile);
            viewHolder.creator = (TextView) convertView.findViewById(R.id.imgCreator);
            viewHolder.time = (TextView) convertView.findViewById(R.id.imgTime);

            convertView.setTag(viewHolder);
        }

        ImageItem item = getItem(position);
        User user = item.getUser();
        Image image = item.getThumbnail();

        String sCreator = getContext().getString(R.string.creator);
        String sLikes = getContext().getString(R.string.likes);

        Picasso.with(getContext()).load(image.getUrl()).resize(image.getWidth(), image.getHeight()).into(viewHolder.image);
        Picasso.with(getContext()).load(user.getPictureUrl()).into(viewHolder.profile);

        viewHolder.caption.setText(item.getCaption().getText());
        viewHolder.creator.setText(String.format(sCreator, user.getUserName()));
        viewHolder.likes.setText(String.format(sLikes, item.getLikes().getCount()));
        viewHolder.time.setText(DateUtils.getRelativeTimeSpanString(item.getCreatedTime() * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));

        return convertView;
    }

    private static class ViewHolder
    {
        private ImageView image;
        private ImageView profile;
        private TextView caption;
        private TextView likes;
        private TextView creator;
        private TextView time;
    }

}
