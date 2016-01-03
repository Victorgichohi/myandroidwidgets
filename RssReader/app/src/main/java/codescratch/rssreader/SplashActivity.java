package codescratch.rssreader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

public class SplashActivity extends Activity {

    private String RSSFEEDURL = "http://www.mobilenations.com/rss/mb.xml";
    RSSFeed feed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() == null
                && !conMgr.getActiveNetworkInfo().isConnected()
                && !conMgr.getActiveNetworkInfo().isAvailable()) {
// No connectivity - Show alert
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "Unable to reach server, \nPlease check your connectivity.")
                    .setTitle("TD RSS Reader")
                    .setCancelable(false)
                    .setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    finish();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();

        } else {
// Connected - Start parsing
            new AsyncLoadXMLFeed().execute();

        }

    }

    private class AsyncLoadXMLFeed extends AsyncTask {

        @Override
        protected Void doInBackground(Void... params) {

// Obtain feed
            DOM_Parser myParser = new DOM_Parser();
            feed = myParser.parseXml(RSSFEEDURL);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            Bundle bundle = new Bundle();
            bundle.putSerializable("feed", feed);

// launch List activity
            Intent intent = new Intent(SplashActivity.this, ListActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

// kill this activity
            finish();
        }
    }
}