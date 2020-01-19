package android.eservices.webrequests.presentation.animedisplay.favorite.adapter;

public class AnimeDetailViewModel {

    private String animeId;
    private String animeImageUrl;
    private String animeTitle;
    private String animeScore;
    private String animeStartDate;
    private String animeEndDate;
    private String animeType;
    private String animeSynopsis;
    private String animeEpisodes;

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

    public void setAnimeScore(double animeScore) {
        this.animeScore = Double.toString(animeScore);
    }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public String getAnimeStartDate() {
        return animeStartDate;
    }

    public void setAnimeStartDate(String animeStartDate) {
        this.animeStartDate = animeStartDate;
    }

    public String getAnimeEndDate() {
        return animeEndDate;
    }

    public void setAnimeEndDate(String animeEndDate) {
        this.animeEndDate = animeEndDate;
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

    public void setAnimeEpisodes(int animeEpisodes) {
        this.animeEpisodes = Integer.toString(animeEpisodes);
    }
}
