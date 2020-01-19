package android.eservices.webrequests.data.repository.animedisplay;

import android.eservices.webrequests.data.api.model.AnimeSearchResponse;
import android.eservices.webrequests.data.entity.AnimeEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface AnimeDisplayRepository {

    Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords);

    Flowable<List<AnimeEntity>> getFavoriteAnimes();

    Flowable<List<AnimeEntity>> getDetails(String id);

    Completable removeAnimeDetails(String animeId);

    Completable addAnimeToFavorites(String animeId);

    Completable removeAnimeFromFavorites(String animeId);

    Completable addAnimeDetails(String animeId);
}
