package com.gem.matching.domain.model.entity.stereotype;

import com.gem.matching.domain.model.entity.stereotype.Id;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter(AccessLevel.PRIVATE)
@Getter
@ToString
public abstract class AggregateRoot<I extends Id> implements Entity<I> {

  @Version
  @Column(nullable = false)
  private long version;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private Instant createdDate;

  @Column(nullable = false)
  @LastModifiedDate
  private Instant modifiedDate;

  @Column(updatable = false)
  @CreatedBy
  private String createdBy;

  @Column(nullable = false)
  @LastModifiedBy
  private String modifiedBy;
}


