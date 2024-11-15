package group.thirtyone.persistencerepositories;

import group.thirtyone.othercomponents.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>{
}