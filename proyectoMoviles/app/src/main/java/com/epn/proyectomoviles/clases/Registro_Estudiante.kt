package com.epn.proyectomoviles.clases


class Registro_Estudiante {

    var nombre : String = ""
    var apellido : String = ""
    var email : String = ""
    var domicilio : String = ""
    var numeroTelef : String = ""

    constructor(nombre:String, apellido:String,email:String, domicilio:String,numero:String){
        this.nombre = nombre
        this.apellido = apellido
        this.email = email
        this.domicilio = domicilio
        this.numeroTelef = numero

    }

}