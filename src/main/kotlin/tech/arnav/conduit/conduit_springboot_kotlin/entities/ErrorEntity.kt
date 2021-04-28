package tech.arnav.conduit.conduit_springboot_kotlin.entities

class ErrorEntity private constructor(vararg errors: String) {

    val errors: Errors = Errors(errors)

    class Errors(
            val body: Array<out String> = arrayOf()
    )

    companion object {
        fun create(vararg errors: String) = ErrorEntity(*errors)
    }
}