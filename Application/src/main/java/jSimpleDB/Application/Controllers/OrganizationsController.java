package jSimpleDB.Application.Controllers;

import jSimpleDB.Application.Repositories.OrganizationsRepository;
import jSimpleDB.DBModel.Models.IndividualModels.Individual;
import jSimpleDB.DBModel.Models.OrganizationModels.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/organizations")
public class OrganizationsController {
    @Autowired
    private OrganizationsRepository organizationsRepository;

    @GetMapping
    public ResponseEntity<Iterable<Organization>> getAll() {

        if (organizationsRepository.count() == 0) return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(organizationsRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Organization> getById(@PathVariable("id") String id) {
        Optional<Organization> organization = organizationsRepository.findById(id);
        return organization.isPresent() ? new ResponseEntity<>(organization.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    ResponseEntity<?> add(@RequestBody Organization organization) {

        Organization newOrganization = organizationsRepository.save(organization);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOrganization.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(newOrganization, headers, HttpStatus.CREATED);
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
    public ResponseEntity<Organization> delete(@PathVariable("id") String id) {
        Optional<Organization> organization = organizationsRepository.findById(id);
        if (!organization.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            organizationsRepository.delete(organization.get());
            return new ResponseEntity<>(organization.get(), HttpStatus.OK);
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
