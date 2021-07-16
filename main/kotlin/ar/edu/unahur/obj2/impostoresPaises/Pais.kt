package ar.edu.unahur.obj2.impostoresPaises

class Pais (
    val nombre: String,
    val codigoIso3: String,
    val poblacion: Long,
    val superficie: Double,
    val continente: String,
    var paisesLimitrofes: List<Pais>,
    val bloquesRegionales: List<String>,
    val idiomasOficiales: List<String> ) {

    fun esPlurinacional() = idiomasOficiales.size > 2

    fun esUnaIsla() = paisesLimitrofes.isEmpty()

    fun densidadPoblacional(): Double {
        if (superficie != 0.0) return poblacion/superficie else return 0.0
    }

    fun vecinoMasPoblado() : Pais {
        if (paisesLimitrofes.isEmpty())
            return this
        else
            return paisesLimitrofes.plus(this).maxByOrNull { it.poblacion }!!
    }

    fun sonLimitrofes(pais:Pais) = this.paisesLimitrofes.any { it.codigoIso3 == pais.codigoIso3 }

    fun necesitanTraduccion(pais:Pais) = idiomasOficiales.intersect(pais.idiomasOficiales).isEmpty()

    private fun compartenBloqueRegional(pais: Pais) = bloquesRegionales.intersect(pais.bloquesRegionales).isEmpty()
    fun sonPotencialesAliados(pais: Pais) = !this.necesitanTraduccion(pais) && !compartenBloqueRegional(pais)
}