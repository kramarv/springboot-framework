package com.gem.matching.controller;


import com.gem.matching.domain.model.entity.mentee.Mentee;
import com.gem.matching.domain.model.entity.mentee.MenteeId;
import com.gem.matching.domain.service.DomainService;
import com.gem.matching.exception.MenteeNotFoundException;

import com.gem.matching.resources.MenteeSettingsResponse;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


  private ConcurrentHashMap<MenteeId,Mentee> menteeMap;

  public Controller() {
    menteeMap = new ConcurrentHashMap<MenteeId,Mentee>();
  }


  /**
   * Exposing an endpoint to retrieve Mentee settings.
   *
   * @param id {@link UUID}
   * @return {@link CompletableFuture} of type {@link MenteeSettingsResponse}
   */
  @GetMapping("/mentee/{id}")
  public CompletableFuture<String> getMenteeSettings(@PathVariable UUID id) {
    log.info("uuid={}", id);
    return CompletableFuture.supplyAsync(() -> {
      try {
        MenteeSettingsResponse res = settingsFacadeService.getMenteeSettings(new MenteeId(id));
        if (res.getMentee() != null) return res.getMentee().toString();
        else return "not found";
        
      } catch (Exception ex) {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access level exception", ex);
      }
    });
  }


  @GetMapping("/add")
  public String postMentee() {
   //decalre some file reference File f;
    MenteeId menteeId = new MenteeId();
    Mentee addedMentee = new Mentee(menteeId);
    String result = settingsFacadeService.configureMentee(addedMentee);

    System.out.println("Entering loop");
    System.out.println("keyset " + System.getProperties().keySet());
    System.out.println("entryset " + System.getProperties().entrySet());
    long pid = ProcessHandle.current().pid();
    System.out.println("pid=" + pid);

    try {
      //open file f.open

      for (int i = 1; i > 0; ++i) {
        MenteeId menteeid = new MenteeId();
        Mentee addedmentee = new Mentee(menteeId);
        menteeMap.put(menteeid, addedmentee);
        if (i % 1000 == 0) {
          System.out.println("size=" + menteeMap.size());
        }
      }
    } catch(Exception e) {

    } finally {
      //f.closed
      System.runFinalization();

    }

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
