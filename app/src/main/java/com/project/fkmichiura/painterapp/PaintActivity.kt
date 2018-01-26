package com.project.fkmichiura.painterapp

import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout

class PaintActivity : AppCompatActivity() {

    private var drawView : DrawingView? = null
    private var currentPaint : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        val paintLayout : LinearLayout = findViewById(R.id.paint_colors)
        drawView = findViewById(R.id.drawing)
        currentPaint = paintLayout.getChildAt(0) as ImageButton

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            currentPaint!!.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed, theme))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.paint_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId  == R.id.menu_new){
            newAlertDialog()
        }
        return true
    }

    fun paintClicked(view : View){
        if(view != currentPaint){
            var imgView = view as ImageButton
            var color : String = view.tag.toString()

            //atribui cor selecionada para iniciar o desenho
            drawView?.setColor(color)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imgView.setImageDrawable(resources.getDrawable(R.drawable.paint_pressed, theme))
                currentPaint?.setImageDrawable(resources.getDrawable(R.drawable.paint, theme))
                currentPaint = view
            }
        }
    }

    fun newAlertDialog(){
        val newDialog = AlertDialog.Builder(this)
        newDialog.setTitle("Novo desenho")
        newDialog.setMessage("Iniciar um novo desenho (você perderá seu desenho anterior)?")

        newDialog.setPositiveButton("OK"){
            newDialog, _ -> drawView?.startNewDraw()
            newDialog.dismiss()
        }

        newDialog.setNegativeButton("Cancelar"){
            newDialog, _ -> newDialog.dismiss()
        }
        newDialog.create()
        newDialog.show()
    }
}
