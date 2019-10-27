package dev.codenation.challenge.caesarcipher.api_client;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.codenation.challenge.caesarcipher.cipher.CaesarCipher;
import dev.codenation.challenge.caesarcipher.cipher.Sha1Digester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ChallengeApiService {

    @Value("${codenation.user-token}")
    private String userToken;

    @Value("${codenation.api-url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final Sha1Digester digester;

    public ChallengeApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.digester = new Sha1Digester();
    }

    public ChallengeModel getChallenge() {

        final String generateData = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .pathSegment("challenge", "dev-ps", "generate-data")
                .queryParam("token", userToken)
                .toUriString();

        System.out.println(generateData);

        return restTemplate.getForObject(generateData, ChallengeModel.class);
    }

    public ChallengeModel solveChallenge(ChallengeModel challengeModel) {

        final CaesarCipher cipherMethod = new CaesarCipher(challengeModel.getNumeroCasas());
        final String deciphered = cipherMethod.decipher(challengeModel.getCifrado());

        challengeModel.setDecifrado(deciphered);
        challengeModel.setResumoCriptografico(digester.getAsHexString(deciphered));

        return challengeModel;
    }

    public String postSolution(ChallengeModel solvedChallenge) {

        final String submitSolution = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .pathSegment("challenge", "dev-ps", "submit-solution")
                .queryParam("token", userToken)
                .toUriString();


        return restTemplate.postForObject(submitSolution, createEntity(solvedChallenge), String.class);
    }

    private HttpEntity<LinkedMultiValueMap<String, Object>> createEntity(ChallengeModel solvedChallenge) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        final LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("answer", toResource(solvedChallenge));

        return new HttpEntity<>(body, httpHeaders);
    }

    private Object toResource(ChallengeModel solvedChallenge) {
        try {
            final Path answer = Files.createTempFile("answer", ".json");

            final byte[] bytes = new ObjectMapper().writeValueAsBytes(solvedChallenge);
            Files.write(answer, bytes);

            return new FileSystemResource(answer.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
