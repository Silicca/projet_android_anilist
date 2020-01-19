package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

public class Anime {

    @SerializedName("mal_id")
    private String id;

    private String title;

    @SerializedName("image_url")
    private String imageUrl;

    private String synopsis;
    private String type;
    private int episodes;
    private double score;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    private String endDate;

    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public void setFavorite() {
        if (isFavorite==true){
            isFavorite = false;
        } else {
            isFavorite = true;
        }
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getType() {
        return type;
    }

    public int getEpisodes() {
        return episodes;
    }

    public double getScore() {
        return score;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() { return endDate; }
}
