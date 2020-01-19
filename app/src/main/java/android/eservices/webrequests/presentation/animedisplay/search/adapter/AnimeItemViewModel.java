package android.eservices.webrequests.presentation.animedisplay.search.adapter;

public class AnimeItemViewModel {

    private String animeId;
    private String iconUrl;
    private String animeTitle;
    private String animeType;
    private String animeTitleJapanese;
    private boolean isFavorite;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public void setAnimeTitle(String animeTitle) { this.animeTitle = animeTitle; }

    public String getAnimeTitleJapanese() {
        return animeTitleJapanese;
    }

    public void setAnimeTitleJapanese(String animeTitleJapanese) { this.animeTitleJapanese = animeTitleJapanese; }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getAnimeType() {
        return animeType;
    }

    public void setFavorite(String animeType) {
        this.animeType = animeType;
    }
}
