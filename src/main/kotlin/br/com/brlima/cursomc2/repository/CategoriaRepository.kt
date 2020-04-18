package br.com.brlima.cursomc2.repository

import br.com.brlima.cursomc2.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long>
