package br.com.motix.services;

import br.com.motix.exceptions.ExistingUpdateErrorException;
import br.com.motix.exceptions.MotorcycleNotFoundException;
import br.com.motix.models.Motorcycle;
import br.com.motix.models.Update;
import br.com.motix.models.User;
import br.com.motix.repositories.MotorcycleRepository;
import br.com.motix.repositories.UpdateRepository;
import br.com.motix.repositories.UserRepository;
import br.com.motix.services.interfaces.UpdatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class UpdateServiceImp implements UpdatesService {

    @Autowired
    private UpdateRepository updateRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Update> findAll() {
        return updateRepository.findAll();
    }


    @Override
    public List<Update> findByUserId(User user) {
        return updateRepository.findAllByUser(user);
    }

    @Override
    public List<Update> findByMotorcycleId(Motorcycle motorcycle) {
        if(motorcycleRepository.existsById(motorcycle.getId())) {
            return updateRepository.findAllByMotorcycle(motorcycle);
        }else throw new MotorcycleNotFoundException("This Motorcycle does not exist");   //<----------------------------------------------
    }

    @Override
    public List<Update> findByDate(Date date) {
        return updateRepository.findAllByUpdateDate(date);
    }

    @Override
    public void deleteById(UUID id) {
        if(updateRepository.existsById(id)){
            updateRepository.deleteById(id);
        }else throw new ExistingUpdateErrorException("This update does not exist");

    }

    @Override
    public Update update(Update update) {
        if(userRepository.existsById(update.getId())){
            return updateRepository.save(update);
        }else throw new ExistingUpdateErrorException("This update does not exist");
    }

    @Override
    public Update post(Update update) {
        if (!updateRepository.existsById(update.getId())){
            return updateRepository.save(update);
        } else throw new ExistingUpdateErrorException("This update alread exists");
    }
}
