package group.thirtyone.surveycomponents;

import group.thirtyone.othercomponents.UserAccount;
import group.thirtyone.persistencerepositories.UserAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@DataJpaTest
public class UserAccountTest {

    @Autowired
    UserAccountRepository userAccountRepository;

    @BeforeEach
    void setUp() {
        UserAccount user1 = new UserAccount("user1", "pass1");
        UserAccount user2 = new UserAccount("user2", "pass2");

        userAccountRepository.save(user1);
        userAccountRepository.save(user2);
    }

    @AfterEach
    void tearDown() {
        userAccountRepository.deleteAll();
    }

    @Test
    void createNewAccount() {
        UserAccount userAccount = new UserAccount("user3", "pass3");

        //I can't figure out how to test Spring controller methods directly.
        //Below is the exact code from the create account method, which will be tested instead.
        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userAccount.getUsername())) {
                //return "create_account_failure";
                fail();
            }
        }

        //model.addAttribute("userAccount", userAccount);
        userAccountRepository.save(userAccount);
        //return "create_account_success";

        Optional<UserAccount> result = userAccountRepository.findById(userAccount.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void createExistingAccount() {
        UserAccount userAccount = new UserAccount("user2", "pass2");
        boolean flag = false;

        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userAccount.getUsername())) {
                //return "create_account_failure";
                flag = true;
            }
        }

        //model.addAttribute("userAccount", userAccount);
        //userAccountRepository.save(userAccount);
        //return "create_account_success";
        if (!flag) {
            fail();
        }
    }

    @Test
    void logInExisting() {
        UserAccount userInfo = new UserAccount("user1", "pass1");
        boolean flag = false;

        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userInfo.getUsername())) {
                if (user.getPassword().equals(userInfo.getPassword())) {
                    //session.setAttribute("loggedIn", true);
                    //session.setAttribute("username", userInfo.getUsername());
                    //model.addAttribute("userInfo", userInfo);
                    //return "login_success";
                    flag = true;
                }
            }
        }
        //return "login_failure";
        if (!flag) {
            fail();
        }
    }

    @Test
    void logInNotExisting() {
        UserAccount userInfo = new UserAccount("user3", "pass3");

        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userInfo.getUsername())) {
                if (user.getPassword().equals(userInfo.getPassword())) {
                    //session.setAttribute("loggedIn", true);
                    //session.setAttribute("username", userInfo.getUsername());
                    //model.addAttribute("userInfo", userInfo);
                    //return "login_success";
                    fail();
                }
            }
        }
        //return "login_failure";
        assertTrue(true);
    }
}
