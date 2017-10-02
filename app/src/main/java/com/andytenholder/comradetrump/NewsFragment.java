package com.andytenholder.comradetrump;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Andy Tenholder on 3/28/2017.
 */

public class NewsFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Article>>{

    public NewsFragment() {
        // Required empty public constructor
    }

    //Strings to be used to create URL for Guardian API
    private static final String URL_STRING = "http://content.guardianapis.com/search?section=us-news&order-by=newest&q=trump%20AND%20russia&show-fields=thumbnail";
    private static final String URL_KEY = "&api-key=9f7f6b48-62a7-4289-ba77-c1fd6b25a55a";
    private static final String START_DATE = "&from-date=";
    private static final String END_DATE = "&to-date=";


    Context context;

    /**
     * Variable that changes based on the user input to determine which start date should be used.
     * 0 uses date in preferences, 1 uses current date -1, 2 uses current date -7
     */
    int startDateCounter = 2;

    /**
     * Variable that changes based on the user input to determine which end date should be used.
     * 0 uses date in preferences, 1 uses current date
     */
    int endDateCounter = 1;

    /**
     * Constant value for the loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int LOADER_ID = 1;

    /** Adapter for the list of books */
    private ArticleAdapter mAdapter;

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    private DatePreference mDatePreference;

