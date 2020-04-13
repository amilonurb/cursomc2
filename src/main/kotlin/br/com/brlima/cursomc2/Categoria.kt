package br.com.brlima.cursomc2

import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class Categoria(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private var id: Long,
        private var nome: String) : Serializable
