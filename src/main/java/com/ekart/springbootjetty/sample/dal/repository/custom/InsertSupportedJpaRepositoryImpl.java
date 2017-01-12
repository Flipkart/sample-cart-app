package com.ekart.springbootjetty.sample.dal.repository.custom;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

/**
 * @author vijay.daniel
 */

@Transactional(readOnly = true)
public class InsertSupportedJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> // NOSONAR
      implements InsertSupportedJpaRepository<T, ID> {

   private final EntityManager entityManager;
   private final JpaEntityInformation<T, ID> entityInformation;

   public InsertSupportedJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {

      super(entityInformation, entityManager);

      this.entityManager = requireNonNull(entityManager);
      this.entityInformation = requireNonNull(entityInformation);
   }

   // See http://bit.ly/1r6ApHH
   @Transactional
   @Override
   public void insert(T entity) {

      entityManager.persist(entity);
   }

   @Transactional
   @Override
   public void insert(Iterable<T> entities) {

      for (T entity : entities) {

         entityManager.persist(entity);
      }
   }

   @Transactional
   @Override
   public void insertAndFlush(T entity) {

      insert(entity);
      flush();
   }

   @Transactional
   @Override
   public void insertAndFlush(Iterable<T> entities) {

      insert(entities);
      flush();
   }

   @Override
   public List<T> retrieveAllOrThrow(Collection<ID> ids) {

      List<T> entities = findAll(ids);
      if (entities.size() != ids.size()) {

         Set<ID> diff = new TreeSet<>(ids);
         diff.removeAll(entities.stream().map(entityInformation::getId).collect(toSet()));
         throw new EntityNotFoundException(
               "The following " + entityInformation.getEntityName() + " entries: " + diff + " were not found");
      }
      return entities;
   }
}

