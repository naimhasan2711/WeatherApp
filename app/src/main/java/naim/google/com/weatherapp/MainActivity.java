package naim.google.com.weatherapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import events.Errorevent;
import events.WeatherEvent;
import models.Currently;
import services.WeatherServiceProvider;
import utility.WeatherIconUtil;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.temperatureTextView)
    TextView textView;

    @BindView(R.id.imageView)
    ImageView iconImageView;

    @BindView(R.id.textView)
    TextView summeryTextView;

    @BindView(R.id.humidityText)
    TextView humidityText;

    @BindView(R.id.windSpeedText)
    TextView windSpeedText;



    private EditText latValue;
    private EditText longValue;

    private Double temperature;
    private Double humidity;
    private Double windSpeed;

    private String first;
    private String second;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latValue = findViewById(R.id.latitudeText);
        longValue = findViewById(R.id.longitudeText);
        button = findViewById(R.id.button);

        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                first = latValue.getText().toString().trim();
                second = longValue.getText().toString().trim();
                requestCurrentWeather(first,second);
                Log.v("latitude = " , first);

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent){
        Currently currently = weatherEvent.getWeather().getCurrently();

        temperature = currently.getTemperature();
        temperature = temperature - 32;
        temperature = (temperature )/1.8;

        humidity = currently.getHumidity();
        humidity = Double.valueOf(Math.round(humidity * 100));

        windSpeed = (currently.getWindSpeed()) * 1.6;
        windSpeed = Double.valueOf(Math.round(windSpeed));

        textView.setText(String.valueOf( Math.round(temperature)));
        summeryTextView.setText(currently.getSummary());
        humidityText.setText(humidity.toString()+"%");
        windSpeedText.setText(windSpeed.toString()+"km/hr");

        iconImageView.setImageResource(WeatherIconUtil.ICONS.get(currently.getIcon()));



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(Errorevent errorevent){
        Toast.makeText(this, errorevent.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void requestCurrentWeather(String lat , String lng)
    {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, lng);
    }
}
