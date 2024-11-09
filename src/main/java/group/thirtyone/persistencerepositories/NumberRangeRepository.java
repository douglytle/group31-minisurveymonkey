package group.thirtyone.persistencerepositories;

import group.thirtyone.surveycomponents.NumberRange;
import org.springframework.data.repository.CrudRepository;
public interface NumberRangeRepository extends CrudRepository<NumberRange, Long> {
}