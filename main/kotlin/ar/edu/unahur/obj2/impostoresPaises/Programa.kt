package ar.edu.unahur.obj2.impostoresPaises

// Acá encapsulamos el manejo de la consola real, desacoplandolo del programa en sí
object Consola {
  fun leerLinea() = readLine()
  fun escribirLinea(contenido: String) {
    println(contenido)
  }
}

// El código de nuestro programa, que (eventualmente) interactuará con una persona real
object Programa {
  // Ambas son variables para poder cambiarlas en los tests
  var entradaSalida = Consola
  var api = RestCountriesAPI()

  fun iniciar() {
    entradaSalida.escribirLinea("Hola, poné el nombre de un país y te mostramos algo de data")
    val pais = entradaSalida.leerLinea()

    checkNotNull(pais) { "Sin nombre no puedo hacer nada :(" }

    val paisesEncontrados = api.buscarPaisesPorNombre(pais)

    check(paisesEncontrados.isNotEmpty())
      { "No encontramos nada, fijate si lo escribiste bien" }

    paisesEncontrados.forEach {
      entradaSalida.escribirLinea(
        "${it.name} (${it.alpha3Code}) es un país de ${it.region}, con una población de ${it.population} habitantes."
      )
    }
  }
}
