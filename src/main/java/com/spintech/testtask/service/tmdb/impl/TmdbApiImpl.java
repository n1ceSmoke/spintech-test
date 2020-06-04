package com.spintech.testtask.service.tmdb.impl;

import com.spintech.testtask.service.tmdb.TmdbApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Service
@Slf4j
public class TmdbApiImpl implements TmdbApi {
    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;
    @Value("${tmdb.tv.popular}")
    private String tvPopular;
    @Value("${tmdb.person.popular}")
    private String personPopular;


    public String popularTVShows() throws IllegalArgumentException {
        try {
            String url = getTmdbUrl(tvPopular);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response
                    = restTemplate.getForEntity(url, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                return null;
            }

            return response.getBody();
        } catch (URISyntaxException e) {
            log.error("Couldn't get popular tv shows");
        }
        return null;
    }

    @Override
    public String getActors() {
        try {
            String url = getTmdbUrl(personPopular);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response
                    = restTemplate.getForEntity(url, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                return "Bad answer from tmdb api. Status code: " + response.getStatusCode();
            }

            return response.getBody();
        } catch (URISyntaxException e) {
            log.error("Couldn't get popular tv shows");
            return "URISyntaxException. Please check tmdb api documentation";
        }
    }

    private String getTmdbUrl(String tmdbItem) throws URISyntaxException {
        StringBuilder builder = new StringBuilder(tmdbApiBaseUrl);
        builder.append(tmdbItem);
        URIBuilder uriBuilder = new URIBuilder(builder.toString());
        uriBuilder.addParameter("language", tmdbLanguage);
        uriBuilder.addParameter("api_key", tmdbApiKey);
        return uriBuilder.build().toString();
    }
}
