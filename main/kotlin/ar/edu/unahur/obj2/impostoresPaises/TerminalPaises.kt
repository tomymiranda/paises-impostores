package ar.edu.unahur.obj2.impostoresPaises

object TerminalPaises {

    var entradaSalida = Consola
    var observatorio = Observatorio()

    private val opciones = listOf(
        "salir",
        "si dos países son limítrofes",
        "si dos países necesitan traductor",
        "si dos países son potenciales aliados",
        "los códigos ISO de los 5 países con mayor densidad poblacional",
        "el nombre del continente con más países plurinacionales",
        "el promedio de densidad poblacional de países isla"
    )
    private val busquedas = listOf(
        "por nombre",
        "por ISO"
    )

    fun ejecutar() {
        val opcion = this.menu()
        when (opcion) {
            0 -> return
            1 -> this.limitrofes()
            2 -> this.traduccion()
            3 -> this.aliados()
            4 -> this.cincoIso()
            5 -> this.continentePlurinacional()
            6 -> this.densidad()
            else -> {
                entradaSalida.escribirLinea("Error!! Aniquilaré a la humanidad... ")
                Thread.sleep(1500)
                entradaSalida.escribirLinea("... pensándolo bien, te daré una nueva oportunidad.\n")
                Thread.sleep(1500)
                this.ejecutar()
            }
        }
    }

    private fun menu() : Int? {
        entradaSalida.escribirLinea("Hola humano, selecciona una de las siguientes opciones:\n")
        opciones.forEach {
            entradaSalida.escribirLinea("[${opciones.indexOf(it)}] para ${if(opciones.indexOf(it)!=0) "saber " else ""}${it}")
        }
        entradaSalida.escribirLinea("\nIngresa una opción correcta o aniquilaré a la humanidad como Skynet")
        return entradaSalida.leerLinea()!!.toIntOrNull()
    }

    private fun menuBusqueda() : Int? {
        entradaSalida.escribirLinea("Querés realizar la búsqueda:")
        busquedas.forEach {
            entradaSalida.escribirLinea("[${busquedas.indexOf(it)}] ${it}.")
        }
        entradaSalida.escribirLinea("¿Cuál preferís?")
        return entradaSalida.leerLinea()!!.toIntOrNull()
    }

    private fun consultarPais() : Pais {
        val opcion = menuBusqueda()
        return when(opcion) {
            0 -> {
                entradaSalida.escribirLinea("Ingresá el nombre:")
                try {
                    Adaptador.buscarPaisPorNombre(entradaSalida.leerLinea()!!)
                } catch (e: NoSuchElementException) {
                    println("Error en la búsqueda: no existe ese nombre. Volvé a intentar!")
                    this.consultarPais()
                }
            }
            1 -> {
                entradaSalida.escribirLinea("Ingresá el ISO:")
                try {
                    Adaptador.buscarPaisPorISO(entradaSalida.leerLinea()!!)
                } catch (e: IllegalStateException) {
                    println("Error en la búsqueda: no existe ese código. Volvé a intentar!")
                    this.consultarPais()
                }
            }
            else -> {
                entradaSalida.escribirLinea("Te daré una nueva oportunidad...")
                this.consultarPais()
            }
        }
    }

    private fun consultarDosPaises() : List<Pais> {
        entradaSalida.escribirLinea("Vamos por el primer país.")
        val pais1 = this.consultarPais()
        entradaSalida.escribirLinea("Vamos por el segundo país.")
        val pais2 = this.consultarPais()
        return listOf(pais1, pais2)
    }

    private fun limitrofes() {
        val (pais1, pais2) = this.consultarDosPaises()
        val sonLimitrofes = if (observatorio.sonLimitrofes(pais1.nombre,pais2.nombre)) "" else "no "
        entradaSalida.escribirLinea("${pais1.nombre} y ${pais2.nombre} ${sonLimitrofes}son limítrofes\n")
    }

    private fun traduccion() {
        val (pais1, pais2) = this.consultarDosPaises()
        val necesitanTraductor = if (observatorio.necesitanTraduccion(pais1.nombre, pais2.nombre)) "" else "no "
        entradaSalida.escribirLinea("${pais1.nombre} y ${pais2.nombre} ${necesitanTraductor}necesitan traductor\n")
    }

    private fun aliados() {
        val (pais1, pais2) = this.consultarDosPaises()
        val posiblesAliados = if (observatorio.sonPotencialesAliados(pais1.nombre, pais2.nombre)) "" else "no "
        entradaSalida.escribirLinea("${pais1.nombre} y ${pais2.nombre} ${posiblesAliados}son potenciales aliados\n")
    }

    private fun cincoIso() {
        entradaSalida.escribirLinea(
            "Los códigos ISO3 de los países con mayor densidad poblacional, de mayor a menor, son:")
        observatorio.iso3DeLos5PaisesConMayorDensidadPoblacional().forEach { entradaSalida.escribirLinea(it) }
    }

    private fun continentePlurinacional() {
        entradaSalida.escribirLinea(
            "El continente con más países plurinacionales es ${observatorio.continenteConMasPaisesPlurinacionales()}\n")
    }

    private fun densidad() {
        entradaSalida.escribirLinea(
            "El promedio de densidad poblacional de los países isla es ${observatorio.promedioDensidadPoblacionalPaisesIsla()}\n"
        )
    }
}
