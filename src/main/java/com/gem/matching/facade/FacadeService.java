package com.gem.matching.facade;

import com.gem.matching.domain.model.MenteeEventualConsistencyTemplate;
import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import com.gem.matching.domain.repository.MenteeRepository;

import java.util.Optional;
import java.util.function.Function;

public abstract class FacadeService implements MenteeEventualConsistencyTemplate {

  public abstract MenteeRepository getMenteeRepository();

  /**
   * Obtains the specified {@link Mentee} with the given {@link MenteeId} and toggles the specified
   * function.
   *
   * @param MenteeId MenteeId the {@link MenteeId} of the {@link Mentee}
   * @param function the function to apply to the {@link Mentee}
   */
  protected Boolean executeFunctionIfMenteeExists(MenteeId MenteeId,
      Function<Mentee, Boolean> function) {
    Optional<Mentee> check = getMenteeRepository().findById(MenteeId.getUuid());
    return check.isPresent() ? function.apply(check.get()) : false;
  }
}
