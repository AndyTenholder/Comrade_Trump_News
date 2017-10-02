package com.andytenholder.comradetrump;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Andy Tenholder on 3/24/2017.
 */

public class ArticleAdapter extends ArrayAdapter <Article>{

    public ArticleAdapter(Context context, ArrayList<Article> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Article currentArticle = getItem(position);



        String articleTitle = currentArticle.getWebTitle();
        TextView articleTitleTextView = (TextView) convertView.findViewById(R.id.article_title);
        articleTitleTextView.setText(articleTitle);

        String articleSection = currentArticle.getSectionName();
        TextView articleSectionTextView = (TextView) convertView.findViewById(R.id.article_section);
        articleSectionTextView.setText(articleSection);

        //TODO Format date to something easier to read
        String dateObject = currentArticle.getWebPublicationDate();
        TextView articleDateTextView = (TextView) convertView.findViewById(R.id.article_date);;
        articleDateTextView.setText(dateObject);

        String coverThumnail = currentArticle.getCoverThumbnail();
        Context context = parent.getContext();
        ImageView bookCoverImageView = (ImageView) convertView.findViewById(R.id.article_thumbnail);
        Picasso.with(context).load(coverThumnail).into(bookCoverImageView);

        Typeface redOctoberLightTypeface = Typefaces.get(context,"Red October-Light");
        Typeface backInUSSRTypeface = Typefaces.get(context,"Back_In_the_USSR_DL_k");
        Typeface robotoRegularTypeface = Typefaces.get(context,"Roboto-Regular");
        Typeface robotoBoldTypeface = Typefaces.get(context,"Roboto-Bold");

        articleTitleTextView.setTypeface(robotoBoldTypeface);
        articleSectionTextView.setTypeface(robotoRegularTypeface);
        articleDateTextView.setTypeface(robotoRegularTypeface);

        return convertView;
    }


}


