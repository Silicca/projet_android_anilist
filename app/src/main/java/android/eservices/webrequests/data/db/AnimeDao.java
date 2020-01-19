package android.eservices.webrequests.data.db;

import android.eservices.webrequests.data.entity.AnimeEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface AnimeDao {

    @Query("SELECT * from AnimeEntity")
    Flowable<List<AnimeEntity>> loadFavorites();

    @Insert
    public Completable addAnimeToFavorites(AnimeEntity animeEntity);

    @Query("DELETE FROM AnimeEntity WHERE mal_id = :id")
    public Completable deleteAnimeFromFavorites(String id);

    @Query("SELECT mal_id from AnimeEntity")
    Single<List<String>> getFavoriteIdList();
}
