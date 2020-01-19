package android.eservices.webrequests.presentation.animedisplay.favorite;

import android.eservices.webrequests.data.entity.AnimeEntity;
import android.eservices.webrequests.data.repository.animedisplay.AnimeDisplayRepository;
import android.eservices.webrequests.presentation.animedisplay.favorite.mapper.AnimeEntityToDetailViewModelMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class AnimeFavoritePresenter implements AnimeFavoriteContract.Presenter {

    private AnimeDisplayRepository animeDisplayRepository;
    private AnimeFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private AnimeEntityToDetailViewModelMapper animeEntityToDetailViewModelMapper;

    public AnimeFavoritePresenter(AnimeDisplayRepository animeDisplayRepository, AnimeEntityToDetailViewModelMapper animeEntityToDetailViewModelMapper) {
        this.animeDisplayRepository = animeDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.animeEntityToDetailViewModelMapper = animeEntityToDetailViewModelMapper;
    }

    @Override
    public void attachView(AnimeFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavorites() {
        compositeDisposable.add(animeDisplayRepository.getFavoriteAnimes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<AnimeEntity>>() {

                    @Override
                    public void onNext(List<AnimeEntity> animeEntityList) {
                        view.displayFavorites(animeEntityToDetailViewModelMapper.map(animeEntityList));
                        System.out.println("BIND FAVORITES");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
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
                        view.onAnimeRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
