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

    @Insert
    public Completable addAnimeDetails(AnimeEntity animeEntity);

    @Query("DELETE FROM AnimeEntity WHERE mal_id = :id")
    public Completable deleteAnimeFromFavorites(String id);

    @Query("DELETE FROM AnimeEntity WHERE mal_id = :id")
    public Completable deleteAnimeDetails(String id);

    @Query("SELECT * from AnimeEntity WHERE mal_id =:id")
    Flowable<List<AnimeEntity>> loadDetails(String id);

    @Query("SELECT mal_id from AnimeEntity WHERE favorite = 'true'")
    Single<List<String>> getFavoriteIdList();
}
