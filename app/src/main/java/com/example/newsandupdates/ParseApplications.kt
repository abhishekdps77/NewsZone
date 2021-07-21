package com.example.newsandupdates

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class ParseApplications {
    val applications = ArrayList<FeedEntry>()
    var count:Int=0
    fun parse(xmlData: String): Boolean {
        var status = true
        var inItem = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType

            var currentRecord=FeedEntry()

            while (eventType!=XmlPullParser.END_DOCUMENT && count<=35){
                var tagName=xpp.name?.toLowerCase()
                when(eventType){
                    XmlPullParser.START_TAG->{
                        if(tagName=="item"){
                            inItem=true
                            count++
                        }
                    }

                    XmlPullParser.TEXT->textValue=xpp.text

                    XmlPullParser.END_TAG->{
                        if(inItem){
                            when(tagName){
                                "item"->{
                                    applications.add(currentRecord)
                                    inItem=false
                                    currentRecord = FeedEntry()
                                   // paragraphs.clear()
                                }

                                "title"->currentRecord.title=textValue
                                "pubdate"->currentRecord.pubDate=textValue
                                "description"->currentRecord.description=textValue
                                "story"->currentRecord.story=textValue
                                "image"->currentRecord.imageLink=textValue
                                "link"->currentRecord.newsLink=textValue
//                                "p"->{
//                                    paragraphs.add(textValue)
//                                    currentRecord.paragraphs=paragraphs
//                                }
                            }
                        }
                    }
                }
                eventType=xpp.next()
            }
        } catch (e: Exception) {
            status = false
        }
        return status
    }

}