package com.gem.matching.domain.event;

import com.gem.matching.domain.model.entity.mentee.MenteeId;

public class SubmittedEvent {

  MenteeId menteeId;

  public MenteeId getMenteeId() {
    return menteeId;
  }
}
