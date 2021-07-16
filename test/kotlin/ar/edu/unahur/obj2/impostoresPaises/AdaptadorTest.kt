package ar.edu.unahur.obj2.impostoresPaises
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf


class AdaptadorTest : DescribeSpec({
    val api = RestCountriesAPI()

    describe("Tests para el Adaptador"){
        describe("Pasaje de tipo Country a Pais "){
            val country = api.buscarPaisesPorNombre("Argentina").first()
            it("Se crea un país con los datos de un country"){
                val arg = Adaptador.transformarAPais(country)
                arg.nombre.shouldBe("Argentina")
                arg.codigoIso3.shouldBe("ARG")
            }
            it("La lista de tipo country la devuelve como lista de países"){
                val listaDeCountryTransformadosAPaises = Adaptador.todosLosCountryComoPaises()
                listaDeCountryTransformadosAPaises.shouldBeInstanceOf<List<Pais>>()
            }
        }

        describe("Buscar un país"){
            it("Busca un país por nombre"){
                val arg = Adaptador.buscarPaisPorNombre("Argentina")
                arg.shouldBeInstanceOf<Pais>()
            }
            it("Se busca un país por ISO3"){
                val bra = Adaptador.buscarPaisPorISO("BRA")
                bra.shouldBeInstanceOf<Pais>()
            }
        }
    }
})

