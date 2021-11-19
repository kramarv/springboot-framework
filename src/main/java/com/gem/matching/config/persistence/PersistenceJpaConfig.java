package com.gem.matching.config.persistence;

import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Configuration for enabling transaction management and other Persistence configurations.
 */
@Configuration
@EnableTransactionManagement
@EnableRetry
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceJpaConfig {

  @Autowired
  private EntityManagerFactory entityManagerFactoryBean;

  /**
   * Wire up the transaction Manager Bean, needed for non-default transactional management concerns.
   * 
   * @return A {@link PlatformTransactionManager} instance for managing transactions
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactoryBean);
    return transactionManager;
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return () -> Optional
        .ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
  }

  /**
   * Sets up the Retry configuration for Spring-Retry.
   * 
   * @param retryListener the logger instance used to log retry attempts
   * @return the {@link RetryTemplate} with our retry configuration
   */
  @Bean
  public RetryTemplate retryTemplate(RetryListenerLogger retryListener) {
    RetryTemplate retryTemplate = new RetryTemplate();

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
    retryPolicy.setMaxAttempts(2);

    FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
    backOffPolicy.setBackOffPeriod(1500); // 1.5 seconds

    retryTemplate.setRetryPolicy(retryPolicy);
    retryTemplate.setBackOffPolicy(backOffPolicy);
    retryTemplate.registerListener(retryListener);

    return retryTemplate;
  }
}
