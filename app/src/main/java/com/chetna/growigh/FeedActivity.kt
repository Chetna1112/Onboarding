package com.chetna.growigh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        feedList = findViewById(R.id.feedList)
        adapter = FeedAdapter(this@FeedActivity, articles)
        feedList.adapter = adapter
        feedList.layoutManager = LinearLayoutManager(this@FeedActivity)

        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout)
        getFeed()
        swipeRefreshLayout.setOnRefreshListener {
            pageNum++
           getFeed()
            swipeRefreshLayout.isRefreshing=false
        }
        getFeed()

          // refreshRecyclerView(pageNum)

    }
    private fun refreshRecyclerView(pageNum:Int) {
        articles.clear()
        //pageNum=1
        getFeed()
    }


    private fun getFeed() {
        swipeRefreshLayout.isRefreshing=true
        val feed:Call<News> = NewsService.newsInstance.gateHeadlines("apple",pageNum)
        feed.enqueue(object :retrofit2.Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable)
            {
                swipeRefreshLayout.isRefreshing=false
                Log.d("Chetna","Error is fetching news",t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>)
            {

                swipeRefreshLayout.isRefreshing=false
                val feed:News?=response.body()
                if(feed!=null){
                    totalResults=feed.totalResults
                    // adapter= FeedAdapter(this@FeedActivity,feed.articles)
                    articles.clear()
                    articles.addAll(feed.articles)
                    adapter.notifyDataSetChanged()

                }
                // pageNum++
                //refreshRecyclerView()

            }
        })
        //refreshRecyclerView()
        // if(adapter.itemCount>10){
        //     adapter.notifyDataSetChanged()
        //     feedList.scrollToPosition(0)
        //    refreshRecyclerView()
        // }

    }


}
