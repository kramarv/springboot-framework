package com.gem.matching.domain.repository;

import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenteeRepository extends CrudRepository<Mentee, UUID> {}
