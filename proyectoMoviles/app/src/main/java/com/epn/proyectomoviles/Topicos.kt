package com.epn.proyectomoviles

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class Topicos : AppCompatActivity() {
    private lateinit var materiaText : TextView
    private lateinit var listValues : ArrayList<String>
    private var mensaje = ""
    private lateinit var objecto :Intent
    private var nameUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topicos)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        listValues = ArrayList<String>(15)

        var topic1: RadioButton = findViewById(R.id.topic1)
        var topic2: RadioButton = findViewById(R.id.topic2)
        var topic3: RadioButton = findViewById(R.id.topic3)
        var topic4: RadioButton = findViewById(R.id.topic4)
        var topic5: RadioButton = findViewById(R.id.topic5)
        val otroTopico : EditText = findViewById(R.id.otroTopicEdit)

        materiaText = findViewById(R.id.idMateria)

        objecto = intent
        mensaje = objecto.getStringExtra("materia")
        materiaText.text = mensaje



        if (mensaje =="Fundamentos de Matemáticas"){
            topic1.setText(R.string.topicMate1)
            topic2.setText(R.string.topicMate2)
            topic3.setText(R.string.topicMate3)
            topic4.setText(R.string.topicMate4)
            topic5.setText(R.string.topicMate5)


        }
        if (mensaje == "Fundamentos de Geometría"){

            topic1.setText(R.string.topicGeo1)
            topic2.setText(R.string.topicGeo2)
            topic3.setText(R.string.topicGeo3)
            topic4.setText(R.string.topicGeo4)
            topic5.setText(R.string.topicGeo5)

        }
        if (mensaje == "Fundamentos de Química"){

            topic1.setText(R.string.topicQuim1)
            topic2.setText(R.string.topicQuim2)
            topic3.setText(R.string.topicQuim3)
            topic4.setText(R.string.topicQuim4)
            topic5.setText(R.string.topicQuim5)

        }

        var buton : Button = findViewById(R.id.buttonAceptar)

        buton.setOnClickListener {

            listValues.clear()

            nameUser = objecto.getStringExtra("nombreUser")
            listValues.add(nameUser)
            listValues.add(mensaje)

            if(topic1.isChecked){
                listValues.add(topic1.text.toString())
            }
            if(topic2.isChecked){
                listValues.add(topic2.text.toString())
            }
            if(topic3.isChecked){
                listValues.add(topic3.text.toString())
            }
            if(topic4.isChecked){
                listValues.add(topic4.text.toString())
            }
            if(topic5.isChecked){
                listValues.add(topic5.text.toString())
            }
            if(otroTopico.text.toString().isNotEmpty()){
                listValues.add(otroTopico.text.toString())
            }


            val irBibliotecas = Intent(this,bibliotecas::class.java).putExtra("valores",listValues)
            startActivity(irBibliotecas)

        }


        var ButonCancelar : Button = findViewById(R.id.butonCancelar)

        ButonCancelar.setOnClickListener {

            if(topic1.isChecked){
                topic1.isChecked = false
            }
            if(topic2.isChecked){
                topic2.isChecked = false
            }
            if(topic3.isChecked){
                topic3.isChecked = false
            }
            if(topic4.isChecked){
                topic4.isChecked = false
            }
            if(topic5.isChecked){
                topic5.isChecked = false
            }
            if(otroTopico.text.toString().isNotEmpty()){
                otroTopico.setText("")
            }

        }

    }

    fun imageButonRegresar(view: View){

        val regresarAMaterias = Intent(this,activity_navegation::class.java)
        startActivity(regresarAMaterias)
    }

}
