package android.eservices.webrequests.data.api;

import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.data.api.model.AnimeSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeDisplayService {
    @GET("search/anime")
    Single<AnimeSearchResponse> searchAnimes(@Query("q") String keywords);

    @GET("anime/{id}")
    Single<Anime> getAnime(@Path("id") String animeId);
}
