package ahmed.praicticing.compose_practice


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Context

class permissionManager(private val context: Context) {
    companion object {
        val REQUIRED_PERMISSIONS_PRE_T = arrayOf(
            READ_EXTERNAL_STORAGE,
            CAMERA,
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
        )
        val REQUIRED_PERMISSIONS_POST_T = arrayOf(
            READ_MEDIA_IMAGES,
            CAMERA,
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
        )
    }
}