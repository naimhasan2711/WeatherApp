package services;

import models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bd on 11/18/2017.
 */

public interface WeatherService {

    @GET("{lat},{lng}")
    Call<Weather> getWeather(@Path("lat") String lat,@Path("lng") String lng);


}
