package android.eservices.webrequests.data.entity;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class AnimeEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "mal_id")
    public String id;

    public String title;

    @ColumnInfo(name = "title_japanese")
    public String titleJapanese;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    private String synopsis;
    private String type;
    private String episodes;
    private String score;
    private boolean favorite = false;

    private String duration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitleJapanese(String title) {
        this.titleJapanese = titleJapanese;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean setIsFavorite(){
        if (favorite==false){
            return true;
        } else {
            return false;
        }
    }

    public boolean getFavorite() {
        return favorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
