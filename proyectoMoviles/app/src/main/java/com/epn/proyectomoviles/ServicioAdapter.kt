package com.epn.proyectomoviles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.elemento_registro.view.*
import kotlinx.android.synthetic.main.registro_est_celular.view.*

class ServicioAdapter(private val mContext: Context, private val listaServicios: List<Servicio>): ArrayAdapter<Servicio>(mContext, 0, listaServicios) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.elemento_registro, parent, false)

        val servicio= listaServicios[position]
        layout.textViewFechaServ.text= servicio.fecha
        layout.textViewNomSer.text= servicio.nomProf
        layout.textViewValServ.text= servicio.valor.toString()
        layout.imageViewSer.setImageResource(servicio.imagen)

        return layout
    }
}