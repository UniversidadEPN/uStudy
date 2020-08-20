package com.epn.proyectomoviles

import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore


class registroEstudiante : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var apel: EditText
    lateinit var email: EditText
    lateinit var domicilio: EditText
    private lateinit var db : FirebaseFirestore
    private lateinit var DatosEstudiante : ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_estudiante)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        DatosEstudiante = ArrayList<String>(15)

        db = FirebaseFirestore.getInstance()
        name = findViewById(R.id.editName)
        apel = findViewById(R.id.editApel)
        email = findViewById(R.id.editEmail)
        domicilio = findViewById(R.id.editDomicilio)

    }

    fun siguiente(view: View){
        val progressDialog = ProgressDialog(this)

        if(name.text.toString().isEmpty()){
            name.setError("Ingrese nombre")
        }
        if(apel.text.toString().isEmpty()){

            apel.setError("Ingrese apellido")
        }
        if(email.text.toString().isEmpty()){

            email.setError("Ingrese correo")

        }
        if(domicilio.text.toString().isEmpty()){

            domicilio.setError("Ingrese domicilio")
        }

        else{

            if(!validarEmail(email.text.toString())){
                email.setError("correo invalido")
                progressDialog.dismiss()
            }else{
                checkAndAddEstudiante()
            }

        }

    }

    private fun checkAndAddEstudiante() {


        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Espere ..")
        progressDialog.show()

        db.collection("registroEstudiante").whereEqualTo("email",email.text.toString()).get()
            .addOnSuccessListener { documentReference ->

                if(documentReference.size() > 0){
                    email.setError("email no esta disponible")
                    progressDialog.dismiss()
                }else{

                    DatosEstudiante.add(name.text.toString())
                    DatosEstudiante.add(apel.text.toString())
                    DatosEstudiante.add(email.text.toString())
                    DatosEstudiante.add(domicilio.text.toString())
                    DatosEstudiante.add("0")


                    val intent  = Intent(this, registro_est_celular::class.java).putExtra("DatosEstudiante",DatosEstudiante)
                    startActivity(intent)
                    finish()
                    progressDialog.dismiss()

                    name.setText("")
                    apel.setText("")
                    email.setText("")
                    domicilio.setText("")

                }

            }
    }

    private fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}
