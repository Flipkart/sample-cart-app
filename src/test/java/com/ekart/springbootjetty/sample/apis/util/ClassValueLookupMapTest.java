package com.ekart.springbootjetty.sample.apis.util;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.junit.Test;

import com.ekart.springbootjetty.sample.apis.util.ClassValueLookupMap;
import com.google.common.collect.ImmutableMap;

/**
 * @author vijay.daniel
 *
 */
public class ClassValueLookupMapTest {

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionDuringCreationIfBackingMapIsNull() {

      new ClassValueLookupMap<>(null);
   }

   @Test(expected = NullPointerException.class)
   public void shouldRaiseExceptionIfNullKeyIsPassedToGet() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(RuntimeException.class, 10));
      map.get(null);
   }

   @Test
   public void shouldReturnValueWhenDirectMappingIsPresentForGet() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(RuntimeException.class, 10));
      assertThat(map.get(RuntimeException.class), is(Optional.of(10)));
   }

   @Test
   public void shouldReturnValueWhenASuperClassMappingIsPresentForGet() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(ValidationException.class, 10));
      assertThat(map.get(ConstraintViolationException.class), is(Optional.of(10)));
   }

   @Test
   public void shouldReturnEmptyWhenMappingIsNotPresentForGet() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(ValidationException.class, 10));
      assertThat(map.get(RuntimeException.class), is(Optional.empty()));
   }

   @Test
   public void shouldReturnValueWhenDirectMappingIsPresentForGetOrDefault() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(RuntimeException.class, 10));
      assertThat(map.getOrDefault(RuntimeException.class, 20), is(10));
   }

   @Test
   public void shouldReturnValueWhenASuperClassMappingIsPresentForGetOrDefault() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(ValidationException.class, 10));
      assertThat(map.getOrDefault(ConstraintViolationException.class, 20), is(10));
   }

   @Test
   public void shouldReturnDefaultValueWhenMappingIsNotPresentForGetOrDefault() {

      ClassValueLookupMap<Exception, Integer> map = new ClassValueLookupMap<>(
            ImmutableMap.of(ValidationException.class, 10));
      assertThat(map.getOrDefault(RuntimeException.class, 20), is(20));
   }
}
