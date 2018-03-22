package com.example.android.news;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Saksham on 03-01-2018.
 */
public class wordadapter extends ArrayAdapter<word> {
    private Context context;
    wordadapter(Activity context, ArrayList<word> earthquake) {
        super(context, 0, earthquake);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list, parent, false);
        }
        word currentearth = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String titleyet = currentearth.getTitle();
        if (currentearth.getTitle().contains("null") || titleyet.isEmpty()) {
            titleyet = "";
        }
        titleView.setText(titleyet);

        TextView descriptionView = (TextView) listItemView.findViewById(R.id.description);
        descriptionView.setText(currentearth.getDescription());
        String desyet = currentearth.getDescription();
        if (currentearth.getDescription().contains("null") || desyet.isEmpty()) {
            desyet = "";
        }
        descriptionView.setText(desyet);

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);

        String authoryet = currentearth.getAuthor();
        if (currentearth.getAuthor().contains("null")) {
            authoryet = "";
        }

        authorView.setText(authoryet);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String timeyet = currentearth.getTime();
        String modifytime = format(timeyet);
        if (modifytime == null) {
            timeView.setText(" ");
        } else {
            if (!currentearth.getAuthor().contains("null")) {
                modifytime = modifytime + " · ";
            } else {
                modifytime = modifytime + " ";
            }
            timeView.setText(modifytime);
        }

        ImageView imag1u=(ImageView)listItemView.findViewById(R.id.image1);
        String u=currentearth.getImageurl();
        if (u.isEmpty()) {
            imag1u.setImageResource(R.drawable.unnamed);
        } else{
            Picasso.with(context).load(u).into(imag1u);
        }


        return listItemView;
    }
    private String format(String t)
    {
        if(t==null)
        {
            return null;
        }
        else {

            String timeago = " ";
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                format.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date past = format.parse(t);
                Date now = new Date();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime());
                long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime());
                long hours = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime());
                long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());

                if (seconds < 60 && seconds>0) {
                    timeago = String.valueOf(seconds);
                    timeago = timeago + " seconds ago";

                } else if (minutes < 60 && minutes >0) {
                    timeago = String.valueOf(minutes);
                    timeago = timeago + " minutes ago";
                } else if (hours < 24 && hours >0) {
                    timeago = String.valueOf(hours);
                    timeago = timeago + " hours ago";
                } else if(minutes>24 && days>0)
                {
                    timeago = String.valueOf(days);
                    timeago = timeago + " days ago";
                }
                else
                {
                    timeago="Few Seconds Ago";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return timeago;
        }
    }
}

