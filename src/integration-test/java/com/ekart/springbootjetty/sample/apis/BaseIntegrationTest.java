package com.ekart.springbootjetty.sample.apis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.BeforeClass;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;

/**
 * To run integration tests on your local box, start up the service using
 * "gradle bootRun". Then run "gradle integrationTest".
 * 
 * To hit the alpha endpoint, run
 * "gradle integrationTest -Dspring.profiles.active=alpha"
 * 
 * To speed up tests by running them in parallel, use maxParallelForks.
 * 
 * Example: "gradle integrationTest -PmaxParallelForks=2".
 * 
 * @author vijay.daniel
 *
 */
public abstract class BaseIntegrationTest {

   private static String host;
   private static String propertiesFilePath = "src/integration-test/resources/application-%s.yml";

   protected static final RestTemplate CLIENT = new TestRestTemplate();
   protected static final ApiInterface API = new ApiInterface();

   @BeforeClass
   public static void setup() throws IOException {

      String profile = System.getProperty("spring.profiles.active", "dev");
      System.out.println("-------- Using profile: " + profile + " -----------");
      Map<String, String> config = loadConfig(profile);
      host = config.get("host");
      API.setClient(CLIENT);
      API.setHost(host);
   }

   protected static String url(String urlPath) {

      return UriBuilder.fromUri("http://" + host).path(urlPath).build().toString();
   }

   protected static HttpHeaders newHeader() {

      HttpHeaders header = new HttpHeaders();
      header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
      return header;
   }

   private static Map<String, String> loadConfig(String profile) throws IOException {

      try (FileInputStream inputStream = new FileInputStream(new File(String.format(propertiesFilePath, profile)))) {

         Yaml yaml = new Yaml();
         @SuppressWarnings("unchecked")
         Map<String, String> config = (Map<String, String>) yaml.load(inputStream);
         return config;
      }
   }
}
