package com.gem.matching.domain.model.entity.mentee;

import com.gem.matching.domain.model.entity.stereotype.AggregateRoot;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "Mentee")
@AllArgsConstructor
@Getter
@ToString(callSuper = true)
@Slf4j
public class Mentee /*extends AggregateRoot<MenteeId>*/ {

  /**
   * Generates a random UUID'd Mentee with default settings.
   */
  public Mentee() {
    this(new MenteeId());
  }

  @EmbeddedId
  private final MenteeId id;

  @Embedded
  private MenteeSettings menteeSettings;


  /**
   * Constructs a {@code Mentee} using the given {@link MenteeId} with default
   * {@link MenteeSettings}.
   *
   * @param menteeId The {@link MenteeId} of this Mentee
   */
  public Mentee(MenteeId menteeId) {
    this(menteeId, MenteeSettings.builder().build());
  }
}
