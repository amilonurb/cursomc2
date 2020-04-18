package br.com.brlima.cursomc2.resource.exception

import java.io.Serializable

open class StandardError(
        val timestamp: Long,
        val status: Int,
        val error: String,
        val message: String?,
        val path: String) : Serializable
