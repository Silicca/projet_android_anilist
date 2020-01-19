package android.eservices.webrequests.presentation.animedisplay.search.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.data.di.FakeDependencyInjection;
import android.eservices.webrequests.presentation.animedisplay.search.AnimeSearchContract;
import android.eservices.webrequests.presentation.animedisplay.search.AnimeSearchPresenter;
import android.eservices.webrequests.presentation.animedisplay.search.adapter.AnimeActionInterface;
import android.eservices.webrequests.presentation.animedisplay.search.adapter.AnimeAdapter;
import android.eservices.webrequests.presentation.animedisplay.search.adapter.AnimeItemViewModel;
import android.eservices.webrequests.presentation.animedisplay.search.mapper.AnimeToViewModelMapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/*
 * TODO : uncheck favorite selection in search results when favorite unchecked from Favorite fragment
 */
public class SearchFragment extends Fragment implements AnimeSearchContract.View, AnimeActionInterface {

    public static final String TAB_NAME = "Search";
    private View rootView;
    AnimeSearchContract.Presenter animeSearchPresenter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;
    private ProgressBar progressBar;
    private SearchView.OnQueryTextListener queryListener;
    private boolean listDisplay = true;
    private RecyclerView.LayoutManager layoutManager;
    private String query = null;

    private SearchFragment() {
    }

    public static SearchFragment newInstance() { return new SearchFragment(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        setupSearchView();
        progressBar = rootView.findViewById(R.id.progress_bar);
        Switch aSwitch = rootView.findViewById(R.id.switchDisplay);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchDisplay();
            }
        });
        animeSearchPresenter = new AnimeSearchPresenter(FakeDependencyInjection.getAnimeDisplayRepository(), new AnimeToViewModelMapper());
        animeSearchPresenter.attachView(this);
    }

    public void switchDisplay(){
        this.listDisplay = !this.listDisplay;
        this.setupRecyclerView();
        this.updateSearchView(query);
    }

    private void updateSearchView(String s) {
        searchView = rootView.findViewById(R.id.search_view);
        queryListener.onQueryTextChange(s);
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.search_view);
        this.queryListener = new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0) {
                    animeSearchPresenter.cancelSubscription();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    query = s;
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 5000;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            animeSearchPresenter.searchAnimes(query);
                        }
                    }, sleep);
                }
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryListener);
    }

    private void setupRecyclerView() {
        this.recyclerView = rootView.findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(getContext());
        this.animeAdapter = new AnimeAdapter(this);

        if(this.listDisplay){
            this.recyclerView.setLayoutManager(this.layoutManager);
        }else{
            this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }

        this.recyclerView.setAdapter(this.animeAdapter);
    }

    @Override
    public void displayAnimes(List<AnimeItemViewModel> animeItemViewModelList) {
        progressBar.setVisibility(View.GONE);
        animeAdapter.bindViewModels(animeItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(String animeId, boolean isFavorite) {
        if (isFavorite) {
            animeSearchPresenter.addAnimeToFavorite(animeId);
        } else {
            animeSearchPresenter.removeAnimeFromFavorites(animeId);
        }
    }

    @Override
    public void onAnimeAddedToFavorites() {
        //Do nothing
    }

    @Override
    public void onAnimeRemovedFromFavorites() {
        //Do nothing
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        animeSearchPresenter.detachView();
    }
}
