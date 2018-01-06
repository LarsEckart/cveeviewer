package ee.tublipoiss.cveeviewer.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by lars on 21.05.13.
 */
public class JobAd {

    private static final String TAG = JobAd.class.getSimpleName();

    private static final String JSON_KEY_JOBTITLE = "jobTitle";
    private static final String JSON_KEY_EMPLOYER = "employer";
    private static final String JSON_KEY_LOCATION = "location";
    private static final String JSON_KEY_URL = "url";
    private static final String JSON_KEY_PUBDATE = "pubDate";
    private static final String JSON_KEY_DEADLINE = "deadline";

    static SimpleDateFormat FORMATTER =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.ENGLISH);
    // <pubDate>Thu, 18 Apr 2013 11:07:23 +0000</pubDate>

    static SimpleDateFormat FORMATTER_DEADLINE_INPUT =
            new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    // 01/01/2013

    static SimpleDateFormat FORMATTER_DEADLINE_OUTPUT =
            new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH);
    // 01/01/2013

    private String jobTitle;
    private String employer;
    private String location;
    private URL link;
    private Date pubDate;

    private Date deadline;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle.trim();
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(String link) {
        try {
            this.link = new URL(link);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDate() {
        return FORMATTER.format(this.pubDate);
    }

    public void setDate(String date) {
        try {
            this.pubDate = FORMATTER.parse(date.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDeadline() {
        return FORMATTER_DEADLINE_OUTPUT.format(this.deadline);
    }

    public void setDeadline(String deadline) {
        try {
            this.deadline = FORMATTER_DEADLINE_INPUT.parse(deadline.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDeadlineJson(String deadline) {
        try {
            this.deadline = FORMATTER_DEADLINE_OUTPUT.parse(deadline.trim());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public JobAd copy() {
        JobAd copy = new JobAd();
        copy.jobTitle = jobTitle;
        copy.employer = employer;
        copy.location = location;
        copy.link = link;
        copy.pubDate = pubDate;
        copy.deadline = deadline;
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(jobTitle);
        sb.append(" at ");
        sb.append(this.getEmployer());
        return sb.toString();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(JSON_KEY_JOBTITLE, jobTitle);
            jsonObject.put(JSON_KEY_EMPLOYER, employer);
            jsonObject.put(JSON_KEY_LOCATION, location);
            jsonObject.put(JSON_KEY_URL, link.toString());
            jsonObject.put(JSON_KEY_PUBDATE, this.getDate());
            jsonObject.put(JSON_KEY_DEADLINE, this.getDeadline());
        } catch (JSONException e) {
            Timber.e(TAG, e.toString());
        }

        return jsonObject;
    }

    public static JobAd fromJSON(String jsonString) {
        JobAd jobAd = new JobAd();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jobAd.setJobTitle(jsonObject.optString(JSON_KEY_JOBTITLE));
            jobAd.setEmployer(jsonObject.optString(JSON_KEY_EMPLOYER));
            jobAd.setLocation(jsonObject.optString(JSON_KEY_LOCATION));
            jobAd.setLink(jsonObject.optString(JSON_KEY_URL));
            jobAd.setDate(jsonObject.optString(JSON_KEY_PUBDATE));
            jobAd.setDeadlineJson(jsonObject.optString(JSON_KEY_DEADLINE));
        } catch (JSONException e) {
            Timber.e(TAG, e.toString());
        }
        return jobAd;
    }
}