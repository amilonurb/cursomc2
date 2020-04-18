package br.com.brlima.cursomc2.model

import javax.persistence.Entity

@Entity
class Categoria(var nome: String) : PersistableEntity()
