package jSimpleDB.Application.Repositories;

import jSimpleDB.DBModel.Models.OrganizationModels.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationsRepository extends CrudRepository<Organization, String> {
}
