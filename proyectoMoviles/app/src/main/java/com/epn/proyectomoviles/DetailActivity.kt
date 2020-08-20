package com.epn.proyectomoviles

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import kotlin.collections.ArrayList


class DetailActivity : AppCompatActivity() {

    private lateinit var listDetail: ArrayList<String>
    private var db = FirebaseFirestore.getInstance()
    private lateinit var setting: FirebaseFirestoreSettings
    private lateinit var listTechers: ArrayList<String>


    private lateinit var idDetalle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setting = FirebaseFirestoreSettings.Builder()
            .setTimestampsInSnapshotsEnabled(true)
            .build()

        var list : Intent = intent
        listDetail = list.getStringArrayListExtra("Detalle")

        val medidasVentana = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(medidasVentana)

        val ancho: Int = medidasVentana.widthPixels
        val alto: Int = medidasVentana.heightPixels

        //tamaño de pantalla emergente
        window.setLayout((ancho * 0.85).toInt(), (alto * 0.80).toInt())


        textViewMateriaDetal.text = listDetail.get(1)
        textViewTopicDetail.text = listDetail.get(2)
        textViewLugarDetail.text = listDetail.get(3)
        textViewDateDetail.text = listDetail.get(4)

        val builder = StringBuilder()
        builder.append("Desde: ")
            .append(listDetail.get(5))
            .append("       Hasta: ")
            .append(listDetail.get(6))

        textViewTimeDetail.text = builder

        buttonConfirmDetail.setOnClickListener {

            //Guardar en la base de datos para mostrar luego en servicios
            addDetailFirestore()

        }

        buttonCancelDetail.setOnClickListener {
            val regresar = Intent(this,activity_navegation::class.java)
            startActivity(regresar)
            finish()
        }

    }

    private fun addDetailFirestore() {

        val builder = StringBuilder()
        builder.append("00")
            .append(listDetail.get(4).substring(0,2))
            .append(listDetail.get(4).substring(5,7))
            .append(listDetail.get(4).substring(12))
            .append(listDetail.get(5).substring(0,2))
            .append(listDetail.get(6).substring(0,2))
        idDetalle = builder.toString()

        //Toast.makeText(applicationContext,id,Toast.LENGTH_LONG).show()

        val horario = StringBuilder()
        horario.append(listDetail.get(5))
            .append(" - ")
            .append(listDetail.get(6))

        val random = Random()
        println(random.nextInt(1..6))
        val estrellas = random.nextInt(1..6)
        val indexProfesor = random.nextInt(1..6)

        var nuevoDetalle= Detail(idDetalle, listDetail.get(4),"Efectivo", horario.toString(), listDetail.get(3),
            "Julio Rosero",listDetail.get(0),1.80, estrellas, listDetail.get(1), listDetail.get(2))


        db!!.collection("registroDetalleServicio").add(nuevoDetalle)
            .addOnSuccessListener{documentSnapshot->

                val intent = Intent(this,ProvideService::class.java)
                intent.putExtra("idDetalle",idDetalle)
                startActivity(intent)
                finish()
                //Toast.makeText(applicationContext,idDetalle,Toast.LENGTH_LONG).show()
            }

    }

    private fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    private fun profesores(){
        listTechers.add("Rogel Laza")
        listTechers.add("")
        listTechers.add("")
        listTechers.add("")
        listTechers.add("")
        listTechers.add("")
    }
}
