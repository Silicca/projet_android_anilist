package android.eservices.webrequests.data.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeSearchResponse {

    @SerializedName("results")
    List<Anime> animeList;

    @SerializedName("last_page")
    int totalItems;

    public List<Anime> getAnimeList() {
        return animeList;
    }

    public int getTotalItems() {
        return totalItems;
    }


}
