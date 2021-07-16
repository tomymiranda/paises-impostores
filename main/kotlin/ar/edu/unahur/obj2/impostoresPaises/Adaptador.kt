package ar.edu.unahur.obj2.impostoresPaises

object Adaptador{

    var api = RestCountriesAPI()

    fun buscarPaisesPorNombre(nombre: String): List<Pais> {
        val countries = api.buscarPaisesPorNombre(nombre)
        return countries.map { transformarAPais(it) }
    }

    fun buscarPaisPorNombre(nombre: String) = buscarPaisesPorNombre(nombre).first()

    fun buscarPaisPorISO(codigo: String) : Pais {
        val country = api.paisConCodigo(codigo)
        return transformarAPais(country)
    }

    fun transformarAPais(country: Country) : Pais {
        return Pais(
            country.name,
            country.alpha3Code,
            country.population,
            country.area ?: 0.0,
            country.region,
            this.generarLimitrofes(country.borders),
            country.regionalBlocs.map { it.name }.toList(),
            country.languages.map { it.name }.toList()
        )
    }

    private fun generarLimitrofes(limitrofes: List<String>) : List<Pais> {
        val countriesEncontrados = limitrofes.map { api.paisConCodigo(it) }
        return countriesEncontrados.map { crearLimitrofe(it) }
    }

    private fun crearLimitrofe(country: Country) : Pais {
        return Pais(
            country.name,
            country.alpha3Code,
            country.population,
            country.area ?: 0.0,
            country.region,
            listOf<Pais>(),
            country.regionalBlocs.map { it.name }.toList(),
            country.languages.map { it.name }.toList()
        )
    }

    fun todosLosCountryComoPaises() : List<Pais> {
        /*  Esta función, a diferencia de las anteriores, aprovecha la carga de todos los países
            para realizar la lista de países limítrofes con los mismos objetos creados previamente
            para cada país. */
        val todosLosPaises = api.todosLosPaises()
        val paisesCreados = todosLosPaises.map { this.crearPais(it) }

        paisesCreados.forEach { pais ->
            val limitrofes = mutableListOf<Pais>()
            todosLosPaises.find { country -> country.name == pais.nombre }!!
                .borders.forEach { codigoPais -> limitrofes.add(paisesCreados.find { pais -> pais.codigoIso3 ==  codigoPais }!!) }
            pais.paisesLimitrofes = limitrofes
        }
        return paisesCreados
    }

    private fun crearPais(country: Country) : Pais {
        return Pais(
            country.name,
            country.alpha3Code,
            country.population,
            country.area ?: 0.0,
            country.region,
            listOf<Pais>(),
            country.regionalBlocs.map { it.name }.toMutableList(),
            country.languages.map { it.name }.toMutableList()
        )
    }
}

