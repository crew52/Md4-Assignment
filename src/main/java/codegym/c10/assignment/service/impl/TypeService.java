package codegym.c10.assignment.service.impl;

import codegym.c10.assignment.dto.ITypeDTO;
import codegym.c10.assignment.exception.NotFountException;
import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.repository.ITypeRepository;
import codegym.c10.assignment.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public Iterable<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public void save(Type type) {
        typeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) throws NotFountException {
        Optional<Type> typeOptional =  typeRepository.findById(id);
        if (typeOptional.isPresent()) {
            return typeOptional;
        }else {
            throw new NotFountException();
        }
    }

    @Override
    public void remove(Long id) {
        typeRepository.deleteTypeById(id);
    }

    @Override
    public Iterable<ITypeDTO> getAllTypes() {
        return typeRepository.getAllTypes();
    }
}
