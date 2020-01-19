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
        animeItemViewModel.setAnimeTitleJapanese(animeEntity.getTitleJapanese());
        animeItemViewModel.setAnimeId(animeEntity.getId());
        animeItemViewModel.setAnimeImageUrl(animeEntity.getImageUrl());
        if (animeEntity.getSynopsis() != null) {
            animeItemViewModel.setAnimeSynopsis(Html.fromHtml(animeEntity.getSynopsis()).toString());
        }
        animeItemViewModel.setAnimeScore("Score : "+animeEntity.getScore()+"/10");
        animeItemViewModel.setAnimeEpisodes("Number of episodes : "+animeEntity.getEpisodes());
        animeItemViewModel.setAnimeDuration(animeEntity.getDuration());
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
