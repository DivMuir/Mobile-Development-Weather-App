package davidmuir.mobileplaformdevelopmentcourseworkdavidmuir;

//David Muir MPD Resit CourseWork
//Matric #S1428976

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PullParser extends AppCompatActivity {


    private String GlasgowURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579";
    private String EdinburghURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2650225";
    private String AberdeenURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2657832";
    private String DundeeURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2650752";
    private String FortWilliamURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2649169";
    private String NorthBerwickURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2641419";
    private String UllapoolURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2635199";
    private String PorteeURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2640006";
    private String LerwickURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2644605";
    private String ManchesterURL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";

    private String Result = "";


    List<ReportDisplay> ForecastList = new ArrayList<>();
    ReportList adapter;

    ListView ReportListView;
    ProgressDialog Message;

    DatePickerDialog.OnDateSetListener dateSetListener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_list_base);


        ReportListView = (ListView) findViewById(R.id.ReportListViewID);

        getSupportActionBar().setTitle(null);

        Bundle extras = getIntent().getExtras();
            final int ReportType = extras.getInt("ReportType");

        if (ReportType == 1) {
            StartRSSFeed(GlasgowURL, ReportType);
            getSupportActionBar().setTitle("Glasgow Forecast");
        }

        if (ReportType == 2) {
            StartRSSFeed(EdinburghURL, ReportType);
            getSupportActionBar().setTitle("Edinburgh Forecast");
        }

        if (ReportType == 3) {
            StartRSSFeed(AberdeenURL, ReportType);
            getSupportActionBar().setTitle("Aberdeen Forecast");
        }

        if (ReportType == 4) {
            StartRSSFeed(DundeeURL, ReportType);
            getSupportActionBar().setTitle("Dundee Forecast");
        }

        if (ReportType == 5) {
            StartRSSFeed(FortWilliamURL, ReportType);
            getSupportActionBar().setTitle("Fort William Forecast");
        }

        if (ReportType == 6) {
            StartRSSFeed(NorthBerwickURL, ReportType);
            getSupportActionBar().setTitle("North Berwick Forecast");
        }

        if (ReportType == 7) {
            StartRSSFeed(UllapoolURL, ReportType);
            getSupportActionBar().setTitle("Ullapool Forecast");
        }

        if (ReportType == 8) {
            StartRSSFeed(PorteeURL, ReportType);
            getSupportActionBar().setTitle("Portee Forecast");
        }

        if (ReportType == 9) {
            StartRSSFeed(LerwickURL, ReportType);
            getSupportActionBar().setTitle("Lerwick Forecast");
        }

        if (ReportType == 10) {
            StartRSSFeed(ManchesterURL, ReportType);
            getSupportActionBar().setTitle("Manchester Forecast");
        }


        ReportListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                ReportDisplay item = (ReportDisplay) adapter.getItemAtPosition(position);
                LoadForecast(item);
            }
        });


    }

    private void LoadForecast(ReportDisplay item) {
        Intent i = new Intent(getApplicationContext(), ReportItem.class);
        i.putExtra("ReportDisplay", item);
        startActivity(i);
    }


    public void StartRSSFeed(String url, int ReportType) {
        RSSFetch rssFetch = new RSSFetch(url, ReportType);
        rssFetch.execute();
    }



    class RSSFetch extends AsyncTask<Void, Void, List<ReportDisplay>> {


        private String theUrl;
        private int theReportType;

        public RSSFetch(String url, int ReportType) {
            theUrl = url;
            theReportType = ReportType;

        }

        @Override
        protected void onPreExecute() {
            Message = new ProgressDialog(PullParser.this);
            Message.setMessage("Loading Weather Info");
           Message.show();
        }



        @Override
        protected List<ReportDisplay> doInBackground(Void... theVoid) {
            ReportDisplay ReportItem = null;
            ForecastList = new ArrayList<>();


            URL aurl;
            URLConnection yc;
            BufferedReader in = null;
            String inputLine = "";



            try {
                aurl = new URL(theUrl);
                yc = aurl.openConnection();
                in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

                while ((inputLine = in.readLine()) != null) {
                    Result = Result + inputLine;
                }
                in.close();



            } catch (IOException ae) {
                Log.e("MyTag", "ioexception" + ae.getCause());
            }

            if (Result != null) {


                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser PullParse = factory.newPullParser();
                    PullParse.setInput(new StringReader(Result));


                    int EventType = PullParse.getEventType();
                    boolean finished = false;
                    while (EventType != XmlPullParser.END_DOCUMENT && !finished) {
                        switch (EventType) {
                            case XmlPullParser.START_DOCUMENT:

                                break;
                            case XmlPullParser.START_TAG:


                                if (PullParse.getName().equalsIgnoreCase("item")) {
                                    ReportItem = new ReportDisplay(" ", " ", " ");
                                } else if (ReportItem != null) {

                                    if (PullParse.getName().equalsIgnoreCase("title")) {
                                        ReportItem.ReportTitle = PullParse.nextText().trim();
                                        String [] TitleName = ReportItem.ReportTitle.split(":");
                                        ReportItem.Day = TitleName[0].trim();
                                        ReportItem.WeatherHolder = TitleName[1].trim();
                                        ReportItem.MinTempInfo = TitleName[2].trim();


                                    }


                                    if(PullParse.getName().equalsIgnoreCase("title"))
                                    {
                                        String[] WeatherInfo = ReportItem.WeatherHolder.split(",");
                                        ReportItem.Weather = WeatherInfo[0].trim();
                                    }

                                    if(PullParse.getName().equalsIgnoreCase("title"))
                                    {
                                        String[] MaxTempInfo = ReportItem.WeatherHolder.split("M");
                                        ReportItem.MaxTempInfo = MaxTempInfo[0].trim();
                                    }

                                    else if (PullParse.getName().equalsIgnoreCase("description")) {
                                        ReportItem.WeatherReportDescription = PullParse.nextText().trim();
                                        String[] ReportInfo = ReportItem.WeatherReportDescription.split(",");


                                        if(ReportInfo.length ==9) {

                                            String[] SpeedInfo = ReportInfo[2].split(":");

                                            ReportItem.MaxTemp = "Max Temperature = N/A";
                                            ReportItem.MinTemp = ReportInfo[0].trim();
                                            ReportItem.LocationName = getSupportActionBar().getTitle().toString()+ ": " +ReportItem.Day;
                                            ReportItem.WindDirection = ReportInfo[1].trim();
                                            ReportItem.WindSpeed = ReportInfo[2].trim();
                                            ReportItem.WindInfo = ReportInfo[1].trim() + " at " +SpeedInfo[1].trim();
                                            ReportItem.Visibility = ReportInfo[3].trim();
                                            ReportItem.Pressure = ReportInfo[4].trim();
                                            ReportItem.Humidity = ReportInfo[5].trim();
                                            ReportItem.UVRisk = ReportInfo[6].trim();
                                            ReportItem.Pollution = ReportInfo[7].trim();
                                            ReportItem.SunRiseTime = "Sun Rise: N/A";
                                            ReportItem.SunsetTime = ReportInfo[8].trim();
                                        }


                                        if(ReportInfo.length ==11) {

                                            String[] SpeedInfo = ReportInfo[3].split(":");

                                            ReportItem.MaxTemp = ReportInfo[0].trim();
                                            ReportItem.MinTemp = ReportInfo[1].trim();
                                            ReportItem.LocationName = getSupportActionBar().getTitle().toString()+ ": " +ReportItem.Day;
                                            ReportItem.WindDirection = ReportInfo[2].trim();
                                            ReportItem.WindSpeed = ReportInfo[3].trim();
                                            ReportItem.WindInfo = ReportInfo[2].trim() + " at " +SpeedInfo[1].trim();
                                            ReportItem.Visibility = ReportInfo[4].trim();
                                            ReportItem.Pressure = ReportInfo[5].trim();
                                            ReportItem.Humidity = ReportInfo[6].trim();
                                            ReportItem.UVRisk = ReportInfo[7].trim();
                                            ReportItem.Pollution = ReportInfo[8].trim();
                                            ReportItem.SunRiseTime = ReportInfo[9].trim();
                                            ReportItem.SunsetTime = ReportInfo[10].trim();
                                        }

                                        } else if (PullParse.getName().equalsIgnoreCase("link")) {
                                        ReportItem.WeatherReportWebLink = PullParse.nextText().trim();

                                    }
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                if (PullParse.getName().equalsIgnoreCase("item") && ReportItem != null) {
                                    ForecastList.add(ReportItem);

                                } else if (PullParse.getName().equalsIgnoreCase("channel")) {
                                    finished = true;
                                }
                                break;
                        }
                        EventType = PullParse.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return ForecastList;
        }


        @Override
        protected void onPostExecute(List<ReportDisplay> result) {
            adapter = new ReportList(getApplicationContext(), R.layout.report_list_item, result);
            ReportListView.setAdapter(adapter);
           Message.dismiss();
        }
    }
}
