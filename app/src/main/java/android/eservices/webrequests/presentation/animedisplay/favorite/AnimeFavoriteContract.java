package android.eservices.webrequests.presentation.animedisplay.favorite;

import android.eservices.webrequests.presentation.animedisplay.favorite.adapter.AnimeDetailViewModel;

import java.util.List;

public interface AnimeFavoriteContract {

    interface View {
        void displayFavorites(List<AnimeDetailViewModel> animeDetailViewModelList);

        void onAnimeRemoved();
    }

    interface Presenter {
        void attachView(View view);

        void getFavorites();

        void removeAnimeFromFavorites(String animeId);

        void detachView();
    }
}
