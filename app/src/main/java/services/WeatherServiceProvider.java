package services;

import android.util.Log;
import org.greenrobot.eventbus.EventBus;
import events.Errorevent;
import events.WeatherEvent;
import models.Currently;
import models.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by bd on 11/18/2017.
 */

public class WeatherServiceProvider {


    private static final String BASE_URL = "https://api.darksky.net/forecast/4f001dfc5b265dee817b17bc264b21bc/";
    private Retrofit retrofit;
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();

    private Retrofit getRetrofit()
    {
        if(this.retrofit == null)
        {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return this.retrofit;
    }


    public void getWeather(String lat, String lng)
    {

        WeatherService weatherService = getRetrofit().create(WeatherService.class);
        Call<Weather> weatherCall = weatherService.getWeather(lat , lng);
        weatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Weather weather = response.body();

                if(weather != null) {

                    Currently currently = weather.getCurrently();
                    EventBus.getDefault().post(new WeatherEvent(weather));

                }
                else{
                    EventBus.getDefault().post(new Errorevent("No Weather data available"));
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

                Log.e(TAG , "Failed to resolve data");
                EventBus.getDefault().post(new Errorevent("Unable to connect weather server"));

            }
        });

    }


}
