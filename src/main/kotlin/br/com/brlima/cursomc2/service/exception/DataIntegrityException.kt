package br.com.brlima.cursomc2.service.exception

class DataIntegrityException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}
