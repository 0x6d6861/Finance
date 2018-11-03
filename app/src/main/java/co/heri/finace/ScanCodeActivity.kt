package co.heri.finace

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.SparseArray
import androidx.core.app.NavUtils
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.heri.finace.utils.barcode.BarcodeReader
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.activity_scan_code.*


class ScanCodeActivity : AppCompatActivity(), BarcodeReader.BarcodeReaderListener {

    private var barcodeReader: BarcodeReader = BarcodeReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scan_code)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        barcodeReader = supportFragmentManager.findFragmentById(R.id.barcode_scanner) as BarcodeReader
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


    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {

    }

    override fun onScannedMultiple(barcodes: MutableList<Barcode>?) {

    }

    override fun onCameraPermissionDenied() {
        Toast.makeText(applicationContext, "Camera permission denied!", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onScanned(barcode: Barcode) {

        // TODO: check if it is the right format, and check if the string is in expected format

        scan_result.post {
            //barcodeReader.playBeep()
            val vibrator: Vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            vibrator.vibrate(200)
            barcodeReader.playBeep()
            scan_result.text = barcode.displayValue
        }


    }

    override fun onScanError(errorMessage: String?) {

    }

}




