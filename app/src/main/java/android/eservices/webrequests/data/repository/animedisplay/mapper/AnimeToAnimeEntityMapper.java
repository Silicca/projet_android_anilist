package android.eservices.webrequests.data.repository.animedisplay.mapper;

import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.data.entity.AnimeEntity;
import android.text.TextUtils;

public class AnimeToAnimeEntityMapper {

    public AnimeEntity map(Anime anime) {
        AnimeEntity animeEntity = new AnimeEntity();
        animeEntity.setTitle(anime.getTitle());
        animeEntity.setTitleJapanese(anime.getTitleJapanese());
        animeEntity.setSynopsis(anime.getSynopsis());
        animeEntity.setId(anime.getId());
        animeEntity.setType(anime.getType());
        animeEntity.setScore(anime.getScore());
        if (anime.getEpisodes() == null) {
            animeEntity.setEpisodes("N.C.");
        } else {
            animeEntity.setEpisodes(anime.getEpisodes());
        }
        animeEntity.setDuration(anime.getDuration());
        animeEntity.setImageUrl(anime.getImageUrl());
        return animeEntity;
    }
}
