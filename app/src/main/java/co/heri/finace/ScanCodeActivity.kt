package co.heri.finace

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Vibrator
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.view.SurfaceHolder
import android.widget.Toast
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_scan_code.*
import java.io.IOException

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ScanCodeActivity : Activity() {

    private var mVisible: Boolean = false


    lateinit var barcodedector: BarcodeDetector;
    lateinit var camerasourece: CameraSource;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scan_code)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        barcodedector = BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE).build()
        camerasourece = CameraSource.Builder(this, barcodedector)
                .setRequestedFps(15.0f)
                .build()

        camera_preview.holder.addCallback(object: SurfaceHolder.Callback{


            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                camerasourece.stop()
            }

            @SuppressLint("MissingPermission")
            override fun surfaceCreated(holder: SurfaceHolder?) {

                try {
                    camerasourece.start(holder)
                }catch (e: IOException){
                    e.printStackTrace()
                }

            }

        })

        barcodedector.setProcessor(object: Detector.Processor<Barcode>{
            override fun release() {
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                var qrCodes: SparseArray<Barcode> = detections!!.detectedItems

                val size = qrCodes?.size()

                if(size != 0){

                    scan_result.post {
                        var vibrator: Vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(200)
                        scan_result.text = qrCodes?.get(0)?.rawValue
                    }

                    barcodedector.release()
                    //Toast.makeText(applicationContext, qrCodes?.get(0)?.displayValue, Toast.LENGTH_SHORT).show()


                }


            }

        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }




}




