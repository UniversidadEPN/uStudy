package com.epn.proyectomoviles


import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private  var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun registroEstudianre(view: View){

        var intent = Intent(this, registroEstudiante::class.java)
        startActivity(intent)

    }

    override fun onStart() {
        super.onStart()

        val user = FirebaseAuth.getInstance().currentUser
         if(user !=null){

             //val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
             //val id = sharedPref.getString("id", "")
             var intentActivityNaveg = Intent(this, activity_navegation::class.java)
             //intentActivityNaveg.putExtra("id",id)
             startActivity(intentActivityNaveg)
             finish()


        }
    }
}
