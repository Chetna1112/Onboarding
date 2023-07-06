package com.chetna.growigh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {
    lateinit var adapter: FeedAdapter
    private var articles:MutableList<Article> = mutableListOf()
    var pageNum = 1
    var totalResults = -1
    val TAG = "MainActivity"
    lateinit var feedList:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        feedList = findViewById(R.id.feedList)
        adapter = FeedAdapter(this@FeedActivity, articles)
        feedList.adapter = adapter
        feedList.layoutManager = LinearLayoutManager(this@FeedActivity)
        getFeed()
    }

    private fun getFeed() {
       val feed:Call<News> = NewsService.newsInstance.gateHeadlines("in",pageNum)
        feed.enqueue(object :retrofit2.Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Chetna","Error is fetching news",t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {

                val feed:News?=response.body()
                if(feed!=null){
                    totalResults=feed.totalResults
                        // adapter= FeedAdapter(this@FeedActivity,feed.articles)
                    articles.addAll(feed.articles)
                    adapter.notifyDataSetChanged()


                }
            }
        })


        }

    }
