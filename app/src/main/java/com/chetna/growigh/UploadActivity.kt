package com.chetna.growigh

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import com.chetna.growigh.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private var imageURL: Uri?=null
    private lateinit var dialog: Dialog
    private var launchGalleryActivity=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode== Activity.RESULT_OK){
            imageURL=it.data!!.data
            binding.uploadImg.setImageURI(imageURL)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        binding.apply {
            select.setOnClickListener {
                val intent= Intent("android.intent.action.GET_CONTENT")
                //only gallery images
                intent.type="image/*"
                launchGalleryActivity.launch(intent)
            }

        }
        setContentView(binding.root)
    }
}