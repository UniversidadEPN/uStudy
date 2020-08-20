package com.epn.proyectomoviles

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.epn.proyectomoviles.clases.Registro_Estudiante
import java.io.Serializable


class registro_est_celular : AppCompatActivity() {
    private lateinit var numberPhone : EditText
    private lateinit var DatosEstudiante :  ArrayList<String>
    private lateinit var connectivityManager :ConnectivityManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_est_celular)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        numberPhone = findViewById(R.id.editPhone)
        DatosEstudiante = ArrayList<String>(15)

        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    }

    fun siguiente(view: View){

        val networkInfo = connectivityManager.activeNetworkInfo

        var objecto: Intent =intent
        DatosEstudiante = objecto.getStringArrayListExtra("DatosEstudiante")



        if(numberPhone.text.toString().isEmpty()){

            numberPhone.setError("ingrese numero celular")

        }else{




            if(networkInfo != null && networkInfo.isConnected){

                if(!validarTelefono(numberPhone.text.toString())){
                    numberPhone.setError("Este número de teléfono no es válido")

                }else{
                    var intent = Intent(this, registro_est_verif::class.java)
                    intent.putExtra("DatosEstudiante",DatosEstudiante)
                    intent.putExtra("tel",numberPhone.text.toString())
                    startActivity(intent)
                }

            }

            else{
                var dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Verifica tu conexión a internet y vuelve a intentarlo")
                dialogAlert.show()
            }

        }
    }

    private fun validarTelefono(telefono: String): Boolean {
        val pattern = Patterns.PHONE
        return pattern.matcher(telefono).matches()
    }


}

