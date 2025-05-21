package br.com.motix.controllers;


import br.com.motix.models.Motorcycle;
import br.com.motix.models.Update;
import br.com.motix.models.User;
import br.com.motix.models.dto.MotorcycleDTO;
import br.com.motix.models.dto.UpdateDTO;
import br.com.motix.models.dto.UserDTO;
import br.com.motix.services.interfaces.MotorcycleService;
import br.com.motix.services.interfaces.UpdatesService;
import br.com.motix.services.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static br.com.motix.models.dto.UpdateDTO.fromEntity;


@RestController
@RequestMapping("/updates")
@Tag(name = "Update", description = "Operações CRUD para updates")
public class UpdateController {

    @Autowired
    private UpdatesService updatesService;

    @Operation(summary = "Listar todos os updates registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updates listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Moto não encontrada")
            }
    )
    @GetMapping
    public List<UpdateDTO> findAll() {
        return UpdateDTO.fromEntityList(updatesService.findAll());
    }


    @Operation(summary = "Listar todos os updates de um usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updates listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Moto não encontrada")
            }
    )
    @GetMapping("/user")
    public List<UpdateDTO> findByUser(
            @Parameter(description = "JSON do Usuário", required = true)
            @RequestBody User user) {
        return UpdateDTO.fromEntityList(updatesService.findByUserId(user));
    }

    @Operation(summary = "Listar todos os updates de uma moto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updates listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Moto não encontrada")
            })
    @GetMapping("/bike")
    public List<UpdateDTO> findByMotorcycle(
            @Parameter(description = "JSON da Moto", required = true)
            @RequestBody Motorcycle motorcycle) {
        return UpdateDTO.fromEntityList(updatesService.findByMotorcycleId(motorcycle));
    }

    @Operation(summary = "Listar todos os updates feitos em uma data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updates listados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Data não encontrada")
            })
    @GetMapping("/date")
    public List<UpdateDTO> findByDate(
            @Parameter(description = "Data", required = true)
            @RequestBody Date date) {
        return UpdateDTO.fromEntityList(updatesService.findByDate(date));
    }

    @Operation(summary = "Atualizar update",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Post atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Post não encontrado")
            }
    )
    @PutMapping
    public UpdateDTO updateUpdate(
            @Parameter(description = "JSON do update", required = true)
            @RequestBody UpdateDTO update) {
        return UpdateDTO.fromEntity(updatesService.update(update.toEntity()));
    }

    @Operation(summary = "Cadastrar novo update",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Update criado com sucesso")
            }
    )
    @PostMapping
    public UpdateDTO postUpdate(
            @Parameter(description = "JSON de Update", required = true)
            @RequestBody UpdateDTO update) {
        Update toMap = update.toEntity();
        Update response = updatesService.post(toMap);
        return UpdateDTO.fromEntity(response);
    }

    @Operation(summary = "Deletar update por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Update deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Update não encontrado")
            }
    )
    @DeleteMapping("/{id:[0-9a-fA-F\\-]{36}}")
    public void deleteUpdate(
            @Parameter(description = "ID do update", required = true)
            @PathVariable UUID id) {
        updatesService.deleteById(id);
    }
}
