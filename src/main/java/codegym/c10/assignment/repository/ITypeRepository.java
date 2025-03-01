package codegym.c10.assignment.repository;

import codegym.c10.assignment.dto.ITypeDTO;
import codegym.c10.assignment.model.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ITypeRepository extends CrudRepository<Type, Long> {
    @Query(nativeQuery = true,
            value = "SELECT \n" +
                    "    t.id, \n" +
                    "    t.name, \n" +
                    "    COUNT(t.name) AS count\n" +
                    "FROM \n" +
                    "    computer c\n" +
                    "RIGHT JOIN \n" +
                    "    type t\n" +
                    "ON \n" +
                    "    c.type_id = t.id\n" +
                    "GROUP BY \n" +
                    "    t.name,  \n" +
                    "    t.id;")
    Iterable<ITypeDTO> getAllTypes();
}
