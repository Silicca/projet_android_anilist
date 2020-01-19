package android.eservices.webrequests.data.repository.animedisplay.local;

import android.eservices.webrequests.data.db.AnimeDatabase;
import android.eservices.webrequests.data.entity.AnimeEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class AnimeDisplayLocalDataSource {

    private AnimeDatabase animeDatabase;

    public AnimeDisplayLocalDataSource(AnimeDatabase animeDatabase) {
        this.animeDatabase = animeDatabase;
    }

    public Flowable<List<AnimeEntity>> loadFavorites() {
        return animeDatabase.animeDao().loadFavorites();
    }

    public Completable addAnimeToFavorites(AnimeEntity animeEntity) {
        return animeDatabase.animeDao().addAnimeToFavorites(animeEntity);
    }

    public Completable deleteAnimeFromFavorites(String id) {
        return animeDatabase.animeDao().deleteAnimeFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return animeDatabase.animeDao().getFavoriteIdList();
    }
}
