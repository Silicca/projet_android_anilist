package android.eservices.webrequests.presentation.animedisplay.favorite.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.animedisplay.favorite.AnimeFavoriteContract;
import android.eservices.webrequests.presentation.animedisplay.favorite.AnimeFavoritePresenter;
import android.eservices.webrequests.presentation.animedisplay.favorite.adapter.AnimeDetailActionInterface;
import android.eservices.webrequests.presentation.animedisplay.favorite.adapter.AnimeDetailAdapter;
import android.eservices.webrequests.presentation.animedisplay.favorite.adapter.AnimeDetailViewModel;
import android.eservices.webrequests.presentation.animedisplay.favorite.mapper.AnimeEntityToDetailViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFragment extends Fragment implements AnimeFavoriteContract.View, AnimeDetailActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    AnimeFavoriteContract.Presenter animeFavoritePresenter;
    private RecyclerView recyclerView;
    private AnimeDetailAdapter animeAdapter;

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();

        animeFavoritePresenter = new AnimeFavoritePresenter(FakeDependencyInjection.getAnimeDisplayRepository(), new AnimeEntityToDetailViewModelMapper());
        animeFavoritePresenter.attachView(this);
        animeFavoritePresenter.getFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        animeAdapter = new AnimeDetailAdapter(this);
        recyclerView.setAdapter(animeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayFavorites(List<AnimeDetailViewModel> animeDetailViewModelList) {
        animeAdapter.bindViewModels(animeDetailViewModelList);

    }

    @Override
    public void onRemoveFavorite(String animeId) {
        animeFavoritePresenter.removeAnimeFromFavorites(animeId);
        System.out.println("Remove anime " + animeId);
    }

    @Override
    public void onAnimeRemoved() {
        //Do nothing yet
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animeFavoritePresenter.detachView();
    }
}
