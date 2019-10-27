package dev.codenation.challenge.caesarcipher.api_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caesar-cipher")
public class ChallengeController {

    private final ChallengeApiService apiService;

    public ChallengeController(ChallengeApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public ChallengeModel makeMagic() {
        final ChallengeModel challenge = apiService.getChallenge();
        final ChallengeModel solvedChallenge = apiService.solveChallenge(challenge);
        return solvedChallenge;
        // return apiService.postSolution(solvedChallenge);
    }

}
