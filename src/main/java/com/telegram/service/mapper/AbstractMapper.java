package com.telegram.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public abstract class AbstractMapper<E, D> implements Mapper<E, D> {
  private final ModelMapper mapper;

  private final Class<E> entityClass;
  private final Class<D> dtoClass;

  @Autowired
  protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass, ModelMapper mapper) {
    this.entityClass = entityClass;
    this.dtoClass = dtoClass;
    this.mapper = mapper;
  }

  @Override
  public E toEntity(D dto) {
    return Objects.isNull(dto)
           ? null
           : mapper.map(dto, entityClass);
  }

  @Override
  public D toDto(E entity) {
    return Objects.isNull(entity)
           ? null
           : mapper.map(entity, dtoClass);
  }

  @Override
  public List<D> toDtoList(List<E> entities) {
    return entities.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Override
  public Set<E> toEntitySet(Collection<D> dtos) {
    return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
  }
}
