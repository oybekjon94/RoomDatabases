package com.example.permissions

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissions.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            galleryBtn.setOnClickListener{
                 val intent = Intent(this@MainActivity,GalleryActivity::class.java)
                startActivity(intent)
            }
            cameraBtn.setOnClickListener {
                val intent  = Intent(this@MainActivity,CameraActivity::class.java)
                startActivity(intent)

            }
            permissionBtn.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.RECORD_AUDIO) //record audioga
                ==PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.READ_SMS) //sms readga
                    ==PackageManager.PERMISSION_GRANTED){   //dostup berilganmi?
                    Toast.makeText(this@MainActivity,"Granted",Toast.LENGTH_SHORT).show()
                }else{
                    requestPermissionForRecordAudio()
                }
            }
            //pastdagisi androidning yangi feature
            //oson va qisqa
            newPermissionBtn.setOnClickListener {
               requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }

            dexterBtn.setOnClickListener {
                Dexter.withContext(this@MainActivity)
                    .withPermission(Manifest.permission.RECORD_AUDIO)
                    .withListener(object : PermissionListener {
                        override fun onPermissionGranted(response: PermissionGrantedResponse) {
                            Toast.makeText(this@MainActivity, "Granted", Toast.LENGTH_SHORT).show()
                        }

                        override fun onPermissionDenied(response: PermissionDeniedResponse) {
                            //umuman user tomondan ruhsat berilmagan bolsa
                            if (response.isPermanentlyDenied){
                                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                val uri = Uri.fromParts("package", packageName, null)
                                intent.data = uri
                                startActivity(intent)
                            }else{
                                val dialog = AlertDialog.Builder(this@MainActivity)
                                dialog.setTitle("Ovoz yozish")
                                dialog.setMessage("Messenger appda ovoz yuborish funksiyasi qushilgan" +
                                        ".shu holat uchun dasturga ruhsat berish kerak")
                                dialog.setPositiveButton("Ruhsat berish"){p0,p1 ->
                                    ActivityCompat.requestPermissions(
                                        this@MainActivity,
                                        arrayOf(Manifest.permission.RECORD_AUDIO),
                                        2
                                    )

                                }

                                dialog.setNegativeButton("Ruhsat bermaslik") { dialog, which ->
                                    dialog?.dismiss() // shunchaki chiqib ketsin
                                }
                                dialog.show()
                                response.requestedPermission.name
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            permission: PermissionRequest?,
                            token: PermissionToken?,
                        ) {
                            val dialog = AlertDialog.Builder(this@MainActivity)
                            dialog.setTitle("Ovoz yozish")
                            dialog.setMessage("Messenger appda ovoz yuborish funksiyasi qushilgan" +
                                    ".shu holat uchun dasturga ruhsat berish kerak")
                            dialog.setPositiveButton("Ruhsat berish"){p0,p1 ->
                                token?.continuePermissionRequest()

                            }

                            dialog.setNegativeButton("Ruhsat bermaslik") { dialog, which ->
                                dialog?.dismiss() // shunchaki chiqib ketsin
                            }
                            dialog.show()
                        }
                    }).check()
            }
        }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted ->
            if (isGranted){

            }else{

            }
        }

    private fun requestPermissionForRecordAudio() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.RECORD_AUDIO
        )){
            //bu yerda ikkinchi marta soralganda ishga tushadi
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Ovoz yozish")
            dialog.setMessage("Messenger appda ovoz yuborish funksiyasi qushilgan" +
                    ".shu holat uchun dasturga ruhsat berish kerak")
            dialog.setPositiveButton("Ruhsat berish",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_SMS),
                        1
                    )
                }
            })

            dialog.setNegativeButton("Ruhsat bermaslik"
            ) { dialog, which ->
                dialog?.dismiss() // shunchaki chiqib ketsin
            }
            dialog.show()

        }else{
            //runtime permissionga dostup berish
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_SMS),
                1
            )
        }
    }
    //permission resultini bilib turish uchun
    //biz permissionga allow qildikmi yoki denoy qildikmi shuni resulti keladi
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            //process
            Log.d("First","Granted")
        }else if (// bu yerda ikkinchi marta soralyabdimi?
         requestCode == 2)  {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Second","Granted")
            }else{
                //bu yerda oxir oqibat albatta ruhsat olish kere bolganda
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }

    }
}