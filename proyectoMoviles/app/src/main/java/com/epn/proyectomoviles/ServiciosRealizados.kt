package com.epn.proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_servicios_realizados.*

class ServiciosRealizados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios_realizados)


        val servicio =Servicio(1, "Katherine Sango", "01/01/2019", "4.50".toFloat(), R.drawable.matematicas1)
        val servicio1 =Servicio(2, "Roger Laza", "13/07/2019", "8.00".toFloat(), R.drawable.geometria)
        val servicio2 =Servicio(3, "Jonathan Vargas", "19/11/2019", "10.50".toFloat(), R.drawable.matematicas1)
        val servicio3=Servicio(4, "David Rosero", "30/08/2019", "6.50".toFloat(), R.drawable.fisica1)
        val servicio4 =Servicio(5, "Katherine Sango", "06/05/2019", "4.50".toFloat(), R.drawable.quimica1)
        val servicio5 =Servicio(6, "Jonathan Vargas", "23/03/2018", "10.50".toFloat(), R.drawable.geometria)

        val listaServicios = listOf(servicio, servicio1, servicio2, servicio3, servicio4, servicio5)

        val adapter =ServicioAdapter(this, listaServicios)

        listaAyuda.adapter=adapter




    }


    fun onClickAtras(view: View){
        var atras = Intent(this, activity_navegation::class.java)
        startActivity(atras)
    }


}
