package org.jscreened.util.gson;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 22-11-2015
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public class Data {

    private String id;
    private String title;
    private String description;
    private long datetime;
    private String type;
    private boolean animated; //gif
    private int width;
    private int height;
    private int size;
    private int views;
    private int bandwith;
    private String vote;
    private boolean favorite;
    private boolean nsfw;
    private String section;
    private String account_url;
    private int account_id;
    private String comment_preview;
    private String deletehash;
    private String name;
    private String link;
    private boolean success;
    private int status;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    public boolean isAnimated() {
        return animated;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return size;
    }

    public int getViews() {
        return views;
    }

    public int getBandwith() {
        return bandwith;
    }

    public String getVote() {
        return vote;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public String getSection() {
        return section;
    }

    public String getAccount_url() {
        return account_url;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getComment_preview() {
        return comment_preview;
    }

    public String getDeletehash() {
        return deletehash;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }
}
