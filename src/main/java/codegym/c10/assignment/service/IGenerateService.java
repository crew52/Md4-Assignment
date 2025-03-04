package codegym.c10.assignment.service;

import codegym.c10.assignment.exception.NotFountException;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id) throws NotFountException;

    void remove(Long id);
}
