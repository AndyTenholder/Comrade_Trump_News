package com.andytenholder.comradetrump;

/**
 * Created by Andy Tenholder on 3/31/2017.
 */

public class Timeline {

    private String mTimelineDate;
    public String getTimelineDate(){return mTimelineDate;}

    private String mTimelineEvent;
    public String getTimelineEvent() {return mTimelineEvent;}

    private String mTimeLineURL;
    public String getTimeLineURL() {return mTimeLineURL;}

    public Timeline (String TimelineDate, String TimelineEvent, String TimelineURL){
        mTimelineDate = TimelineDate;
        mTimelineEvent = TimelineEvent;
        mTimeLineURL = TimelineURL;
    }
}
