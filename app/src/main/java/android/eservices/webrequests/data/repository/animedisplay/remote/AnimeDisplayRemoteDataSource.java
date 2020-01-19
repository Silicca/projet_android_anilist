package android.eservices.webrequests.data.repository.animedisplay.remote;

import android.eservices.webrequests.AnimeApplication;
import android.eservices.webrequests.data.api.AnimeDisplayService;
import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.data.api.model.AnimeSearchResponse;

import io.reactivex.Single;

public class AnimeDisplayRemoteDataSource {

    private AnimeDisplayService animeDisplayService;

    public AnimeDisplayRemoteDataSource(AnimeDisplayService animeDisplayService) {
        this.animeDisplayService = animeDisplayService;
    }

    public Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords) {
        return animeDisplayService.searchAnimes(keywords);
    }

    public Single<Anime> getAnimeDetails(String animeId) {
        return animeDisplayService.getAnime(animeId);
    }
}
