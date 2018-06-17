package jSimpleDB.Application.Controllers;

import jSimpleDB.Application.Repositories.IndividualsRepository;
import jSimpleDB.DBModel.Models.IndividualModels.Individual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/individuals")
public class IndividualController {
    @Autowired
    private IndividualsRepository individualsRepository;

    @GetMapping
    public ResponseEntity<Iterable<Individual>> getAll() {

        if (individualsRepository.count() == 0) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(individualsRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Individual> getById(@PathVariable("id") String id) {
        Optional<Individual> individual = individualsRepository.findById(id);
        return individual.isPresent() ? new ResponseEntity<>(individual.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity<?> add(@RequestBody Individual individual) {

        Individual newIndividual = individualsRepository.save(individual);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newIndividual.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(newIndividual, headers, HttpStatus.CREATED);
//        return ResponseEntity.created(location).build();

//        return this.individualsRepository
//                .findById(individual.getId())
//                .map(account -> {
//                    Bookmark result = bookmarkRepository.save(new Bookmark(account,
//                            input.getUri(), input.getDescription()));
//
//                    URI location = ServletUriComponentsBuilder
//                            .fromCurrentRequest().path("/{id}")
//                            .buildAndExpand(result.getId()).toUri();
//
//                    return ResponseEntity.created(location).build();
//                })
//                .orElse(ResponseEntity.noContent().build());

    }

//    @PostMapping
//    public ResponseEntity<Void> postMessage(@RequestBody Individual individual, UriComponentsBuilder ucBuilder) {
//
//        try {
//            Individual newIndividual = individualsRepository.save(individual);
//            return ResponseEntity.created(ucBuilder.path("/individuals/"+newIndividual.getId()).toString()).build();
//        } catch (ResourceAlreadyExistException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        } catch (URISyntaxException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//
//        messagesRepository.save(individual);
//        return new ResponseEntity<>(individual, HttpStatus.CREATED);
//        RequestHeader.
//                HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder. path("/user/{id}").buildAndExpand(individual.getId()).toUri());
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Individual> delete(@PathVariable("id") String id) {
        Optional<Individual> individual = individualsRepository.findById(id);
        if (!individual.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            individualsRepository.delete(individual.get());
            return new ResponseEntity<>(individual.get(), HttpStatus.OK);
        }
    }

//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Individual> update(@PathVariable("id") String id, @RequestBody Individual individual) {
//        Optional<Individual> currentIndividual = individualsRepository.findById(id);
//
//        if (!currentIndividual.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        currentIndividual.setName(individual.getName());
//        currentIndividual.setAge(individual.getAge());
//        currentIndividual.setSalary(individual.getSalary());
//
//        individualsRepository.save() updateUser(currentIndividual);
//        return new ResponseEntity<User>(currentIndividual, HttpStatus.OK);
//    }
}
