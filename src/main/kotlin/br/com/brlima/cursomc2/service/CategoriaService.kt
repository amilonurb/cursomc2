package br.com.brlima.cursomc2.service

import br.com.brlima.cursomc2.Categoria
import br.com.brlima.cursomc2.repository.CategoriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class CategoriaService {
    @Autowired
    private lateinit var repository: CategoriaRepository

    fun findAll(): List<Categoria> = repository.findAll()
}
