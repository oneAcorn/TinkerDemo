package com.acorn.test.tinkerdemo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tencent.tinker.lib.tinker.TinkerInstaller

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askForRequiredPermissions()

        findViewById<android.view.View?>(R.id.loadPatchBtn)?.setOnClickListener {
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),
                android.os.Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/patch_signed_7zip.apk"
            )
        }
        findViewById<View>(R.id.toastBtn)?.setOnClickListener {
            Toast.makeText(this, ".312..iiiiiiiii", Toast.LENGTH_SHORT).show()
        }
    }

    private fun askForRequiredPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            return
        }
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                0
            )
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= 16) {
            val res = ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            res == PackageManager.PERMISSION_GRANTED
        } else {
            // When SDK_INT is below 16, READ_EXTERNAL_STORAGE will also be granted if WRITE_EXTERNAL_STORAGE is granted.
            val res = ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            res == PackageManager.PERMISSION_GRANTED
        }
    }
}