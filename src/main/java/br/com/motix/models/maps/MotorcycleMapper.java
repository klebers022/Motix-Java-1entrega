package br.com.motix.models.maps;

import br.com.motix.models.Motorcycle;
import br.com.motix.models.dto.MotorcycleDTO;



//TESTE

//Acabei não implementando porque achei redundante
//Tirar duvida sobre o uso


public class MotorcycleMapper {
    public static Motorcycle toEntity(MotorcycleDTO dto) {
        Motorcycle mapped = new Motorcycle();
        mapped.setPlate(dto.getPlate());
        mapped.setType(dto.getType());
        mapped.setSector(dto.getSector());
        mapped.setPosition(dto.getPosition());
        mapped.setPlate(dto.readPlate(dto.getPlate()));
        mapped.setPlateReadable(dto.isPlateReadable());
        return mapped;
    }

    public static MotorcycleDTO toDTO(Motorcycle mapped) {
        MotorcycleDTO dto = new MotorcycleDTO();
        dto.setPlate(mapped.getPlate());
        dto.setType(mapped.getType());
        dto.setSector(mapped.getSector());
        dto.setPosition(mapped.getPosition());
        dto.setPlateReadable(mapped.isPlateReadable());
        return dto;
    }
}

