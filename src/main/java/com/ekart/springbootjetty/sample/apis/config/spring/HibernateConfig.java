package com.ekart.springbootjetty.sample.apis.config.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author paras.narang
 */

@Configuration
@ConfigurationProperties(prefix = "database")
public class HibernateConfig {

   // Idea taken from
   // http://stackoverflow.com/questions/26580948/spring-boot-inject-mapenum-class-from-application-yml

   private Map<String, String> hibernateProperties;

   public Map<String, String> getHibernateProperties() {
      System.out.println(".....Getting hibernationConfig " + hibernateProperties);

      return hibernateProperties;
   }

   public void setHibernateProperties(Map<String, String> hibernateProperties) {
      System.out.println("......Setting hibernationConfig " + hibernateProperties);
      this.hibernateProperties = hibernateProperties;
   }

   @Override
   public String toString() {

      return "HibernateProperties [hibernateProperties=" + hibernateProperties + "]";
   }
}
