package group.thirtyone.persistencerepositories;

import group.thirtyone.surveycomponents.MultipleChoice;
import org.springframework.data.repository.CrudRepository;
public interface MultipleChoiceRepository extends CrudRepository<MultipleChoice, Long> {
}