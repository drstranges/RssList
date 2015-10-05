package com.test.rsslist.data.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 04.10.2015.
 */


@Root(name = "item", strict = false)
public class RssItem {
    private static final SimpleDateFormat PUB_DATE_FORMAT =
            new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
    private static final String IMG_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
    private static final Pattern IMG_PATTERN = Pattern.compile(IMG_REGEX);

    @Element(name = "title", data = true, required = false)
    private String title;

    @Element(name = "link", required = false)
    private String link;

    @Element(name = "pubDate", required = false)
    private String date;

    @Element(name = "author", required = false)
    private String author;

    @Element(name = "description", data = true, required = false)
    private String description;

    @Element(required = false)
    private String picture;



    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() throws ParseException {
        return PUB_DATE_FORMAT.parse(date);
    }

    public String getAuthor() {
        return author;
    }

    public String getPicture() {
        if (picture == null){
            picture = getPictureFromDescription();
        }
        return picture;
    }

    private String getPictureFromDescription() {
        if (description != null) {
            Matcher matcher = IMG_PATTERN.matcher(description);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return "";
    }
}
