package br.com.motix.services;

import br.com.motix.exceptions.ExistingPlateErrorException;
import br.com.motix.exceptions.MotorcycleNotFoundException;
import br.com.motix.models.Motorcycle;
import br.com.motix.repositories.MotorcycleRepository;
import br.com.motix.services.interfaces.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class MotorcycleServiceImp implements MotorcycleService {

    @Autowired
    private MotorcycleRepository bikeRepository;

    @Override
    public List<Motorcycle> findAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Motorcycle findById(UUID id) {
        return bikeRepository.findById(id).orElse(null);
    }

    @Override
    public Motorcycle findByPlate(String plate) {
        return bikeRepository.findByPlate(plate);
    }

    @Override
    public List<Motorcycle> findAllReadPlatesFalse() {
        return bikeRepository.findAllByIsPlateReadableIsFalse();
    }

    @Override
    public Motorcycle postMotorcycle(Motorcycle motorcycle) {
        if (bikeRepository.existsByPlate(motorcycle.getPlate())) throw new ExistingPlateErrorException("Motorcyle with this plate: " + motorcycle.getPlate() + " already exists.");
        return bikeRepository.save(motorcycle);
    }

    @Override
    public Motorcycle updateMotorcycle(Motorcycle motorcycle) {
        if (bikeRepository.existsByPlate(motorcycle.getPlate())) {
            throw new ExistingPlateErrorException("Motorcyle with this plate: " + motorcycle.getPlate() + " already exists.");
        } else return bikeRepository.save(motorcycle);
    }


    @Override
    public void deleteMotorcycleById(UUID id) {
        if (bikeRepository.existsById(id)) {
            bikeRepository.deleteById(id);
        }else throw new MotorcycleNotFoundException("Motorcycle with ID: " + id + " not found.");
    }

    @Override
    public void deleteMotorcycleByPlate(String plate) {
        if (bikeRepository.existsByPlate(plate)) {
            bikeRepository.deleteByPlate(plate);
        }else throw new MotorcycleNotFoundException("Motorcycle with Plate: " + plate + " not found.");
    }
}
