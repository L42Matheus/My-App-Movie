package org.example.popular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/popular")
    public ResponseEntity<List<Person>> getPopularPersons(@RequestParam int page,
                                                         @RequestParam String language){

        List<Person> popularPersons = personService.getPopularPersons(page, language);

        if (popularPersons != null && !popularPersons.isEmpty()) {
            return ResponseEntity.ok(popularPersons);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
