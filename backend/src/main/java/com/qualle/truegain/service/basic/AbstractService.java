package com.qualle.truegain.service.basic;

import com.qualle.truegain.model.exception.EntityNotFoundException;
import com.qualle.truegain.service.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractService<T, DTO, ID extends Serializable> implements GenericService<DTO, ID> {

    @Override
    public List<DTO> getAll() {

        try {
            return getMapper().toDto(getRepository().findAll());

        } catch (RuntimeException e) {
//            log.error("Get all entities [" + getRepository() + "] error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public DTO getById(ID id) {

        try {
            T t = getRepository().findById(id)
                    .orElseThrow(() -> new EntityNotFoundException());
            return getMapper().toDto(t);

        } catch (RuntimeException e) {
//            log.error("Get entity [" + getRepository() + "] by id error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void save(DTO dto) {

        try {
            T t = getMapper().fromDto(dto);
            getRepository().save(t);

        } catch (RuntimeException e) {
//            log.error("Update entity [" + getRepository() + "] error: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(ID id) {

        try {
            getRepository().deleteById(id);

        } catch (RuntimeException e) {
//            log.error("Delete entity [" + getRepository() + "] error: " + e.getMessage());
            throw e;
        }
    }

    protected abstract CrudRepository<T, ID> getRepository();

    protected abstract GenericMapper<T, DTO> getMapper();
}