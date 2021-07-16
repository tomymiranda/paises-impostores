package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PaisTest: DescribeSpec({

    // Listas usadas por los países
    val bloqueRegionalMercoSur = listOf("Mercosur")
    val idiomasDeRegionAmerica = listOf("español", "inglés", "portugués")
    val idiomasEnAsia = listOf("japonés")
    val bloqueRegionAsia = listOf("RCEP")

    // Países
    val chile = Pais("Chile", "CHI", 18952038, 756950.0, "América", emptyList(), bloqueRegionalMercoSur, idiomasDeRegionAmerica)
    val uruguay = Pais("Uruguay", "URU", 3461734, 176215.0, "América", emptyList(), bloqueRegionalMercoSur, idiomasDeRegionAmerica )
    val limitrofesArgentina = listOf(chile, uruguay)
    val argentina = Pais("Argentina", "ARG", 44938712, 278000000.0,"América", limitrofesArgentina, bloqueRegionalMercoSur,idiomasDeRegionAmerica)
    val japon = Pais("Japon", "JPN", 126264931, 377975.0, "asia", emptyList(), bloqueRegionAsia, idiomasEnAsia)
    val bolivia = Pais("Bolivia","BOL",11510000,1099000000.0,"America",listOf(chile,argentina),bloqueRegionalMercoSur,idiomasDeRegionAmerica )

    describe("Pais test") {
        describe("Requerimiento 1: esPlurinacional") {
            it("Argentina es plurinacional") {
                argentina.esPlurinacional().shouldBeTrue()
            }
            it("Japón no es plurinacional") {
                japon.esPlurinacional().shouldBeFalse()
            }
        }
        describe("Requerimiento 2: esUnaIsla") {
            it("Argentina no es una isla") {
                argentina.esUnaIsla().shouldBeFalse()
            }
            it("Japón es una isla") {
                japon.esUnaIsla().shouldBeTrue()
            }
        }
        describe("Requerimiento 3: densidadPoblacional") {
            it("Argentina tiene densidad poblacional de 0.16165004316546762589928057553957") {
                argentina.densidadPoblacional().shouldBe(0.16165004316546762589928057553957)
            }
            it("Uruguay no tiene la misma densidad poblacional de argentina"){
                uruguay.densidadPoblacional().shouldNotBe(0.16165004316546762589928057553957)
            }
        }
        describe("Requerimiento 4: vecinoMasPoblado") {
            it("El vecino más poblado de Argentina entre el mismo pais, Chile y Uruguay es Argentina") {
                argentina.vecinoMasPoblado().shouldBe(argentina)
            }
            it("El vecino más poblado para Japón es Japón") {
                japon.vecinoMasPoblado().shouldBe(japon)
            }
            it("el vecino mas poblado para Bolivia, entre el mismo, Chile y Argentina es Argentina "){
                bolivia.vecinoMasPoblado().shouldBe(argentina)
            }
        }
        describe("Requerimiento 5: sonLimitrofes") {
            it("Argentina es limítrofe de Uruguay") {
                argentina.sonLimitrofes(uruguay).shouldBeTrue()
            }
            it("Argentina no es limítrofe de Japón") {
                argentina.sonLimitrofes(japon).shouldBeFalse()
            }
        }
        describe("Requerimiento 6: necesitanTraduccion") {
            it("Argentina necesita traducción con Japón") {
                argentina.necesitanTraduccion(japon).shouldBeTrue()
            }
            it("Argentina no necesita traducción con Chile") {
                argentina.necesitanTraduccion(chile).shouldBeFalse()
            }
        }
        describe("Requerimiento 7: sonPotencialesAliados") {
            it("Argentina es potencialmente aliado de Uruguay") {
                argentina.sonPotencialesAliados(uruguay).shouldBeTrue()
            }
            it("Argentina no es potencial aliada de Japón") {
                argentina.sonPotencialesAliados(japon).shouldBeFalse()
            }
        }
    }
})