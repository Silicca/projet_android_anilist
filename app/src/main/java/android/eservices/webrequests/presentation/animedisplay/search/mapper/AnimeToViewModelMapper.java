package android.eservices.webrequests.presentation.animedisplay.search.mapper;

import android.eservices.webrequests.data.api.model.Anime;
import android.eservices.webrequests.presentation.animedisplay.search.adapter.AnimeItemViewModel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class AnimeToViewModelMapper {

    private AnimeItemViewModel map(Anime anime) {
        AnimeItemViewModel animeItemViewModel = new AnimeItemViewModel();
        animeItemViewModel.setAnimeTitle(anime.getTitle());
        animeItemViewModel.setAnimeId(anime.getId());
        animeItemViewModel.setIconUrl(anime.getImageUrl());
        animeItemViewModel.setFavorite(anime.isFavorite());
        return animeItemViewModel;
    }

    public List<AnimeItemViewModel> map(List<Anime> animeList) {
        List<AnimeItemViewModel> animeItemViewModelList = new ArrayList<>();
        for (Anime anime : animeList) {
            animeItemViewModelList.add(map(anime));
        }
        return animeItemViewModelList;
    }
}
