package com.telegram.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Mapper<E, D> {
  E toEntity(D d);

  D toDto(E e);

  List<D> toDtoList(List<E> eList);

  Set<E> toEntitySet(Collection<D> dList);
}
