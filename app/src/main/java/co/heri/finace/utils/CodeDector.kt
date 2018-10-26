package co.heri.finace.utils

import android.util.SparseArray
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.graphics.YuvImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import java.io.ByteArrayOutputStream


internal class CodeDector(private val mDelegate: Detector<Barcode>, private val boxWidth: Int, private  val boxHeight: Int) : Detector<Barcode>() {

    override fun detect(qrCode: Frame): SparseArray<Barcode> {

        /*val width = qrCode.metadata.width
        val height = qrCode.metadata.height
        val right = width / 2 + boxHeight / 2
        val left = width / 2 - boxHeight / 2
        val bottom = height / 2 + boxWidth / 2
        val top = height / 2 - boxWidth / 2

        val yuvImage = YuvImage(qrCode.getGrayscaleImageData().array(), FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21, width, height, null)
        val byteArrayOutputStream = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(left, top, right, bottom), 100, byteArrayOutputStream)
        val jpegArray = byteArrayOutputStream.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(jpegArray, 0, jpegArray.size)

        val croppedFrame = Frame.Builder()
                .setBitmap(bitmap)
                .setRotation(qrCode.metadata.rotation)
                .build()*/

//        return mDelegate.detect(croppedFrame)
        return mDelegate.detect(qrCode)
    }

    override fun isOperational(): Boolean {
        return mDelegate.isOperational
    }

    override fun setFocus(id: Int): Boolean {
        return mDelegate.setFocus(id)
    }
}