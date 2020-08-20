package com.epn.proyectomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_detalle_servicio.*



class DetalleServicio : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var listViewItems: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    internal lateinit var db: FirebaseFirestore

    internal lateinit var fecha:String
    internal lateinit var pago:String
    internal lateinit var profesor:String
    internal  var valor:Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_servicio)


        db = FirebaseFirestore.getInstance()







        listView = findViewById<ListView>(R.id.listaAyuda)
        listViewItems = ArrayList<String>(10)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewItems)
        listView.adapter = adapter
        adapter.add(getString(R.string.servicio_detalle_ayuda1))
        adapter.add(getString(R.string.servicio_detalle_ayuda2))
        adapter.add(getString(R.string.servicio_detaller_ayuda3))
        adapter.add(getString(R.string.servicio_detalle_ayuda4))
        adapter.add(getString(R.string.servicio_detalle_ayuda5))



        adapter.notifyDataSetChanged()



       // llenarInformacion()


    }

    fun llenarInformacion(view: View){
       fecha ="hola"
        var aux: Int
      db.collection("registroDetalleServicio").
          get().
          addOnCompleteListener {
              for(documentos in it.result!!){

                  fecha ="hola22"
                   fecha= documentos.getString("nombre").toString()
                  val pago= documentos.getString("formaPago")
                  val profesor= documentos.getString("profesor")
                   valor = documentos.get("valor") as Long

              }



          }


        textViewDetalleSerFecha.setText(fecha)
        textViewDetalleSerFormaPago.setText(pago)
        textViewDetalleSerProfesor.setText(profesor)
        textViewDetalleSerValor.setText(valor.toString())



    }

    fun onClickAtras(view: View){
        var atras = Intent(this, activity_navegation::class.java)
        startActivity(atras)
    }


}
