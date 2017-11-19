package events;

import models.Weather;

/**
 * Created by bd on 11/18/2017.
 */

public class WeatherEvent {

    private final Weather weather;

    public WeatherEvent(Weather weather)
    {
        this.weather = weather;
    }

    public Weather getWeather()
    {
        return weather;
    }
}
