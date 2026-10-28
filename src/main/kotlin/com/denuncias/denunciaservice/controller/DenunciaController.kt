
package com.denuncias.denunciaservice.controller

import com.denuncias.denunciaservice.entity.Denuncia
import com.denuncias.denunciaservice.service.DenunciaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/denuncias")
class DenunciaController(
    private val service: DenunciaService
) {

    // Registrar denuncia
    @PostMapping("", "/")
    @ResponseStatus(HttpStatus.CREATED)
    fun registrar(
        @RequestBody denuncia: Denuncia
    ): Denuncia {

        if (!denuncia.dni.matches(Regex("\\d{8}")) ||
            denuncia.tipo.isBlank() ||
            denuncia.direccion.isBlank() ||
            denuncia.descripcion.isBlank() ||
            denuncia.tipo.length > 30 ||
            denuncia.direccion.length > 200 ||
            denuncia.descripcion.length > 255
        ) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Datos de denuncia invalidos"
            )
        }

        return service.registrar(denuncia)
    }

    // Listar denuncias
    @GetMapping("", "/")
    fun listar(
        @RequestParam(defaultValue = "0") offset: Int,
        @RequestParam(defaultValue = "5") limit: Int
    ): List<Denuncia> {
        return service.listar(offset, limit)
    }

    // Buscar denuncias por DNI
    @GetMapping("/ciudadano/{dni}")
    fun buscarPorDni(
        @PathVariable dni: String
    ): List<Denuncia> {
        return service.buscarPorDni(dni)
    }

    // Obtener denuncia por ID
    @GetMapping("/{id}")
    fun obtener(
        @PathVariable id: Int
    ): Denuncia {
        return service.obtener(id)
    }

    // Atender denuncia
    @PutMapping("/{id}")
    fun atender(
        @PathVariable id: Int
    ): Denuncia {
        return service.atender(id)
    }
}
