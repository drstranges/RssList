package com.test.rsslist.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created on 05.10.2015.
 */
@Root(name = "channel", strict = false)
public class Channel {

    @ElementList(name = "item", inline = true)
    List<RssItem> itemList;

    @Element
    private String title;

    @Element
    private String link;

    @Element
    private String description;

    public List<RssItem> getItemList() {
        return itemList;
    }
}