
package com.denuncias.denunciaservice.repository

import com.denuncias.denunciaservice.entity.Denuncia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DenunciaRepository : JpaRepository<Denuncia, Int> {

    // Buscar denuncias por DNI
    fun findByDniOrderByIdAsc(dni: String): List<Denuncia>

    // Listar denuncias con paginacion
    @Query(
        value = """
            SELECT * FROM denuncias
            ORDER BY id ASC
            LIMIT :limit OFFSET :offset
        """,
        nativeQuery = true
    )
    fun listarPaginado(
        @Param("offset") offset: Int,
        @Param("limit") limit: Int
    ): List<Denuncia>
}
