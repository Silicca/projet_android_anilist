package android.eservices.webrequests.presentation.animedisplay.favorite.mapper;

import android.eservices.webrequests.data.entity.AnimeEntity;
import android.eservices.webrequests.presentation.animedisplay.favorite.adapter.AnimeDetailViewModel;
import android.text.Html;

import java.util.ArrayList;
import java.util.List;

public class AnimeEntityToDetailViewModelMapper {

    private AnimeDetailViewModel map(AnimeEntity animeEntity) {
        AnimeDetailViewModel animeItemViewModel = new AnimeDetailViewModel();
        animeItemViewModel.setAnimeTitle(animeEntity.getTitle());
        animeItemViewModel.setAnimeId(animeEntity.getId());
        animeItemViewModel.setAnimeImageUrl(animeEntity.getImageUrl());
        if (animeEntity.getSynopsis() != null) {
            animeItemViewModel.setAnimeSynopsis(Html.fromHtml(animeEntity.getSynopsis()).toString());
        }
        animeItemViewModel.setAnimeScore(animeEntity.getScore());
        animeItemViewModel.setAnimeEpisodes(animeEntity.getEpisodes());
        animeItemViewModel.setAnimeStartDate("Started in " + animeEntity.getStartDate());
        animeItemViewModel.setAnimeEndDate("Ended in " + animeEntity.getEndDate());
        animeItemViewModel.setAnimeType("Type : " + animeEntity.getType());
        return animeItemViewModel;
    }

    public List<AnimeDetailViewModel> map(List<AnimeEntity> animeList) {
        List<AnimeDetailViewModel> animeItemViewModelList = new ArrayList<>();
        for (AnimeEntity anime : animeList) {
            animeItemViewModelList.add(map(anime));
        }
        return animeItemViewModelList;
    }

    private String languageMapper(String input) {
        switch (input) {
            case "en":
                return "English";
            case "fr":
                return "French";
            default:
                return "Unknown (" + input + ")";
        }
    }
}
