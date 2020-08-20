package com.epn.proyectomoviles

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.nav_header.*


class activity_navegation : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  , AdapterView.OnItemClickListener{
    //Implementacion de Facebook
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    private lateinit var listView: ListView
    private lateinit var imagen_Adapter2 : ImageAdapter2
    private  var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegation)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        listView() //habilitar
        obtenerDatosFirebase()
        //  Buenas tardes
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_tutoles -> {

                Toast.makeText(this, "Tus Tutores", Toast.LENGTH_SHORT).show()
                var detalleSer : Intent = Intent(this, DetalleServicio::class.java)
                startActivity(detalleSer)

            }
            R.id.nav_servicios -> {
                Toast.makeText(this, "Tus Servicios", Toast.LENGTH_SHORT).show()
                var servi : Intent = Intent(this, ServiciosRealizados::class.java)
                startActivity(servi)
            }
            R.id.nav_configuracion -> {
                Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show()
            }


        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun listView(){

        listView = findViewById(R.id.idListaBib)
        listView.setOnItemClickListener(this)
        imagen_Adapter2  = ImageAdapter2(this)
        listView.adapter = imagen_Adapter2
        imagen_Adapter2.notifyDataSetChanged()

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        var materia  = ""

        if(position == 0){
            materia = "Fundamentos de Matemáticas"
        }
        if(position == 1){
            materia = "Fundamentos de Física"
        }
        if(position == 2){
            materia = "Fundamentos de Geometría"
        }
        if(position == 3){
            materia = "Fundamentos de Química"
        }

        Toast.makeText(applicationContext, "Seleccionó: $materia",Toast.LENGTH_SHORT).show()
        val intentTopicos = Intent(this, Topicos::class.java)
        intentTopicos.putExtra("materia",materia)
        intentTopicos.putExtra("nombreUser",nameUser.text.toString())
        startActivity(intentTopicos)

    }

    private fun obtenerDatosFirebase(){

        var navView : NavigationView = findViewById(R.id.nav_view)
        var headerView : View = navView.getHeaderView(0)
        var nameUser : TextView = headerView.findViewById(R.id.nameUser)


        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val idEst = sharedPref.getString("id", "")


        //var objeto: Intent = intent
        //var idEst = objeto.getStringExtra("id")

        db.collection("registroEstudiante").document(idEst.toString()).get().addOnSuccessListener {document ->

            if(document.exists()){

                nameUser.text = document.getString("nombre") +" "+ document.getString("apellido")

            }else{
                Toast.makeText(applicationContext, "Doc no existe",Toast.LENGTH_SHORT).show()
            }

        }

    }
}
