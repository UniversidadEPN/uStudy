package com.epn.proyectomoviles



import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_select_date.*
import java.text.SimpleDateFormat
import java.util.*


class SelectDateActivity : AppCompatActivity() {

    private lateinit var date: String
    private lateinit var startTime: String
    private lateinit var endTime: String
    private lateinit var listDetail : ArrayList<String> //list for detail recived
    private lateinit var listBack : ArrayList<String>

    private lateinit var acceptButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        listDetail = ArrayList<String>(15)
        listBack = ArrayList<String>(15)

        var objectRecived : Intent = intent
        listDetail = objectRecived.getStringArrayListExtra("listaValores")
        listBack = listDetail


        //Para mostrar detalle

        acceptButton = findViewById(R.id.buttonAcceptDate)

        val builder = AlertDialog.Builder(this)

        acceptButton.setOnClickListener {


            if(textViewDate.text != "Fecha" && textViewEndTime.text != "Hora de fin" && textViewStartTime.text != "Hora de inicio"){
                //Toast.makeText(applicationContext, "Pasa a detalle",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DetailActivity::class.java)
                listDetail.add(date) //add date to list
                listDetail.add(startTime) //add start time to list
                listDetail.add(endTime) //add end time to list

                intent.putExtra("Detalle",listDetail)
                startActivity(intent)
            }else{
                builder.setTitle("ALERTA!!!")
                builder.setMessage("Debe seleccionar la fecha y la hora de la consulta")
                builder.setNegativeButton("OK",{dialogInterface: DialogInterface , i: Int -> })
                builder.show()
            }

        }
    }

    //funciones para recolectar la hora y el dia
    fun onClicDate(v: View){

        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ datePicker, yearC, monthOfYear, dayOfMonth ->
            this.date = formatDate(yearC, monthOfYear, dayOfMonth)
            textViewDate.text = date
        }, year,month,day)

        dpd.show()
    }

    fun onClicStartTime(v: View){

        val c = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
            c.set(Calendar.HOUR_OF_DAY, hour)
            c.set(Calendar.MINUTE, minute)
            this.startTime = SimpleDateFormat("HH:mm").format(c.time)
            textViewStartTime.text = startTime
        }
        TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),false).show()
    }

    fun onClicEndTime(v: View){

        val c = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
            c.set(Calendar.HOUR_OF_DAY, hour)
            c.set(Calendar.MINUTE, minute)
            this.endTime = SimpleDateFormat("HH:mm").format(c.time)
            textViewEndTime.text = endTime
        }
        TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),false).show()
    }

    fun formatDate(year: Int, monthOfYear: Int, dayOfMonth: Int): String{

        lateinit var month: String
        lateinit var day: String
        val monthInt = monthOfYear + 1
        val builderMonth = StringBuilder()
        val builderDay = StringBuilder()
        if(monthInt <10){
            builderMonth.append("0")
        }
        builderMonth.append(monthInt)
        month = builderMonth.toString()

        if(dayOfMonth <10){
            builderDay.append("0")
        }
        builderDay.append(dayOfMonth)
        day = builderDay.toString()

        val format = "$day / $month / $year"

        return format
    }

    fun imageButtonBack(view: View){

        val regresarbibliotecas = Intent(this, bibliotecas::class.java)
        regresarbibliotecas.putExtra("valores",listBack)
        startActivity(regresarbibliotecas)
        finish()
    }

}
