package com.gem.matching.domain.model;


import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import com.gem.matching.domain.repository.MenteeRepository;
import javax.persistence.EntityManager;

/**
 * Interface with default method to retrieve a Tenant or insert if it doeesn't exist.
 */
public interface MenteeEventualConsistencyTemplate {

  /**
   * Returns a {@link Mentee} with the given id, if it exists or generates a new one with that
   * {@link MenteeId}.
   * 
   * @param em an entityManager instance
   * @param menteeRepository the repository to look for/save the tenant with
   * @param menteeId the {@link MenteeId} of the {@link Mentee} to retrieve
   * @return a {@link Mentee} with the given {@link MenteeId}
   */
  default Mentee getOrCreateMentee(EntityManager em, MenteeRepository menteeRepository,
      MenteeId menteeId) {
    final Mentee entity = menteeRepository.findById(menteeId).orElseGet(() -> {
      return menteeRepository.save(new Mentee(menteeId));
    });

    return entity;
  }
}
