package com.andytenholder.comradetrump;

import android.content.Context;

import java.util.List;

/**
 * Created by Andy Tenholder on 3/24/2017.
 */

public class ArticleLoader extends android.support.v4.content.AsyncTaskLoader<List<Article>> {
    /** Tag for log messages */
    private static final String LOG_TAG = ArticleLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link ArticleLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Article> articles = QueryUtils.fetchBookData(mUrl);
        return articles;
    }
}
