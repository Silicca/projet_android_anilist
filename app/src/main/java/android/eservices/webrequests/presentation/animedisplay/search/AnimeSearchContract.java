package android.eservices.webrequests.presentation.animedisplay.search;

import android.eservices.webrequests.presentation.animedisplay.search.adapter.AnimeItemViewModel;

import java.util.List;

public interface AnimeSearchContract {

    interface View {
        void displayAnimes(List<AnimeItemViewModel> animeItemViewModelList);

        void onAnimeAddedToFavorites();

        void onAnimeRemovedFromFavorites();
    }

    interface Presenter {
        void searchAnimes(String keywords);

        void attachView(View view);

        void cancelSubscription();

        void addAnimeToFavorite(String animeId);

        void removeAnimeFromFavorites(String animeId);

        void detachView();
    }
}
