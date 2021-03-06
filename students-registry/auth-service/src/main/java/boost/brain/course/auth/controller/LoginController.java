package boost.brain.course.auth.controller;


import boost.brain.course.auth.Constants;
import boost.brain.course.auth.repository.SessionsRepository;
import boost.brain.course.common.auth.Credentials;
import boost.brain.course.common.auth.Session;
import boost.brain.course.common.auth.SessionCheck;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(path = Constants.LOGIN_CONTROLLER_PREFIX)
public class LoginController implements LoginControllerSwaggerAnnotations {
    private final SessionsRepository sessionsRepository;

    @Autowired
    public LoginController(SessionsRepository sessionsRepository) {
        this.sessionsRepository = sessionsRepository;
    }

    @Override
    @PostMapping(path = Constants.LOGIN_PREFIX,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Session login(@RequestBody Credentials credentials){
        log.info("LoginController: login method started");
        log.info("credentials=" + credentials.toString());
        long startTime = System.nanoTime();
        Session startedSession = sessionsRepository.startSession(credentials);
        log.info("lag of sessionsRepository.startSession(credentials) = " + (System.nanoTime() - startTime) / 1000000);
        return startedSession;
    }

    @Override
    @GetMapping(path = Constants.LOGOUT_PREFIX + "/{sessionId}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean logout(@PathVariable String sessionId){
        log.info("LoginController: logout method started");
        long startTime = System.nanoTime();
        Boolean closedSession = sessionsRepository.closeSession(sessionId);
        log.info("lag of sessionsRepository.closeSession(sessionId) = " + (System.nanoTime() - startTime) / 1000000);

        return closedSession;
    }

    @Override
    @GetMapping(path = Constants.CHECK_PREFIX)
    public SessionCheck getCheckSession(@RequestHeader("sessionId") String sessionId) {
        log.info("LoginController: getCheckSession method started");
        long startTime = System.nanoTime();
        SessionCheck sessionCheck = sessionsRepository.getCheckSession(sessionId);
        log.info("lag of sessionsRepository.checkSession(sessionId) = " + (System.nanoTime() - startTime) / 1000000);
        return sessionCheck;
    }
}
