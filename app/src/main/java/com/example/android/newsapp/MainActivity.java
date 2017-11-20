package com.example.android.newsapp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private Bundle bundle;
    private ListView listView;
    private ArrayList<News> news = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News item = news.get(position);
                String url = item.getWebUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            bundle = new Bundle();
            bundle.putString("url", "http://content.guardianapis.com/search?q=Kurdistan&show-tags=contributor&order-by=newest&api-key=test");
            getSupportLoaderManager().initLoader(1, bundle, MainActivity.this).forceLoad();


        } else {
            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void UpdateUi(String jsonData) {
        news = NewsUti.ExtraData(jsonData);
        NewsArrayAdapter adapter = new NewsArrayAdapter(this, 0, news);
        listView.setAdapter(adapter);

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        return new NewsAsyncTaskLoader(this, args.getString("url"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        UpdateUi(data);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
