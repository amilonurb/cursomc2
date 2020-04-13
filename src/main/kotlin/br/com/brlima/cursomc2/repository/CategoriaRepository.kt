package br.com.brlima.cursomc2.repository

import br.com.brlima.cursomc2.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long>
