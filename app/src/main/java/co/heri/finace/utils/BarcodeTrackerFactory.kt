package co.heri.finace.utils

import android.content.Context
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.MultiProcessor
import com.google.android.gms.vision.Tracker


internal class BarcodeTrackerFactory(private val mGraphicOverlay: GraphicOverlay<BarcodeGraphic>,
                                     private val mContext: Context) : MultiProcessor.Factory<Barcode> {

    override fun create(barcode: Barcode): Tracker<Barcode> {
        val graphic = BarcodeGraphic(mGraphicOverlay)
        return BarcodeGraphicTracker(mGraphicOverlay, graphic, mContext)
    }

}