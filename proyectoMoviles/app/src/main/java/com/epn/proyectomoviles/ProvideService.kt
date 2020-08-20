package com.epn.proyectomoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_provide_service.*

class ProvideService : AppCompatActivity() {

    private lateinit var idDetalle: String
    private var db = FirebaseFirestore.getInstance()

    private lateinit var fecha: String
    private lateinit var precio: String
    private lateinit var tipoPago: String
    private lateinit var profesor: String
    private lateinit var lugar: String
    private var horario: String = "00:00 - 00:00"

    var hour: Int = 0
    var minute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provide_service)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        var intent : Intent = intent
        idDetalle = intent.getStringExtra("idDetalle")
        //Toast.makeText(applicationContext,idDetalle,Toast.LENGTH_LONG).show()

        //obtener datos de la base
        db.collection("registroDetalleServicio").whereEqualTo("id",idDetalle).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    fecha = document.data.get("fecha").toString()
                    textViewDateService.text = "Fecha: $fecha"
                    precio = document.data.get("valor").toString()
                    textViewPrecio.text = "Precio: USD $precio"
                    tipoPago = document.data.get("formaPago").toString()
                    textViewTipoPago.text = "Tipo de Pago: $tipoPago"
                    profesor = document.data.get("profesor").toString()
                    editTextTeacherName.setText("Profesor: $profesor")
                    lugar = document.data.get("lugar").toString()
                    editTextLugar.setText("Lugar: $lugar")
                    horario = document.data.get("horario").toString()
                    textViewHorario.text = "Horario: $horario"
                }

            }
            .addOnFailureListener { exception ->
                Toast.makeText(applicationContext,"Error getting user information: $exception", Toast.LENGTH_LONG).show()
            }

        this.time(textViewHorario.text.toString())
    }


    private fun time(hora: String){


        Toast.makeText(applicationContext,"$hora", Toast.LENGTH_LONG).show()

        var hourStart = hora.substring(0,2).toInt()
        var minuteStart = hora.substring(3,5).toInt()
        var hourEnd = hora.substring(8,10).toInt()
        var minuteEnd = hora.substring(11).toInt()

        Log.d("Timer","$hourStart $hourEnd $minuteStart $minuteEnd")
        hour = hourEnd - hourStart
        //Log.d("Timer","$hour")
        minute = minuteEnd - minuteStart
        //Log.d("Timer","$minute")

        if (hour < 0)
            hour = (-1) * hour
        if (minute < 0)
            minute = (-1) * minute

        textViewTime.setText(String.format("%02d:%02d:00",hour, minute))
    }

    fun startReloj(view: View){

        var timeHour = this.hour * 3600000
        var timeMinute = this.minute * 60000
        var timeStart = (timeHour + timeMinute).toLong()

        object : CountDownTimer(timeStart, 1000) {

            override fun onTick(millisUntilFinished: Long) {

                val horasRestantes = (millisUntilFinished / 1000) / 60 / 60
                val minutosRestantes = ((millisUntilFinished / 1000) / 60) %60
                val segundosRestantes = (millisUntilFinished / 1000) % 60
                var tiempoString = String.format("%02d:%02d:%02d",horasRestantes, minutosRestantes, segundosRestantes)
                textViewTime.setText(tiempoString)

            }
            override fun onFinish() {
                textViewTime.setText("00:00:00")
            }
        }.start()

    }

}
