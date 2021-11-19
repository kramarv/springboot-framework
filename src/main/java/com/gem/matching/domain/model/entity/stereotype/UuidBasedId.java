package com.gem.matching.domain.model.entity.stereotype;

import java.util.UUID;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class UuidBasedId implements Id {
  private static final long serialVersionUID = 7190229835966452548L;

  private UUID uuid;

  public UuidBasedId() {
    this(UUID.randomUUID());
  }

  public UuidBasedId(UUID id) {
    this.uuid = id;
  }

  public UuidBasedId(String seed) {
    this.uuid = UUID.nameUUIDFromBytes(seed.getBytes());
  }
}
