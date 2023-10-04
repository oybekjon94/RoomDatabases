package com.example.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.permissions.databinding.ActivityGalleryBinding
import com.example.permissions.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream

class GalleryActivity : AppCompatActivity() {

    lateinit var binding: ActivityGalleryBinding
    private val OLD_METHOD_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            oldMethodBtn.setOnClickListener {
                takePhotoFromGalleryOldMethod()
            }
            newMethodBtn.setOnClickListener {
                takePhotoFromGalleryNewMethod()
            }

            deleteBtn.setOnClickListener {

                val filesDir = filesDir
                if (filesDir.isDirectory == true){
                    val listFiles = filesDir.listFiles()
                    listFiles.forEach {
                        it.delete()
                    }
                }
            }
        }
    }

    private fun takePhotoFromGalleryNewMethod() {
        takePhotoResult.launch("image/*")
    }
    //new
    //mana shu narsa bizaga olgan rasmimizni fileda saqlashda yorsam beradi
    private val takePhotoResult = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
        if(uri == null) return@registerForActivityResult //rasm keladi va saqlab quyadi
        binding.img.setImageURI(uri)
        //fileni saqlash
        val openInputStream = contentResolver?.openInputStream(uri)
        val file  = File(filesDir, "${System.currentTimeMillis()}.jpg") //shu vaqt nomi b/n saqlab quyadi
        val fileOutputStream = FileOutputStream(file)
        openInputStream?.copyTo(fileOutputStream)
        openInputStream?.close()
        Log.d("File Path", "${file.absolutePath}")
    }


    //old
    private fun takePhotoFromGalleryOldMethod() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        //intent.type = "*/*" //xamma filelar kere bolsa
        intent.addCategory(Intent.CATEGORY_OPENABLE)
       startActivityForResult(intent,OLD_METHOD_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OLD_METHOD_REQUEST_CODE && resultCode == RESULT_OK){
            val uri = data?.data?: return //elvis operator
            binding.img.setImageURI(uri)
            //fileni saqlash
            val openInputStream = contentResolver?.openInputStream(uri)
            val file  = File(filesDir, "oybek.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.close()
            Log.d("File Path", "${file.absolutePath}") //absolutepath -> saqlagan joy
        }
    }
}