package codegym.c10.assignment.repository;

import codegym.c10.assignment.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITypeRepository extends CrudRepository<Type, Long> {
}
