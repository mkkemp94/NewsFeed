package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by kempm on 1/8/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    // Constructor
    public NewsAdapter(Context context, ArrayList<News> newsList) {
        super(context, 0, newsList);
    }

    // View holder for initialized views
    private class ViewHolder {
        TextView title;
        TextView date;
        TextView time;
        TextView section;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // This is the view to be built
        View thisView = convertView;

        // Holder to keep views
        ViewHolder holder;

        if (thisView == null) {

            // Not yet created : inflate list item into this view
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            thisView = inflater.inflate(R.layout.news_list_item, parent, false);

            // Assign views inside holder
            holder = new ViewHolder();
            holder.title = (TextView) thisView.findViewById(R.id.tv_title);
            holder.date = (TextView) thisView.findViewById(R.id.tv_date);
            holder.time = (TextView) thisView.findViewById(R.id.tv_time);
            holder.section = (TextView) thisView.findViewById(R.id.tv_section);

            // Save holder
            thisView.setTag(holder);
        } else {

            // Retreive from holder
            holder = (ViewHolder) thisView.getTag();
        }

        // Get this story
        News thisStory = getItem(position);

        // Set webTitle view to display this story's webTitle
        holder.title.setText(thisStory.getWebTitle());

        // Set section view to display this story's section
        holder.section.setText(thisStory.getSectionName());

        // Get the publication date (weird format) and extract what we need
        String unformattedDate = thisStory.getWebPublicationDate();
        String extractedDate = unformattedDate.substring(0, 10) + " " + unformattedDate.substring(11, 16);

        // Create date object out of string in specified format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = format.parse(extractedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Pull date string from date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        String finalDate = dateFormat.format(date);

        // Pull time string from date object
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        String finalTime = timeFormat.format(date);

        // Set date and time views to display this story's publicationDate
        holder.date.setText(finalDate);
        holder.time.setText(finalTime);


        return thisView;
    }

}
