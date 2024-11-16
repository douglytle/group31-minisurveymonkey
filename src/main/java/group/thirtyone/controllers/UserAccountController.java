package group.thirtyone.controllers;

import group.thirtyone.othercomponents.UserAccount;
import group.thirtyone.persistencerepositories.UserAccountRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserAccountController {

    @Autowired
    UserAccountRepository userAccountRepository;

    @GetMapping("/login")
    public String viewLogin(Model model, HttpSession session) {
        model.addAttribute("userInfo", new UserAccount());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserAccount userInfo, Model model, HttpSession session) {

        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userInfo.getUsername())) {
                if (user.getPassword().equals(userInfo.getPassword())) {
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("username", userInfo.getUsername());
                    model.addAttribute("userInfo", userInfo);
                    return "login_success";
                }
            }
        }
        return "login_failure";
    }

    @GetMapping("/create_account")
    public String viewCreateAccount(Model model, HttpSession session) {
        model.addAttribute("userAccount", new UserAccount());
        return "create_account";
    }

    @PostMapping("/create_account")
    public String createAccount(@ModelAttribute UserAccount userAccount, Model model, HttpSession session) {

        Iterable<UserAccount> userAccountIterable = userAccountRepository.findAll();
        for (UserAccount user : userAccountIterable) {
            if (user.getUsername().equals(userAccount.getUsername())) {
                return "create_account_failure";
            }
        }

        model.addAttribute("userAccount", userAccount);
        userAccountRepository.save(userAccount);
        return "create_account_success";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "logout";
    }
}
