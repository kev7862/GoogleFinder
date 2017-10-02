package kev7862.github.googlefinder;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by matite on 10/2/17.
 */

//helps our UI run Swiftly
public class DataParser extends AsyncTask<Object, String, String>{

    String googlePlacesData;
    GoogleMap mMap;
    String Url;

    @Override
    protected String doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
