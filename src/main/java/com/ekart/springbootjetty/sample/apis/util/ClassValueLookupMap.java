package com.ekart.springbootjetty.sample.apis.util;

import static com.google.common.base.Preconditions.*;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import com.google.common.collect.ImmutableMap;

/**
 * Performs a hierarchical lookup of keys based on the class hierarchy during a
 * get operation.
 * 
 * Ex: If m = { RuntimeException.class => 10 }, then
 * m.get(IllegalArgumentException.class) will return 10
 * 
 * Null keys and values are not allowed
 * 
 * @author vijay.daniel
 *
 */
@Immutable
@ParametersAreNonnullByDefault
public class ClassValueLookupMap<C, V> {

   private final Map<Class<? extends C>, V> backingMap;

   public ClassValueLookupMap(Map<Class<? extends C>, V> backingMap) {

      checkNotNull(backingMap);

      this.backingMap = ImmutableMap.copyOf(backingMap);
   }

   public Optional<V> get(Class<? extends C> key) {

      checkNotNull(key);

      Class<?> currentClass = key;
      while (!currentClass.equals(Object.class)) {

         V value = backingMap.get(currentClass);
         if (value != null) {

            return Optional.of(value);
         }

         currentClass = currentClass.getSuperclass();
      }

      return Optional.empty();
   }

   public V getOrDefault(Class<? extends C> key, V defaultValue) {

      return this.get(key).orElse(defaultValue);
   }
}
