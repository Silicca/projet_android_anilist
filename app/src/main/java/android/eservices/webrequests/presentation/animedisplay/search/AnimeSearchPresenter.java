package android.eservices.webrequests.presentation.animedisplay.search;

import android.eservices.webrequests.data.api.model.AnimeSearchResponse;
import android.eservices.webrequests.data.repository.animedisplay.AnimeDisplayRepository;
import android.eservices.webrequests.presentation.animedisplay.search.mapper.AnimeToViewModelMapper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AnimeSearchPresenter implements AnimeSearchContract.Presenter {

    private AnimeDisplayRepository animeDisplayRepository;
    private AnimeSearchContract.View view;
    private CompositeDisposable compositeDisposable;
    private AnimeToViewModelMapper animeToViewModelMapper;

    public AnimeSearchPresenter(AnimeDisplayRepository animeDisplayRepository, AnimeToViewModelMapper animeToViewModelMapper) {
        this.animeDisplayRepository = animeDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.animeToViewModelMapper = animeToViewModelMapper;
    }

    @Override
    public void searchAnimes(String keywords) {
        compositeDisposable.clear();
        compositeDisposable.add(animeDisplayRepository.getAnimeSearchResponse(keywords)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AnimeSearchResponse>() {

                    @Override
                    public void onSuccess(AnimeSearchResponse animeSearchResponse) {
                        // work with the resulting todos
                        view.displayAnimes(animeToViewModelMapper.map(animeSearchResponse.getAnimeList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }

    @Override
    public void addAnimeToFavorite(String animeId) {
        compositeDisposable.add(animeDisplayRepository.addAnimeToFavorites(animeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onAnimeAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeAnimeFromFavorites(String animeId) {
        compositeDisposable.add(animeDisplayRepository.removeAnimeFromFavorites(animeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onAnimeRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(AnimeSearchContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelSubscription() {
        compositeDisposable.clear();
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
