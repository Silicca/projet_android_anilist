package android.eservices.webrequests.presentation.animedisplay.favorite.adapter;

public class AnimeDetailViewModel {

    private String animeId;
    private String animeImageUrl;
    private String animeTitle;
    private String animeTitleJapanese;
    private String animeScore;
    private String animeType;
    private String animeSynopsis;
    private String animeEpisodes;
    private String animeDuration;

    public String getAnimeImageUrl() {
        return animeImageUrl;
    }

    public void setAnimeImageUrl(String animeImageUrl) {
        this.animeImageUrl = animeImageUrl;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public void setAnimeTitle(String animeTitle) {
        this.animeTitle = animeTitle;
    }

    public String getAnimeScore() {
        return animeScore;
    }

    public void setAnimeScore(String animeScore) { this.animeScore = animeScore; }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public String getAnimeType() {
        return animeType;
    }

    public void setAnimeType(String animeType) {
        this.animeType = animeType;
    }

    public String getAnimeSynopsis() {
        return animeSynopsis;
    }

    public void setAnimeSynopsis(String animeSynopsis) {
        this.animeSynopsis = animeSynopsis;
    }

    public String getAnimeEpisodes() {
        return animeEpisodes;
    }

    public void setAnimeEpisodes(String animeEpisodes) { this.animeEpisodes = animeEpisodes; }

    public String getAnimeTitleJapanese() {
        return animeTitleJapanese;
    }

    public void setAnimeTitleJapanese(String animetitleJapanese) {
        this.animeTitleJapanese = animetitleJapanese;
    }

    public String getAnimDuration() {
        return animeDuration;
    }

    public void setAnimeDuration(String animeDuration) { this.animeDuration = animeDuration; }
}
