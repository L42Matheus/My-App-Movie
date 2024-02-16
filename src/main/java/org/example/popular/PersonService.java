package org.example.popular;

import org.example.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.lang.model.UnknownEntityException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class PersonService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${tmdb.baseurl}")
    private String baseurl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PersonRepository repository;

    public List<Person> getPopularPersons(int page, String language) {
        String url = baseurl + "?api_key=" + tmdbApiKey + "&language=" + language + "&page=" + page;

        ResponseEntity<PersonResponse> response = restTemplate.getForEntity(url, PersonResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            List<Person> persons = Objects.requireNonNull(response.getBody()).getResults();

            if (nonNull(persons) && !persons.isEmpty()) {
                repository.saveAll(persons);
                return persons;
            }

            throw new EntityNotFoundException("Lista vazia ou nula");

        }

        throw new RuntimeException("Ocorreu um erro ao tentar chamar API externa!");
    }
}
