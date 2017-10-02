package com.andytenholder.comradetrump;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Andy Tenholder on 3/28/2017.
 */

public class TimelineFragment extends android.support.v4.app.Fragment {

    public TimelineFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.timeline_list, container, false);

        // Create a list of timeline items
        final ArrayList<Timeline> timelines = new ArrayList<>();

        timelines.add(new Timeline(getString(R.string.timeline_date_4_27_2017), getString(R.string.timeline_event_4_27_2017),"https://www.usatoday.com/story/news/politics/2017/04/27/michael-flynn-donald-trump-elijah-cummings-rt-television-vladimir-putin/100973208/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_25_2017), getString(R.string.timeline_event_4_25_2017),"http://www.politico.com/story/2017/04/25/lawmakers-flynn-did-not-disclose-russia-payments-in-security-clearance-application-237576"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_23_2017), getString(R.string.timeline_event_4_23_2017),"http://www.thedailybeast.com/articles/2017/04/23/senate-trump-russia-probe-has-no-full-time-staff-no-key-witnesses.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_21_2017), getString(R.string.timeline_event_4_21_2017),"http://www.cnn.com/2017/04/21/politics/russia-trump-campaign-advisers-infiltrate/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_19_2017), getString(R.string.timeline_event_4_19_2017),"http://www.reuters.com/article/us-usa-russia-election-exclusive-idUSKBN17L2N3"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_13_2017), getString(R.string.timeline_event_4_13_2017),"http://www.politico.com/story/2017/04/jared-kushner-security-clearance-revoke-democrats-237177"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_12_2017), getString(R.string.timeline_event_4_12_2017),"https://apnews.com/20cfc75c82eb4a67b94e624e97207e23"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_11_2017), getString(R.string.timeline_event_4_11_2017),"https://www.washingtonpost.com/world/national-security/fbi-obtained-fisa-warrant-to-monitor-former-trump-adviser-carter-page/2017/04/11/620192ea-1e0e-11e7-ad74-3a742a6e93a7_story.html?utm_term=.98aa2947c227"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_06_2017), getString(R.string.timeline_event_4_06_2017),"http://bigstory.ap.org/article/0835024244ab40fcb7696d2a1db97f48/trump-campaign-adviser-met-russian-intel-operative"));
        timelines.add(new Timeline(getString(R.string.timeline_date_4_03_2017), getString(R.string.timeline_event_4_03_2017),"http://bigstory.ap.org/article/0835024244ab40fcb7696d2a1db97f48/trump-campaign-adviser-met-russian-intel-operative"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_31_2017), getString(R.string.timeline_event_3_31_2017),"http://www.cnbc.com/2017/03/31/senate-intelligence-committee-turned-down-flynns-request-for-immunity-nbc.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_30_2017), getString(R.string.timeline_event_3_30_2017_1),"http://www.nydailynews.com/news/national/michael-flynn-offers-testify-russia-probes-immunity-article-1.3014556"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_30_2017), getString(R.string.timeline_event_3_30_2017_2),"https://www.nytimes.com/2017/03/30/us/politics/devin-nunes-intelligence-reports.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_29_2017), getString(R.string.timeline_event_3_29_2017),"http://www.cnn.com/2017/03/29/politics/senate-intelligence-committee-conference/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_27_2017), getString(R.string.timeline_event_3_27_2017),"https://www.usatoday.com/story/news/politics/2017/03/27/kushner-meet-senate-intelligence-committee-russia-probe/99691802/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_24_2017), getString(R.string.timeline_event_3_24_2017),"http://www.npr.org/sections/thetwo-way/2017/03/24/521367161/paul-manafort-to-testify-on-russia-as-house-intel-committee-drama-continues"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_22_2017), getString(R.string.timeline_event_3_22_2017),"http://www.cnn.com/2017/03/22/politics/devin-nunes-trump-communications/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_20_2017), getString(R.string.timeline_event_3_20_2017),"https://www.nytimes.com/2017/03/20/us/politics/fbi-investigation-trump-russia-comey.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_16_2017), getString(R.string.timeline_event_3_16_2017_1),"http://forward.com/news/national/366181/exclusive-nazi-allied-group-claims-top-trump-aide-sebastian-gorka-as-sworn/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_16_2017), getString(R.string.timeline_event_3_16_2017_2),"http://www.cnn.com/2017/03/16/politics/michael-flynn-payments-rt-russia-tv/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_10_2017), getString(R.string.timeline_event_3_10_2017),"http://www.cnn.com/2017/03/10/politics/michael-flynn-donald-trump-consulting-firm/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_02_2017), getString(R.string.timeline_event_3_02_2017),"http://www.cnn.com/2017/03/02/politics/democrats-sessions-russia-resignation-call/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_3_01_2017), getString(R.string.timeline_event_3_01_2017),"https://www.washingtonpost.com/world/national-security/sessions-spoke-twice-with-russian-ambassador-during-trumps-presidential-campaign-justice-officials-say/2017/03/01/77205eda-feac-11e6-99b4-9e613afeb09f_story.html?utm_term=.10014d2e684b"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_27_2017), getString(R.string.timeline_event_2_27_2017),"http://www.cnn.com/2017/02/27/politics/devin-nunes-house-russia-investigation/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_24_2017), getString(R.string.timeline_event_2_24_2017),"http://www.cnn.com/2017/02/23/politics/fbi-refused-white-house-request-to-knock-down-recent-trump-russia-stories/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_16_2017), getString(R.string.timeline_event_2_16_2017),"http://www.cnn.com/2017/02/16/politics/fbi-not-expected-to-pursue-charges-against-flynn/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_14_2017), getString(R.string.timeline_event_2_14_2017),"http://www.cnn.com/2017/02/14/politics/donald-trump-aides-russians-campaign/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_13_2017), getString(R.string.timeline_event_2_13_2017),"http://www.cnn.com/2017/02/13/politics/michael-t-flynn-resignation-letter/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_10_2017), getString(R.string.timeline_event_2_10_2017),"http://edition.cnn.com/2017/02/10/politics/flynn-russia-us-sanctions-reports/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_09_2017), getString(R.string.timeline_event_2_09_2017),"http://www.cnn.com/2017/02/14/politics/donald-trump-administration-michael-flynn/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_2_08_2017), getString(R.string.timeline_event_2_08_2017),"http://www.cnn.com/2017/02/08/politics/jeff-sessions-vote-senate-slog/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_26_2017), getString(R.string.timeline_event_1_26_2017),"http://www.cnn.com/2017/02/13/politics/michael-flynn-justice-department-warning/index.html"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_23_2017), getString(R.string.timeline_event_1_23_2017),"http://edition.cnn.com/2017/01/15/politics/mike-pence-flynn-trump-russia-contacts/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_15_2017), getString(R.string.timeline_event_1_15_2017),"http://edition.cnn.com/2017/01/15/politics/mike-pence-flynn-trump-russia-contacts/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_13_2017), getString(R.string.timeline_event_1_13_2017),"http://www.cnn.com/2017/01/13/politics/donald-trump-michael-flynn-russia-ambassador/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_12_2017), getString(R.string.timeline_event_1_12_2017),"https://www.washingtonpost.com/opinions/why-did-obama-dawdle-on-russias-hacking/2017/01/12/75f878a0-d90c-11e6-9a36-1d296534b31e_story.html?tid=a_inl&utm_term=.c2743b75a378"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_10_2017), getString(R.string.timeline_event_1_10_2017),"http://www.cnn.com/2017/03/02/politics/russia-jeff-sessions-confirmation-hearing/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_1_06_2017), getString(R.string.timeline_event_1_06_2017),"https://www.dni.gov/files/documents/ICA_2017_01.pdf"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_29_2016), getString(R.string.timeline_event_12_29_2016_1),"http://www.npr.org/2017/01/14/509780474/trump-team-top-advisor-spoke-to-russia-official-before-u-s-sanctions-were-announ"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_29_2016), getString(R.string.timeline_event_12_29_2016_2),"http://www.cnn.com/2016/12/29/politics/russia-sanctions-announced-by-white-house/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_28_2016), getString(R.string.timeline_event_12_28_2016),"http://www.cnn.com/2017/01/13/politics/donald-trump-michael-flynn-russia-ambassador/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_25_2016), getString(R.string.timeline_event_12_25_2016),"http://www.cnn.com/2017/01/13/politics/donald-trump-michael-flynn-russia-ambassador/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_19_2016), getString(R.string.timeline_event_12_19_2016),"http://www.npr.org/2017/01/13/509670980/trump-team-top-adviser-talked-with-russian-ambassador-before-u-s-hacking-respons"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_12_2016), getString(R.string.timeline_event_12_12_2016),"http://www.cnn.com/2016/12/12/politics/gop-russia-hacking-trump/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_12_01_2016), getString(R.string.timeline_event_12_01_2016),"http://www.cnn.com/2016/12/01/politics/paul-manafort-donald-trump-transition/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_11_18_2016), getString(R.string.timeline_event_11_18_2016),"http://www.cnn.com/2016/11/17/politics/trump-offers-flynn-job-of-national-security-adviser/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_10_07_2016), getString(R.string.timeline_event_10_07_2016),"http://www.cnn.com/2016/10/07/politics/john-podesta-emails-hacked/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_10_04_2016), getString(R.string.timeline_event_10_04_2016),"https://twitter.com/rogerjstonejr/status/783532251690704896"));
        timelines.add(new Timeline(getString(R.string.timeline_date_09_08_2016), getString(R.string.timeline_event_09_08_2016),"https://www.washingtonpost.com/world/national-security/sessions-spoke-twice-with-russian-ambassador-during-trumps-presidential-campaign-justice-officials-say/2017/03/01/77205eda-feac-11e6-99b4-9e613afeb09f_story.html?utm_term=.b143387bc567"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_21_2016), getString(R.string.timeline_event_08_21_2016),"https://twitter.com/RogerJStoneJr/status/767366825743097856?ref_src=twsrc%5Etfw"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_19_2016), getString(R.string.timeline_event_08_19_2016),"http://www.cnn.com/2016/08/19/politics/donald-trump-campaign-chairman-paul-manafort-resigns/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_17_2016), getString(R.string.timeline_event_08_17_2016),"https://i1.wp.com/stonecoldtruth.com/wp-content/uploads/2017/03/Screen-Shot-2017-03-09-at-8.47.50-PM.png?w=1600"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_14_2016), getString(R.string.timeline_event_08_14_2016),"https://www.nytimes.com/2016/08/15/us/politics/paul-manafort-ukraine-donald-trump.html?_r=1"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_08_2016), getString(R.string.timeline_event_08_08_2016),"https://www.youtube.com/watch?v=r6-qW_PzGEA"));
        timelines.add(new Timeline(getString(R.string.timeline_date_08_05_2016), getString(R.string.timeline_event_08_05_2016),"http://www.breitbart.com/2016-presidential-race/2016/08/05/dear-hillary-dnc-hack-solved-so-now-stop-blaming-russia/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_27_2016), getString(R.string.timeline_event_07_27_2016),"https://www.youtube.com/watch?v=3kxG8uJUsWU"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_25_2016), getString(R.string.timeline_event_07_25_2016),"http://www.cnn.com/2016/07/25/politics/democratic-convention-dnc-emails-russia/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_22_2016), getString(R.string.timeline_event_07_22_2016),"http://www.cnn.com/2016/07/24/politics/dnc-email-leak-wikileaks/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_18_2016), getString(R.string.timeline_event_07_18_2016_1),"http://www.cnn.com/2017/03/01/politics/jeff-sessions-russian-ambassador-meetings/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_18_2016), getString(R.string.timeline_event_07_18_2016_2),"http://www.cnn.com/2017/03/01/politics/jeff-sessions-russian-ambassador-meetings/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_07_08_2016), getString(R.string.timeline_event_07_08_2016),"http://www.telegraph.co.uk/news/2016/07/08/donald-trumps-adviser-slams-american-policy-on-russia-during-spe/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_06_20_2016), getString(R.string.timeline_event_06_20_2016),"http://www.cnn.com/2016/06/20/politics/corey-lewandowski-out-as-trump-campaign-manager/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_06_15_2016), getString(R.string.timeline_event_06_15_2016),"http://www.cnn.com/2016/06/21/politics/dnc-hack-russians-guccifer-claims/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_05_19_2016), getString(R.string.timeline_event_05_19_2016),"http://abcnews.go.com/Politics/trump-campaign-announces-expanded-role-paul-manafort/story?id=39231973"));
        timelines.add(new Timeline(getString(R.string.timeline_date_03_28_2016), getString(R.string.timeline_event_03_28_2016),"https://www.nytimes.com/politics/first-draft/2016/03/28/donald-trump-hires-paul-manafort-to-lead-delegate-effort/"));
        timelines.add(new Timeline(getString(R.string.timeline_date_03_21_2016), getString(R.string.timeline_event_03_21_2016),"https://www.washingtonpost.com/blogs/post-partisan/wp/2016/03/21/a-transcript-of-donald-trumps-meeting-with-the-washington-post-editorial-board/?utm_term=.7b5d2c5cb1d0"));
        timelines.add(new Timeline(getString(R.string.timeline_date_02_29_2016), getString(R.string.timeline_event_02_29_2016),"http://www.cnn.com/2016/02/28/politics/donald-trump-jeff-sessions-endorsement/"));



        // Create an adatper whose data source is a list of itmes. The
        // adapter knows how to create list items for each item in the list.
        TimelineAdapter adapter = new TimelineAdapter(getActivity(), timelines);

        ListView listView = (ListView) rootView.findViewById(R.id.timeline_list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Timeline currentTimeline = timelines.get(i);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri uri = Uri.parse(currentTimeline.getTimeLineURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        return rootView;
    }
}