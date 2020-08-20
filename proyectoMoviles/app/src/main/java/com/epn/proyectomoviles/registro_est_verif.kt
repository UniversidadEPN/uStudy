package com.epn.proyectomoviles


import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit
import android.app.ProgressDialog
import android.preference.PreferenceManager
import com.epn.proyectomoviles.clases.Registro_Estudiante


class registro_est_verif : AppCompatActivity() {

    private var verificador=""
    private  var auth = FirebaseAuth.getInstance()
    private lateinit var butonVerificar : Button
    private  var db = FirebaseFirestore.getInstance()
    private var phoneNumber : String = ""
    private lateinit var DatosEstudiante : ArrayList<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_est_verif)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        butonVerificar = findViewById(R.id.button3)
        DatosEstudiante = ArrayList<String>(15)

        var objecto:Intent = intent
        phoneNumber = "+593"+objecto.getStringExtra("tel")
        DatosEstudiante = objecto.getStringArrayListExtra("DatosEstudiante")


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            callbacks
        )


        butonVerificar.setOnClickListener {

            var codEditText : EditText = findViewById(R.id.cod)

            if(codEditText.text.toString().isEmpty()){

                codEditText.setError("Ingrese codigo")

            }else{

                var credential = PhoneAuthProvider.getCredential(verificador,codEditText.text.toString())
                signInWithPhoneAuthCredential(credential)

            }


        }

    }

    var callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){


        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            //se encargara de logear

            signInWithPhoneAuthCredential(credential)
            Toast.makeText(applicationContext, "Bienvenido", Toast.LENGTH_SHORT).show()

        }

        override fun onVerificationFailed(e: FirebaseException) {
            //funcion cuando falla el  numero de telefono

            if(e is FirebaseAuthInvalidCredentialsException){

                Toast.makeText(applicationContext, "Codigo inválido", Toast.LENGTH_SHORT).show()

            }else if(e is FirebaseTooManyRequestsException){

                Toast.makeText(applicationContext, "Esperó demasiado", Toast.LENGTH_SHORT).show()

            }

        }

        override fun onCodeSent(verificationID: String, token: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationID, token)
            verificador  = verificationID
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Registrando ..")
        progressDialog.show()

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) {task ->

               if(task.isSuccessful){
                   addStudentToFirestore()
                   progressDialog.dismiss()

               }else{

                   Toast.makeText(applicationContext, "falló código de verificacion", Toast.LENGTH_SHORT).show()
                   progressDialog.dismiss()

               }
            }
    }

    private fun addStudentToFirestore() {

        val estudiante = Registro_Estudiante(DatosEstudiante.get(0),DatosEstudiante.get(1),DatosEstudiante.get(2),DatosEstudiante.get(3),phoneNumber)

        db.collection("registroEstudiante")
            .add(estudiante)
            .addOnSuccessListener { documentReference ->

                //guardar id en el archivo de preferences
                val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                val editor = sharedPref.edit()
                editor.putString("id", documentReference.id)
                editor.apply()

                //ir al activity navegation cuando se registro
                var intent = Intent(this, activity_navegation::class.java)
                //intent.putExtra("id",documentReference.id)
                startActivity(intent)
                finish()

            }
    }
}
