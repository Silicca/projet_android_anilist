package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

public class AnimeDetails {

    @SerializedName("mal_id")
    private String id;

    private String title;

    @SerializedName("title_japanese")
    private String titleJapanese;

    @SerializedName("image_url")
    private String imageUrl;

    private String synopsis;
    private String type;
    private String episodes;
    private String score;


    private String duration;

    private boolean isFavorite;

    public String getId() {
        return id;
    }

    public void setFavorite() {
        if (isFavorite==true){
            this.isFavorite = false;
        } else {
            this.isFavorite = true;
        }
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleJapanese() {
        return titleJapanese;
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

    public String getEpisodes() {
        return episodes;
    }

    public String getScore() {
        return score;
    }

    public String getDuration() {
        return duration;
    }
}
