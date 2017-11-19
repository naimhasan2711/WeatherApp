package utility;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import naim.google.com.weatherapp.R;

/**
 * Created by bd on 11/18/2017.
 */

public final class WeatherIconUtil {

    public static final Map<String , Integer> ICONS;
    static {
        Map<String , Integer> iconMap = new HashMap<>();
        iconMap.put("clear-day" , R.mipmap.ic_clear_day);
        iconMap.put("clear-night" , R.mipmap.ic_clear_night);
        iconMap.put("rain" , R.mipmap.ic_rain);
        iconMap.put("snow" , R.mipmap.ic_snow);
        iconMap.put("sleet" , R.mipmap.ic_sleet);
        iconMap.put("wind" , R.mipmap.ic_wind);
        iconMap.put("fog" , R.mipmap.ic_fog);
        iconMap.put("cloudy" , R.mipmap.ic_cloudy);
        iconMap.put("partly-cloudy-day" , R.mipmap.ic_partly_cloudy_night);
        iconMap.put("partly-cloudy-night" , R.mipmap.ic_partly_cloudy_night);
        iconMap.put("thunderstorm" , R.mipmap.ic_thunderstorm);

        ICONS = Collections.unmodifiableMap(iconMap);
    }

}
