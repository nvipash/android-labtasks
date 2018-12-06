package iot.nvipash_harvardapi;

import android.app.Application;

import iot.nvipash_harvardapi.http_client.GetRecordsData;
import iot.nvipash_harvardapi.models.MainInteractor;
import iot.nvipash_harvardapi.models.MainInteractorImpl;
import iot.nvipash_harvardapi.models.RecordDetailsInteractor;
import iot.nvipash_harvardapi.models.RecordDetailsInteractorImpl;
import iot.nvipash_harvardapi.models.RecordsFavouritesInteractor;
import iot.nvipash_harvardapi.models.RecordsFavouritesInteractorImpl;
import iot.nvipash_harvardapi.repositories.SharedPreferencesRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {
    private static final String BASE_URL = "https://api.harvardartmuseums.org";

    private MainInteractor mainInteractor;
    private RecordDetailsInteractor detailsInteractor;
    private RecordsFavouritesInteractor favouritesInteractor;
    private SharedPreferencesRepository repository;
    private FragmentNavigation fragmentNavigation;

    @Override
    public void onCreate() {
        super.onCreate();
        configApplication();
    }

    public GetRecordsData getRetrofitInstance() {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(GetRecordsData.class);
    }

    private void configApplication() {
        final GetRecordsData getRecordsData = getRetrofitInstance();
//        mainInteractor = new MainInteractorImpl(getRecordsData);
//        repository = new SharedPreferencesRepository(getApplicationContext());
//        favouritesInteractor = new RecordsFavouritesInteractorImpl(repository);
//        detailsInteractor = new RecordDetailsInteractorImpl(repository);
    }

    public RecordDetailsInteractor getDetailsInteractor() {
        return detailsInteractor;
    }

    public RecordsFavouritesInteractor getFavouritesInteractor() {
        return favouritesInteractor;
    }

    public MainInteractor getMainInteractor() {
        return mainInteractor;
    }

    public SharedPreferencesRepository getPreferences() {
        return repository;
    }

    public FragmentNavigation getFragmentNavigation() {
        return fragmentNavigation;
    }

    public void setFragmentNavigation(FragmentNavigation fragmentNavigation) {
        this.fragmentNavigation = fragmentNavigation;
    }
}