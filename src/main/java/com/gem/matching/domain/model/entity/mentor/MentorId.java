package com.gem.matching.domain.model.entity.mentor;


import com.gem.matching.domain.model.entity.stereotype.Id;
import java.util.UUID;
import javax.persistence.Embeddable;

/**
 * Our Value Object (https://martinfowler.com/bliki/ValueObject.html) for our shared
 *
 */
@Embeddable
public final class MentorId implements Id {

    /**
     * Auto-generated serialVersionUID.
     */
    private static final long serialVersionUID = 7404485051422794852L;

    private UUID uuid;

    public MentorId() {
      this(UUID.randomUUID());
    }

    public MentorId(UUID uuid) {
      this.uuid = uuid;
    }

    public MentorId(String seed) {
      uuid = UUID.nameUUIDFromBytes(seed.getBytes());
    }

    public UUID getUuid() {
      return uuid;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      MentorId other = (MentorId) obj;
      if (uuid == null) {
        if (other.uuid != null)
          return false;
      } else if (!uuid.equals(other.uuid))
        return false;
      return true;
    }

    @Override
    public String toString() {
      return "MentorId [uuid=" + uuid + "]";
    }

}
