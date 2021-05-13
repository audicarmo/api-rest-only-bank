package br.com.bank.onlybank.services;

import java.util.List;

public interface GenericService<E,K> {

    public E save(E entity) ;
    public E update(E entity) ;
    public void delete(K key);
    public E find(K key);
    public List<E> listAll();
}
