package group.thirtyone.persistencerepositories;

import group.thirtyone.surveycomponents.OpenEnded;
import org.springframework.data.repository.CrudRepository;
public interface OpenEndedRepository extends CrudRepository<OpenEnded, Long> {
}