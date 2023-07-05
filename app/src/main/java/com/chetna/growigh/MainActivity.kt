package com.chetna.growigh

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.chetna.growigh.model.OnBoardingData
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var onBoardingDataAdapter: OnboardingAdapter
    private lateinit var indicator: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        sharedPreferences=getSharedPreferences("MyPrefernces", Context.MODE_PRIVATE)
        if(isFirstLaunch()){
            setOnboardingItems()
            setFirstLaunch(false)
        }
       else{
           startActivity(Intent(applicationContext,HomeActivity::class.java))
            finish()
        }
    }

    private fun setFirstLaunch(isFirstLaunch: Boolean) {
    sharedPreferences.edit().putBoolean("isFirstLaunch",isFirstLaunch).apply()
    }

    private fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("isFirstLaunch",true)
    }


    private fun setOnboardingItems() {
        onBoardingDataAdapter = OnboardingAdapter(
            listOf(
                OnBoardingData(
                    imageUrl = R.drawable.amico,
                    title = "About Us",
                    desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
                ),
                OnBoardingData(
                    imageUrl = R.drawable.pana,
                    title = "Our Mission",
                    desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
                ),
                OnBoardingData(
                    imageUrl = R.drawable.bro,
                    title = "Our Vision",
                    desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
                )
            )
        )
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = onBoardingDataAdapter
        viewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
        (viewPager.getChildAt(0) as RecyclerView).overScrollMode=
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.progress).setOnClickListener {
            if(viewPager.currentItem+1<onBoardingDataAdapter.itemCount){
               viewPager.currentItem+=1
            }
            else
            {
                navigateToHome()
            }
        }
        findViewById<TextView>(R.id.skipButton).setOnClickListener {
            navigateToHome()
        }

    }
    private fun navigateToHome(){
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

}