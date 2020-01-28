package davidmuir.mobileplaformdevelopmentcourseworkdavidmuir;


import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

//David Muir MPD Resit CourseWork
//Matric #S1428976


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button GlasgowButton;
    private Button EdinburghButton;
    private Button AberdeenButton;
    private Button DundeeButton;
    private Button FortWilliamButton;
    private Button NorthBerwickButton;
    private Button UllapoolButton;
    private Button PortreeButton;
    private Button LerwickButton;
    private Button ManchesterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlasgowButton = (Button) findViewById(R.id.GlasgowButton);
        GlasgowButton.setOnClickListener(this);

        EdinburghButton = (Button) findViewById(R.id.EdinburghButton);
        EdinburghButton.setOnClickListener(this);

        AberdeenButton= (Button) findViewById(R.id.AberdeenButton);
        AberdeenButton.setOnClickListener(this);

        DundeeButton= (Button) findViewById(R.id.DundeeButton);
        DundeeButton.setOnClickListener(this);

        FortWilliamButton= (Button) findViewById(R.id.FortWilliamButton);
        FortWilliamButton.setOnClickListener(this);

        NorthBerwickButton= (Button) findViewById(R.id.NorthBerwickButton);
        NorthBerwickButton.setOnClickListener(this);

        UllapoolButton= (Button) findViewById(R.id.UllapoolButton);
        UllapoolButton.setOnClickListener(this);

        PortreeButton= (Button) findViewById(R.id.PortreeButton);
        PortreeButton.setOnClickListener(this);

        LerwickButton= (Button) findViewById(R.id.LerwickButton);
        LerwickButton.setOnClickListener(this);

        ManchesterButton= (Button) findViewById(R.id.ManchesterButton);
        ManchesterButton.setOnClickListener(this);


    }

    public void onClick(View MainView)
    {
        if (MainView == GlasgowButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",1);
            startActivity(i);
        }
        else if (MainView == EdinburghButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",2);
            startActivity(i);
        }

        else if (MainView == AberdeenButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",3);
            startActivity(i);
        }

        else if (MainView == DundeeButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",4);
            startActivity(i);
        }

        else if (MainView == FortWilliamButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",5);
            startActivity(i);
        }

        else if (MainView == NorthBerwickButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",6);
            startActivity(i);
        }

        else if (MainView == UllapoolButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",7);
            startActivity(i);
        }

        else if (MainView == PortreeButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",8);
            startActivity(i);
        }

        else if (MainView == LerwickButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",9);
            startActivity(i);
        }

        else if (MainView == ManchesterButton)
        {
            Intent i = new Intent(getApplicationContext(), PullParser.class);
            i.putExtra("ReportType",10);
            startActivity(i);
        }
    }

}
