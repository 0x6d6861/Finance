package co.heri.finace

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.SparseArray
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.view.SurfaceHolder
import co.heri.finace.utils.CodeDector
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_scan_code.*
import java.io.IOException
import com.google.android.gms.vision.MultiProcessor
import co.heri.finace.utils.BarcodeGraphic
import co.heri.finace.utils.BarcodeTrackerFactory
import co.heri.finace.utils.GraphicOverlay



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


        val myDector = CodeDector(barcodedector, 420, 640)

       // val mGraphicOverlay = findViewById<GraphicOverlay<BarcodeGraphic>>(R.id.graphicOverlay)



        // TODO: check if carema has autofocus
        camerasourece = CameraSource.Builder(this, myDector)
                //.setRequestedFps(15.0f)
                .setAutoFocusEnabled(true)
                //.setRequestedPreviewSize(480, 360)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .build()

        camera_preview.holder.addCallback(object: SurfaceHolder.Callback2{
            override fun surfaceRedrawNeeded(holder: SurfaceHolder?) {

            }


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

        myDector.setProcessor(object: Detector.Processor<Barcode>{
            override fun release() {
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                var qrCodes: SparseArray<Barcode> = detections!!.detectedItems

                val size = qrCodes.size()

                if(size != 0){

                    myDector.release()
                    scan_result.post {
                        // TODO: check if it is the right format, and check if the string is in expected format
                        var vibrator: Vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        vibrator.vibrate(200)
                        scan_result.text = qrCodes.valueAt(0).displayValue
                    }

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




