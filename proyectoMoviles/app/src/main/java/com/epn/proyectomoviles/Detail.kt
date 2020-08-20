package com.epn.proyectomoviles

class Detail {

    var id: String? = null
    var fecha: String? = null
    var formaPago: String? = null
    var horario: String? = null
    var lugar: String? = null
    var profesor: String? = null
    var estudiante: String? = null
    var valor: Double? = null
    var calificacionProf: Int? = null
    var materia: String? = null
    var topico: String? = null

    constructor(id:String, fecha: String, formaPago: String, horario: String, lugar: String,
                profesor: String, estudiante: String, valor: Double, calificacionProf: Int, materia: String, topico: String){
        this.id = id
        this.fecha = fecha
        this.formaPago = formaPago
        this.horario = horario
        this.lugar = lugar
        this.profesor = profesor
        this.estudiante = estudiante
        this.valor = valor
        this.calificacionProf = calificacionProf
        this.materia = materia
        this.topico = topico
    }
}