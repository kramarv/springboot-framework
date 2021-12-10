package com.gem.matching.controller;


import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import com.gem.matching.domain.service.DomainService;
import com.gem.matching.exception.MenteeNotFoundException;

import com.gem.matching.resources.MenteeSettingsResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class Controller {

  private static final Logger log = LoggerFactory.getLogger(Controller.class);

  @Autowired
  private DomainService settingsFacadeService;

  /**
   * Exposing an endpoint to retrieve Mentee settings.
   *
   * @param id {@link UUID}
   * @return {@link CompletableFuture} of type {@link MenteeSettingsResponse}
   */
  @GetMapping("/mentee/{id}")
  public CompletableFuture<MenteeSettingsResponse> getMenteeSettings(@PathVariable UUID id) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        return settingsFacadeService.getMenteeSettings(new MenteeId(id));
      } catch (Exception ex) {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access level exception", ex);
      }
    });
  }


  @GetMapping("/add")
  public String postMentee() {
    MenteeId menteeId = new MenteeId();
    Mentee addedMentee = new Mentee(menteeId);
    String result = settingsFacadeService.configureMentee(addedMentee);
    return result;
  }


    /*
    return CompletableFuture.supplyAsync(() -> {
      try {

        MenteeId menteeId=new MenteeId();
        Mentee addedMentee = new Mentee(menteeId);
        return menteeId.toString();

      } catch (Exception ex) {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access level exception", ex);
      }
    });
  }*/
}
