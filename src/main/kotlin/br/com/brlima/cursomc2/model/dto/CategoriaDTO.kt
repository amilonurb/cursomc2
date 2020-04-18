package br.com.brlima.cursomc2.model.dto

import br.com.brlima.cursomc2.model.Categoria
import java.io.Serializable

class CategoriaDTO(categoria: Categoria) : Serializable {
    var id: Long? = categoria.id
    var nome: String = categoria.nome
}
