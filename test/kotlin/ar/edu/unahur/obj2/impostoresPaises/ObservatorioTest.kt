package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ObservatorioTest: DescribeSpec({
    val observatorio = Observatorio()

    val apiMock = mockk<RestCountriesAPI>()
    // Descomentar línea siguiente para testear con MockK
    //Adaptador.api = apiMock


    //Countries
    val espaniol = Language("Español")
    val japones = Language("Japones")
    val chino = Language("Chino")

    val mercosur = RegionalBloc("MERCOSUR","mercado comun del sur")
    val rcep = RegionalBloc("RCEP","Asociación Económica Integral Regional")

    val listaLenguajesCountry = listOf(espaniol)
    val listaLenguajeJaponCountry = listOf(japones)
    val listaLenguajeQueHablanChino = listOf(chino)
    val listaMulitLenguaje = listOf(espaniol,japones,chino)

    val listaBloqueRegionalCountry = listOf(mercosur)
    val listaBloqueRegionalJaponCountry = listOf(rcep)
    val listaBloqueRegionalVacio = listOf<RegionalBloc>()

    val limitrofesCountryVacio = listOf<String>()

    val limitrofesCountryDeArg = listOf("URU","CHI","BRA","PAR","BOL")
    val arg = Country("Argentina", "ARG", "Buenos Aires","América",44938712, 278000000.0,limitrofesCountryDeArg, listaLenguajesCountry,listaBloqueRegionalCountry )
    val listQueTieneArg = listOf(arg)

    val limitrofesCountryDeChi = listOf("ARG","PER","BOL")
    val chi = Country("Chile", "CHI", "Santiago","América",18952038, 756950.0,limitrofesCountryDeChi, listaLenguajesCountry,listaBloqueRegionalCountry )
    val listQueTieneChi = listOf(chi)

    val limitrofesCountryDeUru = listOf("ARG","BRA","PAR")
    val uru = Country("Uruguay", "URU", "Montevideo","América",3461734, 176215.0,limitrofesCountryDeUru, listaLenguajesCountry,listaBloqueRegionalCountry )
    val listQueTieneUru = listOf(uru)

    val limitrofesCountryDeBra = listOf("ARG","URU","PAR","BOL","COL","PER","VEN")
    val bra = Country("Brasil", "BRA", "Brasilia","América",211000000, 8516000000.0,limitrofesCountryDeBra, listaLenguajesCountry,listaBloqueRegionalCountry )

    val limitrofesCountryDePar = listOf("ARG","BRA","BOL")
    val par = Country("Paraguay", "PAR", "Asunsion","América",1234567, 7777777.0,limitrofesCountryDePar, listaLenguajesCountry,listaBloqueRegionalCountry )

    val limitrofesCountryDeBol = listOf("ARG","BRA","PAR","PER","CHI")
    val bol = Country("Bolivia", "BOL", "La Paz","América",10985059, 1098581.0,limitrofesCountryDeBol, listaLenguajesCountry,listaBloqueRegionalCountry )

    val limitrofesCountryDePer = listOf("ECU","COL","BOL","CHI")
    val per = Country("Peru","PER","Lima","América",3251000000,1285000000.0,limitrofesCountryDePer, listaLenguajesCountry,listaBloqueRegionalCountry)

    val jap = Country("Japan","JAP","tokio","Asia",987654321, 123456.0,limitrofesCountryVacio,listaLenguajeJaponCountry,listaBloqueRegionalJaponCountry)
    val listaQueTieneAJap = listOf(jap)

    describe("observatorio test"){

            every { apiMock.buscarPaisesPorNombre("Argentina") } returns listQueTieneArg
            every { apiMock.paisConCodigo("URU") } returns uru
            every { apiMock.paisConCodigo("CHI") } returns chi
            every { apiMock.paisConCodigo("BRA") } returns bra
            every { apiMock.paisConCodigo("PAR") } returns par
            every { apiMock.paisConCodigo("BOL") } returns bol

            every { apiMock.buscarPaisesPorNombre("Chile") } returns listQueTieneChi
            every { apiMock.paisConCodigo("ARG") } returns arg
            every { apiMock.paisConCodigo("PER") } returns per
            every { apiMock.buscarPaisesPorNombre("Uruguay") } returns listQueTieneUru

            every { apiMock.buscarPaisesPorNombre("Japan")}returns listaQueTieneAJap

            describe("Requerimiento 1: sonLimitrofes"){
                it("Dice que Argentina y Chile son limítrofes"){

                    observatorio.sonLimitrofes("Argentina","Chile").shouldBeTrue()
                    observatorio.sonLimitrofes("Chile","Argentina").shouldBeTrue()
                }
                it("Dice que Chile y Uruguay no son limítrofes"){
                    observatorio.sonLimitrofes("Chile","Uruguay").shouldBeFalse()
                }
            }

            describe("Requerimiento 2: necesitanTraduccion"){
                it("Dice que Argentina y Uruguay no necesitan traductor"){
                    observatorio.necesitanTraduccion("Argentina","Uruguay").shouldBeFalse()
                }
                it("Dice que Argentina y Japón necesitan un traductor"){
                    observatorio.necesitanTraduccion("Argentina","Japan").shouldBeTrue()
                }
            }

            describe("Requerimiento 3: sonPotencialesAliados"){
                it("Dice que Argentina y Uruguay son posibles aliados"){
                    observatorio.sonPotencialesAliados("Argentina","Uruguay").shouldBeTrue()
                }
                it("Dice que Uruguay y Japón no son posibles aliados"){
                    observatorio.sonPotencialesAliados("Uruguay","Japan").shouldBeFalse()
                }
            }

            describe("Requerimiento 4: iso3DeLos5PaisesConMayorDensidadPoblacional"){

                val mac = Country("Macao","MAC", "","Asia", 649100, 30.0, listOf(), listaLenguajeQueHablanChino, listaBloqueRegionalJaponCountry)
                val mco = Country("Monaco","MCO","Monaco","Europe",38400,2.02,listOf(),listaMulitLenguaje,listaBloqueRegionalJaponCountry)
                val sgp = Country("Singapore","SGP","Singapore","Asia",5535000,710.0,listOf(),listaLenguajeQueHablanChino,listaBloqueRegionalJaponCountry)
                val hkg = Country("Hong Kong","HKG","City of Victoria","Asia",7324300,1104.0,listOf(),listaLenguajeQueHablanChino,listaBloqueRegionalJaponCountry)
                val gib = Country("Gibraltar","GIB","Gibraltar","Europe",33140,6.0,listOf(),listaMulitLenguaje,listaBloqueRegionalJaponCountry)

                val countryParaRequerimiendo4 = listOf(mac,mco,sgp,hkg,gib)
                it("El orden de los códigos de los paises con mas mayor densidad poblacional es : MAC,GUF,SGP,HKG,GIB") {
                    val lista = listOf("MAC", "MCO", "SGP", "HKG", "GIB")
                    every { apiMock.todosLosPaises() } returns countryParaRequerimiendo4
                    observatorio.iso3DeLos5PaisesConMayorDensidadPoblacional().shouldBe(lista)
                }
            }

            describe("Requerimiento 5: continenteConMasPaisesPlurinacionales") {
                val suiza = Country("Suiza", "CHE", "Berlin", "Europe", 12, 12.1, listOf("FRA"), listaMulitLenguaje, listaBloqueRegionalVacio)
                val australia = Country("Australia", "AUS", "Caberra", "Oceania", 12, 12.1, listOf(), listaLenguajeJaponCountry, listaBloqueRegionalVacio)
                val francia = Country("Francia", "FRA", "Paris", "Europe", 12, 12.1, listOf("CHE"), listaMulitLenguaje, listaBloqueRegionalVacio)
                val countriesPlurinacionales = listOf(suiza, australia, francia)

                it("El continente con más países plurinacionales es Europa"){
                    every { apiMock.todosLosPaises() } returns countriesPlurinacionales
                    observatorio.continenteConMasPaisesPlurinacionales().shouldBe("Europe")
                }
            }

            describe("Requerimiento 6: promedioDensidadPoblacionalPaisesIsla") {
                val australia = Country("Australia", "AUS", "Caberra", "Oceania", 10000000, 31496.46598887757912842, listOf(), listaLenguajeJaponCountry, listaBloqueRegionalVacio)
                val japan = Country("Japan", "JPN", "Caberra", "Asia", 10000000, 31496.46598887757912842, listOf(), listaLenguajeJaponCountry, listaBloqueRegionalVacio)
                val islandCountries = listOf(australia, japan)

                it("El Promedio de densidad poblacional de los países-isla es 317.49593759285005") {
                    every { apiMock.todosLosPaises() } returns islandCountries
                    observatorio.promedioDensidadPoblacionalPaisesIsla().shouldBe(317.49593759285005)
                }
            }
    }
})