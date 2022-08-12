package com.gem.matching.domain.service;

import com.gem.matching.domain.event.SubmittedEvent;
import com.gem.matching.domain.model.MenteeEventualConsistencyTemplate;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import com.gem.matching.domain.repository.MenteeRepository;
import com.gem.matching.exception.MenteeNotFoundException;
import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.resources.MenteeSettingsResponse;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DomainService implements MenteeEventualConsistencyTemplate {

  @Autowired
  private final MenteeRepository menteeRepository;


  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Validate work request event, validates that all fields are valid, that all required fields were
   * submitted and is a valid value based on the field type.
   *
   * @param event {@link SubmittedEvent} event received with new work request.
   * @return true if it is a valid event false otherwise.
   */
  public Boolean validateRequest(@NonNull SubmittedEvent event) {
    Iterable<Mentee> repo = menteeRepository.findAll();
    System.out.println("TK==" + repo.toString());

    menteeRepository.findAll().forEach(m-> {
      System.out.println("===found: " + m.getId() +  "uuid" + m.getId().toString());

    });

    final Optional<Mentee> optionalMentee = menteeRepository.findById(event.getMenteeId());

    if (optionalMentee.isPresent()) {
      log.error("Invalid Mentee Id");
      return false;
    }

    if (isInvalidEvent(event)) {
      log.error("Invalid event");
      return false;
    }

    return true;
  }

  boolean isInvalidEvent(SubmittedEvent event) {
    return event.getMenteeId() == null;
  }


  /**
   * Applies the application logic to configure a list of sites the guests of a {@link Mentee} will
   * have access to.
   *
   * @param MenteeId the {@link MenteeId} of the Mentee to configure the site access for.
   * @throws MenteeNotFoundException throws when a Mentee with the given MenteeId cannot be found
   * @return
   */
  @Transactional
  //@PreAuthorize("hasRole('ROLE_USER')")
  @Retryable({StaleStateException.class, MenteeNotFoundException.class})
  public String configureMentee(@NonNull Mentee mentee) {
      /*throws MenteeNotFoundException {*/

    log.info("confiure mentee with id={}",mentee.getId().toString());
    //MenteeSettings settings = new MenteeSettings(Arrays.asList("name:test","two","three"));
    //log.info("settins={}",settings.getSettings());

    log.info("mentee={}", mentee.getId());
    mentee.setName("test");

    menteeRepository.save(mentee);
    return mentee.toString();

  }


  public MenteeSettingsResponse getMenteeSettings(MenteeId menteeId) {
    Iterable<Mentee> repo = menteeRepository.findAll();
    System.out.println("TK1 repo=" + repo.toString());

    UUID uuid = menteeId.getUuid();
    Optional<Mentee> mentee = menteeRepository.findById(menteeId);
    System.out.println("TK2 mentee=" + mentee);

    MenteeSettingsResponse res = new MenteeSettingsResponse();
    if(mentee.isPresent())
      res.setMentee(mentee.get());
    return res;
  }

}
