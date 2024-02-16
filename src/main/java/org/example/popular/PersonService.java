package org.example.popular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<Person> getPopularPersons(int page, String language){
        String url = "https://api.themoviedb.org/3/person/popular?api_key=" + tmdbApiKey + "&language=" + language + "&page=" + page;
        ResponseEntity<PersonResponse> response = restTemplate.getForEntity(url, PersonResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResults();
        }else {
            return null;
        }
    }

}
