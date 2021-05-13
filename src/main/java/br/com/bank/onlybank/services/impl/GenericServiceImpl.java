package br.com.bank.onlybank.services.impl;

import br.com.bank.onlybank.services.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class GenericServiceImpl<E, K> implements GenericService<E, K> {

    private JpaRepository<E, K> jpaRepository;

    public GenericServiceImpl(JpaRepository<E, K> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    protected abstract E update(E entity, E newEntity);

    @Override
    public E save(E entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public E update(E entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public E find(K key) {
        Optional<E> obj = jpaRepository.findById(key);
        return obj.orElse(null);
    }

    @Override
    public void delete(K key) {
        jpaRepository.deleteById(key);
    }

    @Override
    public List<E> listAll() {
        return jpaRepository.findAll();
    }

    public Page<E> sort(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return jpaRepository.findAll(pageRequest);
    }

    public List<E> saveAll(List<E> entities) {
        return jpaRepository.saveAll(entities);
    }
}
