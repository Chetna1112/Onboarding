package com.chetna.growigh

import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chetna.growigh.model.OnBoardingData

class OnboardingAdapter(private val onBoardingData:List<OnBoardingData>):
    RecyclerView.Adapter<OnboardingAdapter.OnboardingDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingDataViewHolder {
       return OnboardingDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slider_layout,parent,false))
    }

    override fun onBindViewHolder(holder: OnboardingDataViewHolder, position: Int) {
       holder.bind(onBoardingData[position])
    }

    override fun getItemCount(): Int {
        val itemCount=onBoardingData.size
       return onBoardingData.size
    }
    inner class OnboardingDataViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val image=view.findViewById<ImageView>(R.id.titleImage)
        private val title=view.findViewById<TextView>(R.id.title)
        private val description=view.findViewById<TextView>(R.id.description)
        fun bind(onBoardingData: OnBoardingData){
            image.setImageResource(onBoardingData.imageUrl)
            title.text=onBoardingData.title
            description.text=onBoardingData.desc
        }
    }

}