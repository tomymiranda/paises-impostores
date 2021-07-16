package ar.edu.unahur.obj2.impostoresPaises

class Observatorio {

    fun sonLimitrofes(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = Adaptador.buscarPaisPorNombre(nombrePais1)
        val pais2 = Adaptador.buscarPaisPorNombre(nombrePais2)
        return pais1.sonLimitrofes(pais2)
    }

    fun necesitanTraduccion(nombrePais1: String,nombrePais2: String): Boolean {
        val pais1 = Adaptador.buscarPaisPorNombre(nombrePais1)
        val pais2 = Adaptador.buscarPaisPorNombre(nombrePais2)
        return pais1.necesitanTraduccion(pais2)
    }

    fun sonPotencialesAliados(nombrePais1: String,nombrePais2: String): Boolean{
        val pais1 = Adaptador.buscarPaisPorNombre(nombrePais1)
        val pais2 = Adaptador.buscarPaisPorNombre(nombrePais2)
        return pais1.sonPotencialesAliados(pais2)
    }

    private fun paisesPorDensidadPoblacional() = Adaptador.todosLosCountryComoPaises().sortedByDescending { it.densidadPoblacional() }
    fun iso3DeLos5PaisesConMayorDensidadPoblacional() = this.paisesPorDensidadPoblacional().subList(0,5).map { it.codigoIso3 }

    private fun paisesAgrupadosPorContinente() = Adaptador.todosLosCountryComoPaises().groupBy { it.continente }
    private fun cantidadPaisesPlurinacionales(continente: List<Pais>) = continente.count { it.esPlurinacional() }
    fun continenteConMasPaisesPlurinacionales() = paisesAgrupadosPorContinente().maxByOrNull { cantidadPaisesPlurinacionales( it.value ) }!!.key

    private fun paisesIsla() = Adaptador.todosLosCountryComoPaises().filter{ it.esUnaIsla() }
    fun promedioDensidadPoblacionalPaisesIsla() = this.paisesIsla().sumByDouble { it.densidadPoblacional() } / this.paisesIsla().size
}



