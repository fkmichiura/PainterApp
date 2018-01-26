package com.project.fkmichiura.painterapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Fabio on 26/01/2018.
 */

class DrawingView(context: Context?, attrs: AttributeSet?) : View(context, attrs){

    val drawPath : Path? = null
    val drawPaint : Paint? = null
    val canvasPaint : Paint? = null
    val canvas : Canvas? = null
    val bitmap : Bitmap? = null

    val color = 0xFF660000

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawBitmap(bitmap, 0f, 0f, canvasPaint)
        canvas?.drawPath(drawPath, drawPaint)
    }

    //Ações de desenho baseado nos controles de toque
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event!!.x
        val touchY = event!!.y

        when(event?.action){
            MotionEvent.ACTION_DOWN ->
                drawPath?.moveTo(touchX, touchY)
            MotionEvent.ACTION_MOVE ->
                drawPath?.lineTo(touchX, touchY)
            MotionEvent.ACTION_UP -> {
                canvas?.drawPath(drawPath, drawPaint)
                drawPath?.reset()
            }
            else ->
                return false
        }
        invalidate()
        return true
    }
}


