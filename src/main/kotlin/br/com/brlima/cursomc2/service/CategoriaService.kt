package br.com.brlima.cursomc2.service

import br.com.brlima.cursomc2.model.Categoria
import br.com.brlima.cursomc2.model.dto.CategoriaDTO
import br.com.brlima.cursomc2.repository.CategoriaRepository
import br.com.brlima.cursomc2.service.exception.DataIntegrityException
import br.com.brlima.cursomc2.service.exception.ObjectNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoriaService(private val repository: CategoriaRepository) {

    fun find(id: Long?): Categoria {
        val categoria: Optional<Categoria> = repository.findById(id!!)
        return categoria.orElseThrow {
            ObjectNotFoundException("Objeto não encontrado! ID: $id, Tipo: ${Categoria::class.simpleName}")
        }
    }

    fun findAll(): List<Categoria> = repository.findAll()

    fun insert(categoria: Categoria): Categoria {
        return repository.save(categoria)
    }

    fun update(categoria: Categoria): Categoria {
        val categoriaDB = this.find(categoria.id)
        this.updateFromData(categoriaDB, categoria)
        return repository.save(categoriaDB)
    }

    fun delete(id: Long) {
        try {
            this.find(id)
            repository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            throw DataIntegrityException("Não é possível excluir uma categoria que possui produtos")
        }
    }

    fun findPage(page: Int, linesPerPage: Int, orderBy: String, sortDirection: String): Page<Categoria> {
        val pageRequest: PageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(sortDirection), orderBy)
        return repository.findAll(pageRequest)
    }

    fun fromDTO(dto: CategoriaDTO): Categoria {
        return Categoria(dto.nome)
    }

    private fun updateFromData(current: Categoria, data: Categoria) {
        current.nome = data.nome
    }
}
