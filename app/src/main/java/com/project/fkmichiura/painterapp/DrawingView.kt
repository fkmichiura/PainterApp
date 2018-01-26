package com.project.fkmichiura.painterapp

import android.content.Context
import android.graphics.*
import android.graphics.Paint.DITHER_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Fabio on 26/01/2018.
 */

class DrawingView : View{

    var drawPath : Path? = null
    var drawPaint : Paint? = null
    var canvasPaint : Paint? = null
    var canvas : Canvas? = null
    var bitmap : Bitmap? = null

    var color : Int? = 0xFF660000.toInt()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setupDrawing()
    }

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    fun setColor(newColor : String){
        invalidate()

        color = Color.parseColor(newColor)
        drawPaint?.color = color!!
    }

    fun setupDrawing(){
        drawPath = Path()
        drawPaint = Paint()

        drawPaint?.color = color!!
        drawPaint?.isAntiAlias = true
        drawPaint?.strokeWidth = 20F
        drawPaint?.style = Paint.Style.STROKE
        drawPaint?.strokeJoin = Paint.Join.ROUND
        drawPaint?.strokeCap = Paint.Cap.ROUND

        canvasPaint = Paint(DITHER_FLAG)
    }

    fun startNewDraw(){
        canvas?.drawColor(0, PorterDuff.Mode.CLEAR)
        invalidate()
    }
}


