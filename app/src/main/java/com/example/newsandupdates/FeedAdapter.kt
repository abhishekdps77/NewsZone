package com.example.newsandupdates

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class ViewHolder(v:View){
    val tvTitle:TextView=v.findViewById(R.id.tvTitle)
    val tvDescription:TextView=v.findViewById(R.id.tvDescription)
    val tvStory:TextView=v.findViewById(R.id.tvStory)
    val tvImageLink:TextView=v.findViewById(R.id.tvImageLink)
    val tvNewsLink:TextView=v.findViewById(R.id.tvNewsLink)
    val tvPubDate:TextView=v.findViewById(R.id.tvPubDate)
    val imageView:ImageView=v.findViewById(R.id.imageView)
}

class FeedAdapter (context: Context, private val resource :Int, private val applications :List<FeedEntry>)
    :ArrayAdapter<FeedEntry>(context,resource){


    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return applications.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view:View
        val viewHolder:ViewHolder
        if(convertView==null)
        {
            view=inflater.inflate(resource,parent,false)
            viewHolder=ViewHolder(view)
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ViewHolder
        }
        val currentApp=applications[position]

        viewHolder.tvTitle.text=currentApp.title
        viewHolder.tvDescription.text=currentApp.description
        //viewHolder.tvStory.text=currentApp.story
        //viewHolder.tvImageLink.text=currentApp.imageLink
        //viewHolder.tvNewsLink.text=currentApp.newsLink
        viewHolder.tvPubDate.text=currentApp.pubDate
        //viewHolder.tvStory.text= currentApp.paragraphs.toString()


        viewHolder.tvNewsLink.setOnClickListener{
            //val queryUrl: Uri = Uri.parse(viewHolder.tvNewsLink.text.toString())
            val queryUrl: Uri = Uri.parse(currentApp.newsLink)
            val intent= Intent(Intent.ACTION_VIEW,queryUrl)
            context.startActivity(intent)
        }

        Glide.with(context).load(currentApp.imageLink).into(viewHolder.imageView)

        return view
    }

}