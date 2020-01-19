package android.eservices.webrequests.data.repository.animedisplay.mapper;

import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.data.entity.AnimeEntity;
import android.text.TextUtils;

public class AnimeToAnimeEntityMapper {

    public AnimeEntity map(Anime anime) {
        AnimeEntity animeEntity = new AnimeEntity();
        animeEntity.setTitle(anime.getTitle());
        animeEntity.setSynopsis(anime.getSynopsis());
        animeEntity.setId(anime.getId());
        animeEntity.setType(anime.getType());
        animeEntity.setScore(anime.getScore());
        animeEntity.setEpisodes(anime.getEpisodes());
        animeEntity.setStartDate(anime.getStartDate());
        animeEntity.setEndDate(anime.getEndDate());
        animeEntity.setImageUrl(anime.getImageUrl());
        return animeEntity;
    }
}
