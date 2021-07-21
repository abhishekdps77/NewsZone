package com.example.newsandupdates

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FirstScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button)
    }

    fun btnClicked(view: View) {
        when(view.id){
            R.id.internationalCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://www.newindianexpress.com/World/rssfeed/?id=171&getXmlFeed=true")
                intent.putExtra("title","INTERNATIONAL")
                context.startActivity(intent)
            }

            R.id.nationalCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://www.newindianexpress.com/Nation/rssfeed/?id=170&getXmlFeed=true")
                intent.putExtra("title","NATIONAL")
                context.startActivity(intent)
            }

            R.id.sportsCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://www.hindustantimes.com/feeds/rss/sports/rssfeed.xml")
                intent.putExtra("title","SPORTS")
                context.startActivity(intent)
            }

            R.id.businessCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://economictimes.indiatimes.com/rssfeedsdefault.cms")
                intent.putExtra("title","BUSINESS")
                context.startActivity(intent)
            }

            R.id.entertainmentCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://www.newindianexpress.com/Entertainment/Hindi/rssfeed/?id=195&getXmlFeed=true")
                intent.putExtra("title","ENTERTAINMENT")
                context.startActivity(intent)
            }

            R.id.editorialCard->{
                val context=view.context
                val intent=Intent(context,News::class.java)
                intent.putExtra("international","https://www.newindianexpress.com/Opinions/Editorials/rssfeed/?id=219&getXmlFeed=true")
                intent.putExtra("title","EDITORIAL")
                context.startActivity(intent)
            }
        }
    }
}