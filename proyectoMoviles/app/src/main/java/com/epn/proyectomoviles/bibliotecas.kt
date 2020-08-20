package com.epn.proyectomoviles

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class bibliotecas : AppCompatActivity(), AdapterView.OnItemClickListener  {


    private lateinit var listView: ListView
    private lateinit var imagen_AdapterBi : ImageAdapterBi
    private lateinit var listValues : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bibliotecas)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        listValues = ArrayList<String>(15)
        listView = findViewById(R.id.idListaBib)

        //extraer datos enviados de topicos
        var objecto : Intent = intent
        listValues = objecto.getStringArrayListExtra("valores")


        listView.setOnItemClickListener(this)
        imagen_AdapterBi  = ImageAdapterBi(this)
        listView.adapter = imagen_AdapterBi

        imagen_AdapterBi.notifyDataSetChanged()


        var regresarMaterias :ImageButton = findViewById(R.id.imageButonRegresar)


        regresarMaterias.setOnClickListener {
            val regresarAMaterias = Intent(this,Topicos::class.java)
            regresarAMaterias.putExtra("nombreUser",listValues.get(0))
            regresarAMaterias.putExtra("materia",listValues.get(1))
            startActivity(regresarAMaterias)
        }


    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        //listValues.clear()

        var biblioteca : String = ""

        if(position == 0){
            biblioteca = "Ciencias Sociales"
            listValues.add(biblioteca)
        }
        if(position == 1){
            biblioteca = "Formación Básica"
            listValues.add(biblioteca)
        }
        if(position == 2){
            biblioteca = "General"
            listValues.add(biblioteca)
        }
        if(position == 3){
            biblioteca = "Fac. Geología y Petróleos"
            listValues.add(biblioteca)
        }
        if(position == 4){
            biblioteca = "Fac. Eléctrica y Electrónica"
            listValues.add(biblioteca)
        }
        if(position == 5){
            biblioteca = "Fac. de Sistemas"
            listValues.add(biblioteca)
        }


        val bibliotecasIntent = Intent(this, SelectDateActivity::class.java)
        bibliotecasIntent.putExtra("listaValores",listValues)
        startActivity(bibliotecasIntent)
        finish()
        //Toast.makeText(applicationContext,listValues.toString(),Toast.LENGTH_SHORT).show()
    }

    fun imageButonRegresarTopicos(view: View){

        val regresarTopicos = Intent(this,Topicos::class.java)
        startActivity(regresarTopicos)
        finish()
    }

}
