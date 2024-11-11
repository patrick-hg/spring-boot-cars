package com.pha.spring_boot_cars.commons;

public interface CommonMapper <E,D> {

    public E toEntity(D dto);
    public D toDto(E entity);
}
