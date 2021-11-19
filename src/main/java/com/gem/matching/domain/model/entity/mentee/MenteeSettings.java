package com.gem.matching.domain.model.entity.mentee;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Accessors(chain = true)

/**
 * Settings is a ValueObject, it should be immutable. {@link Builder} pattern here allows for
 * convenience when replacing a {@code MenteeSettins} with a new instance, but we would like to
 * transfer over properties from an existing {@code MenteeSettins} instance.
 *
 */
public class MenteeSettings {

  @Fetch(FetchMode.SELECT)
  @Builder.Default
  @Column
  @ElementCollection(targetClass=String.class)
  private List<String> settings = new ArrayList<>();

}

