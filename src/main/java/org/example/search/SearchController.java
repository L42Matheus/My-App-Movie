package org.example.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private SearchMoviesService searchMoviesService;


    @GetMapping("/movie")
    public ResponseEntity<List<KnownFor>> getSearchMovies(@RequestParam String query){

        List<KnownFor> knownFor = searchMoviesService.getSearchMovies(query);

        if (knownFor != null && !knownFor.isEmpty()) {
            return ResponseEntity.ok(knownFor);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
