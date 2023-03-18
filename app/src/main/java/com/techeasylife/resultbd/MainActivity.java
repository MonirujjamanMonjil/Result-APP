package com.techeasylife.resultbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {
    FirebaseRemoteConfig remoteConfig;


    LinearLayout Jsc_View;
    LinearLayout Ssc_View;
    LinearLayout Hsc_View;
    LinearLayout Dibs_View;
    LinearLayout Nu_View;
    LinearLayout Info_View;
    TextView txtMarquee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int currentVersionCode;

        currentVersionCode = getCurrentVersionCode();
        Log.d("myApp",String.valueOf(currentVersionCode));

        remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(2)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);
        remoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()){
                    final String newVersionCode = remoteConfig.getString("newVersionCode");
                    if (Integer.parseInt(newVersionCode) > getCurrentVersionCode()){
                        showUpdateDialogBox();

                    }
                }
            }
        });





        txtMarquee = (TextView) findViewById(R.id.marqueeText);

        Jsc_View = findViewById(R.id.Jsc_View);
        Ssc_View = findViewById(R.id.Ssc_View);
        Hsc_View = findViewById(R.id.Hsc_View);

        Dibs_View = findViewById(R.id.Dibs_View);

        Nu_View = findViewById(R.id.Nu_View);

        Info_View = findViewById(R.id.Info_View);




        txtMarquee.setSelected(true);

        Jsc_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent myIntent = new Intent(MainActivity.this,Jsc.class);
                startActivity(myIntent);
            }
        });

        //SSC VIEW


        Ssc_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent myIntent = new Intent(MainActivity.this,Ssc.class);
                startActivity(myIntent);
            }
        });



        //HSC view

        Hsc_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent myIntent = new Intent(MainActivity.this,Hsc.class);
                startActivity(myIntent);
            }
        });




        //Dibs_View
        Dibs_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                Intent myIntent = new Intent(MainActivity.this,Dibs.class);
                startActivity(myIntent);
            }
        });


        // Nu_View
        Nu_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code
                Intent myIntent = new Intent(MainActivity.this,Nu.class);
                startActivity(myIntent);
            }
        });

        //Info_View
        Info_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code Intent
                Intent myIntent = new Intent(MainActivity.this,Info.class);
                startActivity(myIntent);
            }
        });


    }

    private int getCurrentVersionCode(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(),0);
        }catch (Exception e){
            Log.d("myApp",e.getMessage());
        }
        return  packageInfo.versionCode;
    }
    private void showUpdateDialogBox() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.hsc)
                .setTitle(getString(R.string.new_update))
                .setMessage(getString(R.string.new_update_text))
                .setCancelable(false)
                .setPositiveButton(Html.fromHtml("<h4 width:200px style=\"background-color:rgb(57, 233, 145);\" > Update Now. </h4>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playStoreLink))));
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Something Wrong ! Please try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();

    }


    }

