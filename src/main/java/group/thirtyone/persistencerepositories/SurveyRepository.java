package group.thirtyone.persistencerepositories;

import group.thirtyone.surveycomponents.Survey;
import org.springframework.data.repository.CrudRepository;
public interface SurveyRepository extends CrudRepository<Survey, Long>{
    Survey findById(long id);
}