package com.example.newsandupdates

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.news.*
import java.net.URL
import kotlin.properties.Delegates

class News : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news)

        val title=intent?.extras?.getString("title").toString()
        setTitle(title)

        val rssFeedUrl=intent?.extras?.getString("international").toString()

        val downloadDataInternational= DownloadData(this,xmlListView)
        downloadDataInternational.execute(rssFeedUrl)

        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_one)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        class DownloadData(context: Context, listView: ListView) : AsyncTask<String, Void, String>(){

            var propContext:Context by Delegates.notNull()
            var propListView:ListView by Delegates.notNull()

            init {
                propContext=context
                propListView=listView
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
                var parseApplication=ParseApplications()
                parseApplication.parse(result)

                var feedAdapter=FeedAdapter(propContext,R.layout.list_record,parseApplication.applications)
                propListView.adapter=feedAdapter
            }

            override fun doInBackground(vararg url: String?): String {
                val rssFeed=downloadXmlInternational(url[0])
                if(rssFeed.isEmpty()){
                    Log.e("DownloadDataNational", "error downloading the feed")
                }
                return rssFeed
            }

            private fun downloadXmlInternational(urlPath: String?): String {
                return URL(urlPath).readText()
            }
        }
    }
}