package davidmuir.mobileplaformdevelopmentcourseworkdavidmuir;

//David Muir MPD Resit CourseWork
//Matric #S1428976


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.renderscript.Sampler;
import android.support.v4.graphics.ColorUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReportList extends BaseAdapter
{
    Context Context;
    int ReportLayoutId;
    List<ReportDisplay> ReportData;
    List<ReportDisplay> StringFilterList;





    public ReportList( Context Context, int ReportLayoutId, List<ReportDisplay> ReportData)
    {
        this.Context = Context;
        this.ReportLayoutId = ReportLayoutId;
        this.ReportData = ReportData;
        StringFilterList = ReportData;
    }

    @Override
    public int getCount() {
        return ReportData.size();
    }

    @Override
    public ReportDisplay getItem(int Position) {
        return ReportData.get(Position);
    }

    @Override
    public long getItemId(int Position) {
        return Position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ReportView = View.inflate(Context, ReportLayoutId,null);


         String CurrentWeather;




        TextView DayText = (TextView)ReportView.findViewById(R.id.DayTextID);
        TextView WeatherText = (TextView)ReportView.findViewById(R.id.WeatherTextID);
        TextView MinTempText = (TextView)ReportView.findViewById(R.id.ListMinTempTextID);
        TextView MaxTempText = (TextView)ReportView.findViewById(R.id.ListMaxTempTextID);
        TextView WindDirectionText = (TextView)ReportView.findViewById(R.id.ListWindDirectionTextID);
        TextView VisiblityText = (TextView)ReportView.findViewById(R.id.ListVisibilityTextID);

        ImageView WeatherIcon = (ImageView)ReportView.findViewById(R.id.WeatherIconList);


        DayText.setText(ReportData.get(position).Day);
        WeatherText.setText(ReportData.get(position).Weather);
        CurrentWeather = ReportData.get(position).Weather;
        MinTempText.setText(ReportData.get(position).MinTemp);
        MaxTempText.setText(ReportData.get(position).MaxTemp);
        WindDirectionText.setText(ReportData.get(position).WindInfo);
        VisiblityText.setText(ReportData.get(position).Visibility);




        if(CurrentWeather.contains("Drizzle"))
        {
            WeatherIcon.setImageResource(R.drawable.drizzle);
        }

        if(CurrentWeather.contains("Light Rain"))
        {
            WeatherIcon.setImageResource(R.drawable.lightrain);
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

        ReportView.setTag(ReportData.get(position));

        return ReportView;
    }

}
