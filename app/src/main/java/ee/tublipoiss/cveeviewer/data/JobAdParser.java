package ee.tublipoiss.cveeviewer.data;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ee.tublipoiss.cveeviewer.data.source.remote.RemoteDataSourceResponseConverter;
import timber.log.Timber;

/**
 * Created by lars on 21.05.13.
 */
public class JobAdParser implements RemoteDataSourceResponseConverter {

    static final String TAG = JobAdParser.class.getSimpleName();

    static final String RSS = "rss";
    static final String CHANNEL = "channel";
    static final String ITEM = "item";

    static final String JOB_TITLE = "job_function";
    static final String LINK = "link";
    static final String LOCATION = "category";
    static final String PUB_DATE = "pubDate";
    static final String EMPLOYER = "employer";
    static final String DESCRIPTION = "description";

    @Override
    public List<JobAd> convert(InputStream in) {

        final List<JobAd> jobAds = new ArrayList<JobAd>();
        final JobAd currentJobAd = new JobAd();

        // parser definition
        RootElement root = new RootElement(RSS);
        Element channel = root.getChild(CHANNEL);
        Element item = channel.getChild(ITEM);
        item.getChild(JOB_TITLE).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentJobAd.setJobTitle(body);
            }
        });
        item.getChild(LINK).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentJobAd.setLink(body);
            }
        });
        item.getChild(LOCATION).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentJobAd.setLocation(body);
            }
        });
        item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentJobAd.setDate(body);
            }
        });
        item.getChild(EMPLOYER).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                currentJobAd.setEmployer(body);
            }
        });
        item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener() {
            public void end(String body) {
                int startIndex = body.indexOf("Deadline:");
                String deadline = body;
                if (startIndex != -1) {
                    deadline = body.substring(startIndex + 10, startIndex + 20);
                }
                currentJobAd.setDeadline(deadline);
            }
        });
        item.setEndElementListener(new EndElementListener() {
            public void end() {
                jobAds.add(currentJobAd.copy());
            }
        });

        try {
            Xml.parse(in, Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (IOException e) {
            Timber.e(e.getMessage());
            return Collections.emptyList();
        } catch (SAXException e) {
            Timber.e(e.getMessage());
            return Collections.emptyList();
        }
        return jobAds;
    }
}
