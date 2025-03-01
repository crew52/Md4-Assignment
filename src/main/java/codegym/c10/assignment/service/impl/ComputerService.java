package codegym.c10.assignment.service.impl;

import codegym.c10.assignment.model.Computer;
import codegym.c10.assignment.model.Type;
import codegym.c10.assignment.repository.IComputerRepository;
import codegym.c10.assignment.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComputerService implements IComputerService {
    @Autowired
    private IComputerRepository computerRepository;

    @Override
    public Iterable<Computer> findAllByType(Type type) {
        return computerRepository.findAllByType(type);
    }

    @Override
    public Page<Computer> findAll(Pageable pageable) {
        return computerRepository.findAll(pageable);
    }

    @Override
    public Page<Computer> findAllByNameContaining(Pageable pageable, String name) {
        return computerRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Iterable<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public void save(Computer computer) {
        computerRepository.save(computer);
    }

    @Override
    public Optional<Computer> findById(Long id) {
        return computerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        computerRepository.deleteById(id);
    }
}
