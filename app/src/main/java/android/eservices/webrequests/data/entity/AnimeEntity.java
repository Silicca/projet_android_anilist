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

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    private String synopsis;
    private String type;
    private int episodes;
    private double score;
    private boolean favorite = false;

    @ColumnInfo(name = "start_date")
    private String startDate;

    @ColumnInfo(name = "end_date")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        Log.d(AnimeEntity.class.getName(),"Title in BDD");
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

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
