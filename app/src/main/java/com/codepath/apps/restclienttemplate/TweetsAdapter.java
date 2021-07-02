package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Entities;
import com.codepath.apps.restclienttemplate.models.TimelineActivity;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;


public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;
    //Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }
    //For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        //Get the data at position
       Tweet tweet = tweets.get(position);
        //Bind the tweet with view holder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
    //Pass in the context and list of tweets

    //For each row, inflate the layout for a tweet

    //Bind values based on the position of the element

    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView timeAgo;
        ImageView mediaImage;
        TextView retweetCount;
        TextView likeCount;
        ImageButton btReply;
        //TextView hashtags;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            timeAgo = itemView.findViewById(R.id.timeAgo);
            mediaImage = itemView.findViewById(R.id.mediaImage);
            retweetCount = itemView.findViewById(R.id.retweetCount);
            likeCount = itemView.findViewById(R.id.likeCount);
           // hashtags = itemView.findViewById(R.id.hashtag);
            btReply = itemView.findViewById(R.id.replyBtn);

        }
        public void bind(final Tweet tweet){
            tvBody.setText(tweet.body);
            tvBody.append(tweet.hashtag);
            tvScreenName.setText(tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            timeAgo.setText(tweet.createdAt);


            if(tweet.entities.isEmpty()){
                mediaImage.setVisibility(View.GONE);
            }else{
                mediaImage.setVisibility(View.VISIBLE);
                Glide.with(context).load(tweet.entities.getMedia(0)).into(mediaImage);

            }
            likeCount.setText(String.format(Locale.ENGLISH, "%d",tweet.likes));
            retweetCount.setText(String.format(Locale.ENGLISH, "%d",tweet.retweet));

            btReply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ComposeActivity.class);
                    intent.putExtra("refrencing", tweet.user.screenName);
                    ((Activity) context).startActivityForResult(intent, TimelineActivity.REQUEST_COMPOSE);
                }
            });

        }
    }
    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }
}
