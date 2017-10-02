package com.andytenholder.comradetrump;

/**
 * Created by Andy Tenholder on 3/24/2017.
 */

public class Article {

    private String mWebTitle;
    public String getWebTitle() {return mWebTitle;}

    private String mWebURL;
    public String getWebURL() {return mWebURL;}

    private String mSectionName;
    public String getSectionName() {return mSectionName;}

    private String mWebPublicationDate;
    public String getWebPublicationDate() {return mWebPublicationDate;}

    private String mCoverTumbnail;
    public String getCoverThumbnail() {return mCoverTumbnail;}

    public Article (String webTitle, String webURL, String sectionName, String webPublicationDate, String coverThumbnail) {
        mWebTitle = webTitle;
        mWebURL = webURL;
        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mCoverTumbnail = coverThumbnail;
    }

}
