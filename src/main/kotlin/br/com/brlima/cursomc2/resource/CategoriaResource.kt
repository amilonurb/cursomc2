package br.com.brlima.cursomc2.resource

import br.com.brlima.cursomc2.Categoria
import br.com.brlima.cursomc2.Cursomc2Application
import br.com.brlima.cursomc2.service.CategoriaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categorias")
class CategoriaResource {

    @Autowired
    private lateinit var service: CategoriaService

    @GetMapping
    fun findAll(): ResponseEntity<List<Categoria>> {
        val categorias: List<Categoria> = service.findAll()
        return ResponseEntity.ok().body(categorias)
    }
}
