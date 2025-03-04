package codegym.c10.assignment.service;

import codegym.c10.assignment.dto.ITypeDTO;
import codegym.c10.assignment.model.Type;

public interface ITypeService extends IGenerateService<Type>{
    Iterable<ITypeDTO> getAllTypes();
}
