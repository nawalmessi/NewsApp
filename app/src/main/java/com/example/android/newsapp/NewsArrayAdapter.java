package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by High Spec on 10/28/2017.
 */

public class NewsArrayAdapter extends ArrayAdapter<News> {
    public NewsArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        ViewHolder viewHolder;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.sectionName = (TextView) listItem.findViewById(R.id.section_name_text_view);
            viewHolder.webTitle = (TextView) listItem.findViewById(R.id.web_title_text_view);
            viewHolder.publicationDate = (TextView) listItem.findViewById(R.id.web_publication_date_text_view);
            viewHolder.authorName = (TextView) listItem.findViewById(R.id.author_name_text_view);
            listItem.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) listItem.getTag();
        }
        News item = getItem(position);
        viewHolder.sectionName.setText(item.getSectionName());
        viewHolder.webTitle.setText(item.getWebTitle());

        int index = item.getPublicationDate().indexOf("T");
        if (index > 0)
            viewHolder.publicationDate.setText(item.getPublicationDate().substring(0, index));
        viewHolder.authorName.setText(item.getAuthorName());
        return listItem;
    }

    static class ViewHolder {
        TextView sectionName;
        TextView webTitle;
        TextView publicationDate;
        TextView authorName;
    }
}


