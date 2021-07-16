package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.mockk.*

class TerminalPaisesTest : DescribeSpec({

    val consolaMock = mockk<Consola>()
    TerminalPaises.entradaSalida = consolaMock

    every { consolaMock.escribirLinea(any()) } just Runs

    describe("Países limítrofes") {
        it("Argentina y Japón no son limítrofes") {
            every { consolaMock.leerLinea() } returns "1" andThen "0" andThen "Argentina" andThen "1" andThen "JPN"
            TerminalPaises.ejecutar()
            verify {
                consolaMock.escribirLinea("Argentina y Japan no son limítrofes\n")
            }
        }
    }

    describe("Países potenciales aliados") {
        it("Uruguay y España no son potenciales aliados") {
            every { consolaMock.leerLinea() } returns "3" andThen "0" andThen "Uruguay" andThen "1" andThen "ESP"
            TerminalPaises.ejecutar()
            verify {
                consolaMock.escribirLinea("Uruguay y Spain no son potenciales aliados\n")
            }
        }
    }

    describe("Continente plurinacional") {
        it("Simulamos opción 5") {
            every { consolaMock.leerLinea() } returns "5"
            TerminalPaises.ejecutar()
            verify {
                consolaMock.escribirLinea("El continente con más países plurinacionales es Europe\n")
            }
        }
    }

    describe("Densidad poblacional de países isla") {
        it("Simulamos opción 6") {
            every { consolaMock.leerLinea() } returns "6"
            TerminalPaises.ejecutar()
            verify {
                consolaMock.escribirLinea("El promedio de densidad poblacional de los países isla es 317.49593759285005\n")
            }
        }
    }
})
