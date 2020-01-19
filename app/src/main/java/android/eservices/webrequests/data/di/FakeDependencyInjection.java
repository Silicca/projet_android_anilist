package android.eservices.webrequests.data.di;

import android.content.Context;
import android.eservices.webrequests.data.api.AnimeDisplayService;
import android.eservices.webrequests.data.db.AnimeDatabase;
import android.eservices.webrequests.data.repository.animedisplay.AnimeDisplayDataRepository;
import android.eservices.webrequests.data.repository.animedisplay.AnimeDisplayRepository;
import android.eservices.webrequests.data.repository.animedisplay.local.AnimeDisplayLocalDataSource;
import android.eservices.webrequests.data.repository.animedisplay.mapper.AnimeToAnimeEntityMapper;
import android.eservices.webrequests.data.repository.animedisplay.remote.AnimeDisplayRemoteDataSource;
import android.util.Log;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static AnimeDisplayService animeDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static AnimeDisplayRepository animeDisplayRepository;
    private static AnimeDatabase animeDatabase;
    private static Context applicationContext;

    public static AnimeDisplayRepository getAnimeDisplayRepository() {
        if (animeDisplayRepository == null) {
            animeDisplayRepository = new AnimeDisplayDataRepository(
                    new AnimeDisplayLocalDataSource(getAnimeDatabase()),
                    new AnimeDisplayRemoteDataSource(getAnimeDisplayService()),
                    new AnimeToAnimeEntityMapper()
            );
        }
        return animeDisplayRepository;
    }

    public static AnimeDisplayService getAnimeDisplayService() {
        if (animeDisplayService == null) {
            animeDisplayService = getRetrofit().create(AnimeDisplayService.class);
        }
        return animeDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.jikan.moe/v3/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

        }
        Log.d(FakeDependencyInjection.class.getName(),"GSON OK");
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static AnimeDatabase getAnimeDatabase() {
        if (animeDatabase == null) {
            animeDatabase = Room.databaseBuilder(applicationContext,
                    AnimeDatabase.class, "anime-database").build();
        }
        return animeDatabase;
    }
}
