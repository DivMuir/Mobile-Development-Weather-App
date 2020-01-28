package davidmuir.mobileplaformdevelopmentcourseworkdavidmuir;

//David Muir MPD Resit CourseWork
//Matric #S1428976

import android.os.Bundle;

import java.io.Serializable;
import java.util.Date;


public class ReportDisplay implements Serializable {

    String ReportTitle;
    String WeatherReportDescription;
    String LocationName;
    String Day;
    String Weather;
    String MaxTemp;
    String MinTemp;
    String WindInfo;
    String WindDirection;
    String WindSpeed;
    String Visibility;
    String Pressure;
    String Humidity;
    String UVRisk;
    String Pollution;
    String SunRiseTime;
    String SunsetTime;

    String WeatherReportWebLink;

    String WeatherHolder;
    String MinTempInfo;
    String MaxTempInfo;






    public ReportDisplay(String ReportTitle,String ReportDescription, String ReportWebLink) {
        this.ReportTitle = ReportTitle;
        this.WeatherReportWebLink = ReportWebLink;
    }





}