    LoaderManager loaderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.list,container,false);

        context = getActivity();

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.article_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        // Find a reference to the {@link ListView} in the layout
        ListView articleListView = (ListView) rootView.findViewById(R.id.list);

        // Text view that appears when the list of articles comes back empty
        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.emptyView);
        articleListView.setEmptyView(mEmptyStateTextView);

        // Set empty state text for refresh button
        mEmptyStateTextView.setText(R.string.no_articles_custom);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new ArticleAdapter(context, new ArrayList<Article>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        articleListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open the website of the article
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                   @Override
                                                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                       // Find the current item that was clicked
                                                       Article currentArticle = mAdapter.getItem(position);

                                                       // Convert the String URL into a URI object (to pass into the Intent constructor)
                                                       Uri uri = Uri.parse(currentArticle.getWebURL());

                                                       // Create a new intent to view the earthquake URI
                                                       Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);

                                                       // Send the intent to launch a new activity
                                                       startActivity(websiteIntent);
                                                   }
                                               }
        );



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = rootView.findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        //Find the today button in the activity_main
        Button todayButton = (Button) rootView.findViewById(R.id.button_today);
        //Set an OnClickListener on the today button
        todayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                // Set start date counter to 1 to display today's date -1
                startDateCounter = 1;
                // Set end date counter to 1 to display today's date
                endDateCounter = 1;

                // Set empty state text for Today button
                mEmptyStateTextView.setText(R.string.no_articles_today);

                // Reset loader to use the selected dates
                loaderManager.restartLoader(LOADER_ID,null, NewsFragment.this);

            }
        });

        //Find the week button in the activity_main
        Button weekButton = (Button) rootView.findViewById(R.id.button_week);
        //Set an OnClickListener on the week button
        weekButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                // Set start date counter to 1 to display today's date -7
                startDateCounter = 2;
                // Set end date counter to 1 to display today's date
                endDateCounter = 1;

                // Set empty state text for Week button
                mEmptyStateTextView.setText(R.string.no_articles_week);

                // Reset loader to use the selected dates
                loaderManager.restartLoader(LOADER_ID,null, NewsFragment.this);

            }
        });

        //Find the custom button in the activity_main
        Button customButton = (Button) rootView.findViewById(R.id.button_custom);
        //Set an OnClickListener on the custom button
        customButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                // Set start date counter to 1 to display today's date -7
                startDateCounter = 0;
                // Set end date counter to 1 to display today's date
                endDateCounter = 0;

                //Pull up Settings Activity so user can set start and end date preferences
                Intent settingsIntent = new Intent(context, SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });



        return rootView;
    }

    @Override public void onStart(){
        super.onStart();
        loaderManager.restartLoader(LOADER_ID,null, NewsFragment.this);
    }

    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {

        //call the createRequestURL method and pass that string into the BookLoader
        String completedURLString = createRequestURL();

        context = getActivity();

        return new ArticleLoader(context, completedURLString);
    }


    public void onLoadFinished(Loader<List<Article>> loader, List<Article> books) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = getActivity().findViewById(R.id.loading_spinner);
        loadingIndicator.setVisibility(View.GONE);


        // Clear the adapter of previous data
        mAdapter.clear();

        // If there is a valid list of {@link Books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }


    public void onLoaderReset(Loader<List<Article>> loader) {
        // BookLoader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    //Method to use the data for the search field to create a string URL that matches the google books API requirements
    public String createRequestURL (){


        /*
        *Create an attribute set using the data in the settings_main.xml
        * attribute set is then used as input in DatePreference
         */
        Resources r = getResources();
        XmlResourceParser parser = r.getXml(R.xml.settings_main);
        AttributeSet as = Xml.asAttributeSet(parser);

        // Create DatePreference object
        mDatePreference = new DatePreference(context,as);

        //Access Shared Preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        //Create a calendar object of the start date stored in shared preferences
        Calendar startDateCalendar = DatePreference.getDateFor(sharedPrefs, getString(R.string.start_date_key));
        //Create a date object from the calendar object
        Date startDateDate = startDateCalendar.getTime();
        //Set date format ex 2017-02-29
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String to hold start date
        String startDateString;

        /*
        If statement to catch which start date should be used.
        0= date from preferences, 1= today's date -1, else use today's date -7
         */
        if (startDateCounter == 0){
            // format date from shared preferences and place it in string
            startDateString = dateFormat.format(startDateDate);
            startDateCounter = 2;
        }else if(startDateCounter == 1){
            // Create a calendar object of today's date
            Calendar c = Calendar.getInstance();
            // Subtract 1 day from the calendar object
            c.add(Calendar.DATE, -1);
            // Create a Date object of the calendar object
            Date resultdate = new Date(c.getTimeInMillis());
            // Format date object and place it in a string
            startDateString = dateFormat.format(resultdate);
            // Reset date counter to 0 so refresh button will use shared preferences date
            startDateCounter = 2;
        }else{
            // Create a calendar object of today's date
            Calendar c = Calendar.getInstance();
            // Subtract 7 days from the calendar object
            c.add(Calendar.DATE, -7);
            // Create a Date object of the calendar object
            Date resultdate = new Date(c.getTimeInMillis());
            // Format date object and place it in a string
            startDateString = dateFormat.format(resultdate);
            // Reset date counter to 0 so refresh button will use shared preferences date
            startDateCounter = 2;
        }

        // add &from-date= to startDateString to be used to create URL
        startDateString = START_DATE + startDateString;

        //Create a calendar object of the end date stored in shared preferences
        Calendar endDateCalendar = DatePreference.getDateFor(sharedPrefs, getString(R.string.end_date_key));
        //Create a date object from the calendar object
        Date endDateDate = endDateCalendar.getTime();

        // String to hold end date
        String endDateString;

        /*
        If statement to catch which end date should be used.
        0= date from preferences else use today's date
         */
        if (endDateCounter == 0){
            // format date from shared preferences and place it in string
            endDateString = dateFormat.format(endDateDate);
            endDateCounter = 1;
        }else {
            // Create a date object of today's date
            Date todaysDate = new Date();
            // Format date object and place it in string
            endDateString = dateFormat.format(todaysDate);
            // Reset date counter to 0 so refresh button will use shared preferences date
            endDateCounter = 1;
        }

        // add &to-date= to endDateString to be used to create URL
        endDateString = END_DATE + endDateString;



        //Put pieces of the URL together into a single string
        String completeURLString = URL_STRING + startDateString + endDateString + URL_KEY;

        Log.e("my_tag", "The value of completeURLString is :" +completeURLString);

        return completeURLString;

    }

}
