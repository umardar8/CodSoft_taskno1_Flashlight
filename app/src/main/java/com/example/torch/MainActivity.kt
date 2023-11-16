package com.example.torch

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    var cameraFlash = false
    var flashOn = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bulb: ImageButton = findViewById(R.id.bulb)
        cameraFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        bulb.setOnClickListener {
            if (cameraFlash) {
                if (flashOn) {
                    flashOn = false
                    bulb.setImageResource(R.drawable.off)
                    cameraFlashOff()
                } else {
                    flashOn = true
                    bulb.setImageResource(R.drawable.on)
                    cameraFlashOn()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cameraFlashOn() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, true)
        } catch (e: Exception) {}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cameraFlashOff() {
        val cameraManager: CameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        var cameraId: String
        try {
            cameraId = cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraId, false)
        } catch (e: Exception) {}
    }
}