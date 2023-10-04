package com.example.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.permissions.databinding.ActivityCameraBinding
import com.example.permissions.databinding.ActivityGalleryBinding
import com.karumi.dexter.BuildConfig
import java.io.File
import java.io.IOException
import java.lang.Exception

class CameraActivity : AppCompatActivity() {
    lateinit var binding: ActivityCameraBinding
    lateinit var currentPhotoPath:String //shu yol boyicha file create qilanadi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            oldMethodBtn.setOnClickListener {
                takePhotoFromOldMethod()
            }
            newMethodBtn.setOnClickListener {
                takePhotoFromNewMethod()
            }
            deleteBtn.setOnClickListener {

            }
        }
    }

    private fun takePhotoFromNewMethod() {
        try {
            createImageFile()
        }catch (e:Exception){
            null
        }
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        //fileni uri formatga otkazish

    }

    private fun takePhotoFromOldMethod() {

    }

    @Throws(IOException::class) //chiqadigan filedagi exception majburlab quyamiz
    private fun createImageFile():File{
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("${System.currentTimeMillis()}",".jpg",externalFilesDir)
            .apply {
                currentPhotoPath = absolutePath
            }
    }


}