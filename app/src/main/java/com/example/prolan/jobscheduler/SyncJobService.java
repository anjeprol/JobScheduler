package com.example.prolan.jobscheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * Created by Prolan on 28/01/2016.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SyncJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new JobTask(this).execute(jobParameters);
        Toast.makeText(this, "Message Scheduled", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Toast.makeText(this, "Work done", Toast.LENGTH_LONG).show();
        return false;
    }
    private static class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
        private final JobService jobService;

        public JobTask(JobService jobService) {
            this.jobService = jobService;
        }

        @Override
        protected JobParameters doInBackground(JobParameters... params) {
            SystemClock.sleep(5000);
            return params[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            jobService.jobFinished(jobParameters, false);
        }
    }
}
