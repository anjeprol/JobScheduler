package com.example.prolan.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 12345 ;
    private static final String TAG = "AAA_app";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JobInfo job = new JobInfo.Builder(JOB_ID, new ComponentName(this, SyncJobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .setOverrideDeadline(60000) //the job will be executed anyway after one hour
                .build();

        //Starting the job
        JobScheduler scheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(job);
        if (result == JobScheduler.RESULT_SUCCESS){
            Toast.makeText(this, "Job scheduled successfully!", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Job scheduled successfully!");
        }

    }

}
