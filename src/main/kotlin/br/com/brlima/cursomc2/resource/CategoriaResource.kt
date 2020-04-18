package br.com.brlima.cursomc2.resource

import br.com.brlima.cursomc2.model.Categoria
import br.com.brlima.cursomc2.model.dto.CategoriaDTO
import br.com.brlima.cursomc2.service.CategoriaService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.stream.Collectors

@RestController
@RequestMapping("/categorias")
class CategoriaResource(private val service: CategoriaService) {

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: Long): ResponseEntity<Categoria> {
        val categoria = service.find(id)
        return ResponseEntity.ok().body(categoria)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<CategoriaDTO>> {
        val categorias: List<CategoriaDTO> = service.findAll().stream().map(::CategoriaDTO).collect(Collectors.toList())
        return ResponseEntity.ok().body(categorias)
    }

    fun insert(/*@Valid*/ @RequestBody categoriaDTO: CategoriaDTO): ResponseEntity<Void> {
        var categoria = service.fromDTO(categoriaDTO)
        categoria = service.insert(categoria)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.id).toUri()
        return ResponseEntity.created(uri).build()
    }

    fun update(/*@Valid*/ @RequestBody categoriaDTO: CategoriaDTO, @PathVariable id: Long): ResponseEntity<Void> {
        var categoria = service.fromDTO(categoriaDTO)
        categoria.id = id
        categoria = service.update(categoria)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/page")
    fun findPage(
            @RequestParam(value = "page", defaultValue = "0") page: Int,
            @RequestParam(value = "linesPerPage", defaultValue = "24") linesPerPage: Int,
            @RequestParam(value = "page", defaultValue = "0") orderBy: String,
            @RequestParam(value = "page", defaultValue = "0") sortDirection: String): ResponseEntity<Page<CategoriaDTO>> {
        val categorias = service.findPage(page, linesPerPage, orderBy, sortDirection).map(::CategoriaDTO)
        return ResponseEntity.ok().body(categorias)
    }
}
