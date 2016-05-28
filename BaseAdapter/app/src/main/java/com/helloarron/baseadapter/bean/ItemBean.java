package com.helloarron.baseadapter.bean;

/**
 * Created by arron on 16/5/28.
 */
public class ItemBean {

    public int ItemImageResId;
    public String ItemTitle;
    public String ItemContent;

    public ItemBean(int itemImageResId, String itemTitle, String itemContent) {
        ItemImageResId = itemImageResId;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }
}
