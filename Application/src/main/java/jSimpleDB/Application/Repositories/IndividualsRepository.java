package jSimpleDB.Application.Repositories;

import jSimpleDB.DBModel.Models.IndividualModels.Individual;
import org.springframework.data.repository.CrudRepository;

public interface IndividualsRepository extends CrudRepository<Individual, String> {
}
