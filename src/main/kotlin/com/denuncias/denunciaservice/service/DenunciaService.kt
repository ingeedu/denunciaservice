
package com.denuncias.denunciaservice.service

import com.denuncias.denunciaservice.entity.Denuncia
import com.denuncias.denunciaservice.repository.DenunciaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class DenunciaService(
    private val repository: DenunciaRepository
) {

    // Registrar una nueva denuncia
    fun registrar(denuncia: Denuncia): Denuncia {
        denuncia.id = null
        denuncia.fecha = LocalDateTime.now()
        denuncia.estado = 1

        return repository.save(denuncia)
    }

    // Listar denuncias paginadas
    fun listar(offset: Int, limit: Int): List<Denuncia> {
        if (offset < 0 || limit < 1 || limit > 100) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Paginacion invalida"
            )
        }

        return repository.listarPaginado(offset, limit)
    }

    // Obtener una denuncia por ID
    fun obtener(id: Int): Denuncia {
        return repository.findById(id).orElseThrow {
            ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Denuncia no encontrada"
            )
        }
    }

    // Buscar denuncias por DNI
    fun buscarPorDni(dni: String): List<Denuncia> {
        return repository.findByDniOrderByIdAsc(dni)
    }

    // Marcar una denuncia como atendida
    fun atender(id: Int): Denuncia {
        val denuncia = obtener(id)
        denuncia.estado = 0

        return repository.save(denuncia)
    }
}
