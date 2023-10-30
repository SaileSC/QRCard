package com.qrcard.iu.fragment.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import com.squareup.picasso.Transformation

class RoundedCornersTransformation(private val radius: Int) : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(source.width, source.height, source.config)
        val canvas = Canvas(result)
        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawRoundRect(RectF(0f, 0f, source.width.toFloat(), source.height.toFloat()), radius.toFloat(), radius.toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(source, 0f, 0f, paint)
        source.recycle()
        return result
    }

    override fun key(): String {
        return "rounded_corners"
    }
}
