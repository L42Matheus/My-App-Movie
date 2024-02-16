package org.example.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SearchMoviesService {

    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Autowired
    private RestTemplate restTemplate;


    public List<KnownFor> getSearchMovies(String query){
        String url = "https://api.themoviedb.org/3/search/movie?query=" + query + "&api_key=" + tmdbApiKey +"&language=pt-BR";
        ResponseEntity<SearchMoviesResponse> response = restTemplate.getForEntity(url, SearchMoviesResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getResults();
        }else {
            return  null;
        }
    }
}
