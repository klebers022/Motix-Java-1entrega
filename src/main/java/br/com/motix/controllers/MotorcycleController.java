package br.com.motix.controllers;


import br.com.motix.models.dto.MotorcycleDTO;
import br.com.motix.models.Motorcycle;
import br.com.motix.services.interfaces.MotorcycleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/bikes")
@Tag(name = "Motorcycle", description = "Operações CRUD para motocicletas")
public class MotorcycleController {

    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping
    @Operation(summary = "Listar todas as motocicletas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Motocicletas listadas com sucesso")
            })
    public List<MotorcycleDTO> getAllMotorcycles() {
        return MotorcycleDTO.fromEntityList(motorcycleService.findAll());
    }

    @GetMapping("/plate-error")
    @Operation(summary = "Listar motocicletas com falha na leitura da placa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Motocicletas com erro de placa listadas com sucesso")
            })
    public List<MotorcycleDTO> findAllPlateErrors() {
        return MotorcycleDTO.fromEntityList(motorcycleService.findAllReadPlatesFalse());
    }

    @GetMapping("/id/{id:[0-9a-fA-F\\-]{36}}")
    @Operation(summary = "Buscar motocicleta por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Motocicleta encontrada"),
                    @ApiResponse(responseCode = "404", description = "Motocicleta não encontrada")
            })
    public MotorcycleDTO findById(@Parameter(description = "UUID da motocicleta")
                                  @PathVariable UUID id) {
        return MotorcycleDTO.fromEntity(motorcycleService.findById(id));
    }

    @GetMapping("/{plate}")
    @Operation(summary = "Buscar motocicleta por placa",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Motocicleta encontrada"),
                    @ApiResponse(responseCode = "404", description = "Motocicleta não encontrada")
            })
    public MotorcycleDTO findByPlate(@Parameter(description = "Placa da motocicleta") @PathVariable String plate) {
        return MotorcycleDTO.fromEntity(motorcycleService.findByPlate(plate));
    }

    @PutMapping
    @Operation(summary = "Atualizar uma motocicleta existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Motocicleta atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Motocicleta não encontrada")
            })
    public MotorcycleDTO updateMotorclycle(@Parameter(description = "JSON de uma motocicleta") @RequestBody MotorcycleDTO dto) {
        return MotorcycleDTO.fromEntity(motorcycleService.updateMotorcycle(dto.toEntity()));
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova moto usando DTO",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Motocicleta criada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Não foi possivel cadastrar a Motocicleta")
            })
    public MotorcycleDTO postMotorcycle(@Parameter(description = "JSON de uma motocicletaDTO") @RequestBody MotorcycleDTO dto) {
        Motorcycle toMap = dto.toEntity();
        Motorcycle response = motorcycleService.postMotorcycle(toMap);
        return MotorcycleDTO.fromEntity(response);
    }

    @DeleteMapping("/{id:[0-9a-fA-F\\-]{36}}")
    @Operation(summary = "Deletar motocicleta por ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Motocicleta deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Motocicleta não encontrada")
            })
    public void deleteMotocycleById(@PathVariable UUID id) {
        motorcycleService.deleteMotorcycleById(id);
    }

    @DeleteMapping("/{plate}")
    @Operation(summary = "Deletar motocicleta por placa",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Motocicleta deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Motocicleta não encontrada")
            })
    public void deleteMotocycleByPlate(@PathVariable String plate) {
        motorcycleService.deleteMotorcycleByPlate(plate);
    }
}

