package com.ekart.springbootjetty.sample.apis.config.spring;

import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.ClassLoadingGaugeSet;
import com.codahale.metrics.jvm.FileDescriptorRatioGauge;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.codahale.metrics.logback.InstrumentedAppender;
import com.github.kristofa.brave.LoggingSpanCollector;
import com.github.kristofa.brave.SpanCollector;
import com.readytalk.metrics.StatsDReporter;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@Configuration
@EnableMetrics
public class EnvironmentConfig {

   @Value("${statsd.host}")
   private String statsdHost;

   @Value("${statsd.port}")
   private int statsdPort;

   @Value("${statsd.pushIntervalSeconds}")
   private long statsdPushIntervalSeconds;

   @Value("${statsd.prefix}")
   private String statsdMetricPrefix;

   @ConditionalOnProperty(value = "spring.zipkin.enabled", havingValue = "true")
   @Bean
   public AlwaysSampler alwaysSampler() {

      return new AlwaysSampler();
   }

   // For testing on local
   // Taken from http://bit.ly/1RJexHI
   @Bean
   @ConditionalOnProperty(value = "spring.zipkin.enabled", havingValue = "true")
   public SpanCollector spanCollector() {

      return new LoggingSpanCollector();
   }

   @Bean
   public MetricRegistry metricRegistry() {

      MetricRegistry metricRegistry = new MetricRegistry();

      // http://metrics.dropwizard.io/3.1.0/manual/jvm/
      metricRegistry.register("jvm.gc", new GarbageCollectorMetricSet());
      metricRegistry.register("jvm.mem", new MemoryUsageGaugeSet());
      metricRegistry.register("jvm.threads", new ThreadStatesGaugeSet());
      metricRegistry.register("jvm.classloader", new ClassLoadingGaugeSet());
      metricRegistry.register("jvm.files", new FileDescriptorRatioGauge());
      return metricRegistry;
   }

   @Bean
   public InstrumentedAppender loggerInstrumentedAppender() {

      // http://metrics.dropwizard.io/3.1.0/manual/logback/
      LoggerContext factory = (LoggerContext) LoggerFactory.getILoggerFactory();

      InstrumentedAppender metricsAppender = new InstrumentedAppender(metricRegistry());
      metricsAppender.setContext(factory);
      metricsAppender.setName("logback_metrics");
      metricsAppender.start();

      factory.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(metricsAppender);

      return metricsAppender;
   }

   @Bean
   public JmxReporter jmxReporter() {

      JmxReporter reporter = JmxReporter.forRegistry(metricRegistry()).build();
      reporter.start();
      return reporter;
   }

   // We don't want to use JMX in production for pushing metrics to Cosmos.
   // More here:
   // https://dropwizard.github.io/metrics/3.1.0/manual/core/
   // https://groups.google.com/forum/#!topic/metrics-user/bgBzP5KJ-kc
   @Bean
   public StatsDReporter statsdReporter() {

      StatsDReporter statsdReporter =
            StatsDReporter.forRegistry(metricRegistry()).prefixedWith(statsdMetricPrefix).build(statsdHost, statsdPort);
      statsdReporter.start(statsdPushIntervalSeconds, TimeUnit.SECONDS);
      return statsdReporter;
   }

   @Bean
   public MetricsConfigurerAdapter metricsConfigurerAdapter() {

      MetricRegistry metricRegistry = metricRegistry();
      return new MetricsConfigurerAdapter() {

         @Override
         public MetricRegistry getMetricRegistry() {

            return metricRegistry;
         }
      };
   }
}
