package com.andytenholder.comradetrump;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andy Tenholder on 3/31/2017.
 */

public class TimelineAdapter extends ArrayAdapter<Timeline>{

    public TimelineAdapter(Activity context, ArrayList<Timeline> timeline) {
        super(context, 0, timeline);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.timeline_item, parent, false);
        }

        Timeline currentTimeline = getItem(position);

        String timelineDate = currentTimeline.getTimelineDate();
        TextView timelineDateTextView = (TextView) listItemView.findViewById(R.id.timeline_date_tv);
        timelineDateTextView.setText(timelineDate);

        String timelineEvent = currentTimeline.getTimelineEvent();
        TextView timelineEventTextView = (TextView) listItemView.findViewById(R.id.timeline_event_tv);
        timelineEventTextView.setText(timelineEvent);

        Context context = parent.getContext();
        Typeface redOctoberLightTypeface = Typefaces.get(context,"Red October-Light");
        Typeface backInUSSRTypeface = Typefaces.get(context,"Back_In_the_USSR_DL_k");
        Typeface robotoRegularTypeface = Typefaces.get(context,"Roboto-Regular");
        Typeface robotoBoldTypeface = Typefaces.get(context,"Roboto-Bold");

        timelineDateTextView.setTypeface(robotoBoldTypeface);
        timelineEventTextView.setTypeface(robotoRegularTypeface);

        return listItemView;
    }

}
