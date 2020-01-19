package android.eservices.webrequests.data.repository.animedisplay;

import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.data.api.model.AnimeSearchResponse;
import android.eservices.webrequests.data.entity.AnimeEntity;
import android.eservices.webrequests.data.repository.animedisplay.local.AnimeDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.animedisplay.mapper.AnimeToAnimeEntityMapper;
import android.eservices.webrequests.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class AnimeDisplayDataRepository implements AnimeDisplayRepository {

    private AnimeDisplayLocalDataSource animeDisplayLocalDataSource;
    private AnimeDisplayRemoteDataSource animeDisplayRemoteDataSource;
    private AnimeToAnimeEntityMapper animeToAnimeEntityMapper;

    public AnimeDisplayDataRepository(AnimeDisplayLocalDataSource animeDisplayLocalDataSource,
                                        AnimeDisplayRemoteDataSource animeDisplayRemoteDataSource,
                                        AnimeToAnimeEntityMapper animeToAnimeEntityMapper) {
        this.animeDisplayLocalDataSource = animeDisplayLocalDataSource;
        this.animeDisplayRemoteDataSource = animeDisplayRemoteDataSource;
        this.animeToAnimeEntityMapper = animeToAnimeEntityMapper;
    }

    @Override
    public Single<AnimeSearchResponse> getAnimeSearchResponse(String keywords) {
        return animeDisplayRemoteDataSource.getAnimeSearchResponse(keywords)
                .zipWith(animeDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<AnimeSearchResponse, List<String>, AnimeSearchResponse>() {
                    @Override
                    public AnimeSearchResponse apply(AnimeSearchResponse animeSearchResponse, List<String> idList) throws Exception {
                        for (Anime anime : animeSearchResponse.getAnimeList()) {
                            if (idList.contains(anime.getId())) {
                                anime.setFavorite();
                            }
                        }
                        return animeSearchResponse;
                    }
                });
    }

    @Override
    public Flowable<List<AnimeEntity>> getFavoriteAnimes() {
        return animeDisplayLocalDataSource.loadFavorites();
    }

    @Override
    public Flowable<List<AnimeEntity>> getDetails(String id) {
        return animeDisplayLocalDataSource.loadDetails(id);
    }

    @Override
    public Completable removeAnimeDetails(String animeId) {
        return animeDisplayLocalDataSource.deleteAnimeDetails(animeId);
    }

    @Override
    public Completable addAnimeToFavorites(String animeId) {
        return animeDisplayRemoteDataSource.getAnimeDetails(animeId)
                .map(new Function<Anime, AnimeEntity>() {
                    @Override
                    public AnimeEntity apply(Anime anime) throws Exception {
                        return animeToAnimeEntityMapper.map(anime);
                    }
                })
                .flatMapCompletable(new Function<AnimeEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(AnimeEntity animeEntity) throws Exception {
                        return animeDisplayLocalDataSource.addAnimeToFavorites(animeEntity);
                    }
                });
    }

    @Override
    public Completable removeAnimeFromFavorites(String animeId) {
        return animeDisplayLocalDataSource.deleteAnimeFromFavorites(animeId);
    }

    @Override
    public Completable addAnimeDetails(String animeId) {
        return animeDisplayRemoteDataSource.getAnimeDetails(animeId)
                .map(new Function<Anime, AnimeEntity>() {
                    @Override
                    public AnimeEntity apply(Anime anime) throws Exception {
                        return animeToAnimeEntityMapper.map(anime);
                    }
                })
                .flatMapCompletable(new Function<AnimeEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(AnimeEntity animeEntity) throws Exception {
                        return animeDisplayLocalDataSource.addAnimeDetails(animeEntity);
                    }
                });
    }
}
