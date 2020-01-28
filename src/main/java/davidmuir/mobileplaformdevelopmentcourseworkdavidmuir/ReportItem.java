package davidmuir.mobileplaformdevelopmentcourseworkdavidmuir;

//David Muir MPD Resit CourseWork
//Matric #S1428976

import android.graphics.Color;
import android.media.Image;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import javax.xml.datatype.Duration;

public class ReportItem extends FragmentActivity {

    private TextView ReportTitleText;
    private TextView DayText;
    private TextView ReportLocationText;
    private TextView ReportWeatherText;

    private TextView MaxTempText;
    private TextView MinTempText;
    private TextView WindDirectionText;
    private TextView WindSpeedText;
    private TextView VisibilityText;
    private TextView PressureText;
    private TextView HumidityText;
    private TextView UVRiskText;
    private TextView UVLevelText;
    private TextView PollutionText;
    private TextView SunRiseText;
    private TextView SunSetText;

    private ImageView WeatherIcon;

    private String ForecastLocation;
    private String CurrentWeather;
    private String UVRiskLevel;



    private TextView DurationText;
    private TextView WebLinkText;




    ReportDisplay TheItem;
    PullParser PullInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_display);





        TheItem = (ReportDisplay) getIntent().getSerializableExtra("ReportDisplay");

        ReportLocationText = (TextView)findViewById(R.id.LocationTextID);
        DayText = (TextView)findViewById(R.id.DayTextID);
        ReportWeatherText  = (TextView)findViewById(R.id.WeatherTextID);
        WeatherIcon = (ImageView) findViewById(R.id.WeatherIconReport);

        MaxTempText = (TextView)findViewById(R.id.MaxTempTextID);
        MinTempText = (TextView)findViewById(R.id.MinTempTextID);

        WindDirectionText = (TextView)findViewById(R.id.WindDirectionTextID);
        WindSpeedText = (TextView)findViewById(R.id.WindSpeedTextID);
        VisibilityText = (TextView)findViewById(R.id.VisibilityTextID);

        PressureText = (TextView)findViewById(R.id.PressureTextID);
        HumidityText = (TextView)findViewById(R.id.HumidityTextID);

        UVRiskText = (TextView)findViewById(R.id.UVRiskTextID);
        UVLevelText = (TextView)findViewById(R.id.UVLevelTextID);

        PollutionText = (TextView)findViewById(R.id.PollutionTextID);

        SunRiseText = (TextView)findViewById(R.id.SunRiseTextID);
        SunSetText = (TextView)findViewById(R.id.SunsetTextID);

        WebLinkText = (TextView)findViewById(R.id.LinkID);


        if (TheItem!=null)
        {
            ReportLocationText.setText(TheItem.LocationName);
            ReportWeatherText.setText(TheItem.Weather);

            MaxTempText.setText(TheItem.MaxTemp);
            MinTempText.setText(TheItem.MinTemp);

            WindDirectionText.setText(TheItem.WindDirection);
            WindSpeedText.setText(TheItem.WindSpeed);

            VisibilityText.setText(TheItem.Visibility);
            PressureText.setText(TheItem.Pressure);
            HumidityText.setText(TheItem.Humidity);
            UVRiskText.setText(TheItem.UVRisk);
            PollutionText.setText(TheItem.Pollution);

            SunRiseText.setText(TheItem.SunRiseTime);
            SunSetText.setText(TheItem.SunsetTime);

            WebLinkText.setText(TheItem.WeatherReportWebLink);

            CurrentWeather = TheItem.Weather;
            UVRiskLevel = TheItem.UVRisk;

            if(CurrentWeather.contains("Light Rain"))
            {
                WeatherIcon.setImageResource(R.drawable.lightrain);
            }

            if(CurrentWeather.contains("Drizzle"))
            {
                WeatherIcon.setImageResource(R.drawable.drizzle);
            }

            if(CurrentWeather.contains("Light Cloud"))
            {
                WeatherIcon.setImageResource(R.drawable.lightclouds);
            }

            if(CurrentWeather.contains("Light Rain Showers"))
            {
                WeatherIcon.setImageResource(R.drawable.lightclouds);
            }

            if(CurrentWeather.contains("Light snow"))
            {
                WeatherIcon.setImageResource(R.drawable.lightsnow);
            }

            if(CurrentWeather.contains("Heavy Rain"))
            {
                WeatherIcon.setImageResource(R.drawable.heavyrain);
            }

            if(CurrentWeather.contains("Heavy Rain Showers"))
            {
                WeatherIcon.setImageResource(R.drawable.heavyrainshowers);
            }

            if(CurrentWeather.contains("Heavy Snow"))
            {
                WeatherIcon.setImageResource(R.drawable.heavysnow);
            }

            if(CurrentWeather.contains("Thunder storm"))
            {
                WeatherIcon.setImageResource(R.drawable.thunderstorm);
            }

            if(CurrentWeather.contains("Thundery shower"))
            {
                WeatherIcon.setImageResource(R.drawable.thunderyshowers);
            }

            if(CurrentWeather.contains("Thick Cloud"))
            {
                WeatherIcon.setImageResource(R.drawable.thickclouds);
            }

            if(CurrentWeather.contains("Sunny Intervals"))
            {
                WeatherIcon.setImageResource(R.drawable.sunnyintervals);
            }


            if(UVRiskLevel.contains("0")||UVRiskLevel.contains("1")||UVRiskLevel.contains("2"))
            {
                UVLevelText.setText("Risk: Low");
                UVLevelText.setTextColor(Color.GREEN);
            }

            if(UVRiskLevel.contains("3")||UVRiskLevel.contains("4")||UVRiskLevel.contains("5"))
            {
                UVLevelText.setText("Risk: Moderate ");
                UVLevelText.setTextColor(Color.YELLOW);
            }

            if(UVRiskLevel.contains("6")||UVRiskLevel.contains("7"))
            {
                UVLevelText.setText( "Risk: High ");
                UVLevelText.setTextColor(Color.argb(1,255,127,80));
            }

            if(UVRiskLevel.contains("8")||UVRiskLevel.contains("9")||UVRiskLevel.contains("10"))
            {
                UVRiskLevel = "Risk: Very High ";
                UVLevelText.setTextColor(Color.RED);
            }

            if(UVRiskLevel.contains("11"))
            {
                UVRiskLevel = "Risk: Extreme";
                UVLevelText.setTextColor(Color.argb(1,148,0,211));
            }







        }

    }







}
