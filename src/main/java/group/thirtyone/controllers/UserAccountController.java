package group.thirtyone.controllers;

import group.thirtyone.othercomponents.UserAccount;
import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.persistencerepositories.UserAccountRepository;
import group.thirtyone.surveycomponents.MultipleChoice;
import group.thirtyone.surveycomponents.NumberRange;
import group.thirtyone.surveycomponents.OpenEnded;
import group.thirtyone.surveycomponents.Survey;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        //Optional<Survey> result = surveyRepository.findById(id);
        model.addAttribute("username", session.getAttribute("username"));
        UserAccount currentUser = userAccountRepository.findByUsername(session.getAttribute("username").toString());

        ArrayList<List<NumberRange>> totalNumberRanges = new ArrayList<>();
        ArrayList<List<MultipleChoice>> totalMultipleChoices = new ArrayList<>();
        ArrayList<List<OpenEnded>> totalOpenEndeds = new ArrayList<>();
        ArrayList<ArrayList<Map<String,Integer>>> mcqmaplistlist = new ArrayList<>();
        ArrayList<ArrayList<Map<String,Integer>>> nrmaplistlist = new ArrayList<>();

        for (Survey survey : currentUser.getSurveys()) {
            List<NumberRange> numberRanges = survey.getNumberRangeQuestions();
            List<MultipleChoice> multipleChoices = survey.getMultipleChoiceQuestions();
            List<OpenEnded> openEndeds = survey.getOpenEndedQuestions();

            ArrayList<Map<String,Integer>> mcqmaplist = new ArrayList<>();
            for (MultipleChoice mcq : multipleChoices) {
                Map<String,Integer> mcqmap = new HashMap<>();
                for (String choice : mcq.getChoices()) {
                    mcqmap.put(choice, Collections.frequency(mcq.getAnswers(), choice));
                }
                mcqmaplist.add(mcqmap);
            }

            mcqmaplistlist.add(mcqmaplist);

            ArrayList<Map<String,Integer>> nrmaplist = new ArrayList<>();

            for (NumberRange nr : numberRanges) {
                HashSet<String> nrAnswers = new HashSet<>(nr.getAnswers());
                Map<String,Integer> nrmap = new HashMap<>();
                for (String choice : nrAnswers) {
                    nrmap.put(choice, Collections.frequency(nr.getAnswers(), choice));
                }
                nrmaplist.add(nrmap);
            }

            nrmaplistlist.add(nrmaplist);

            totalOpenEndeds.add(openEndeds);
            totalMultipleChoices.add(multipleChoices);
            totalNumberRanges.add(numberRanges);
        }
        model.addAttribute("numberranges", totalNumberRanges);
        model.addAttribute("openendeds", totalOpenEndeds);
        model.addAttribute("multiplechoices", totalMultipleChoices);
        model.addAttribute("mcqmaplists", mcqmaplistlist);
        model.addAttribute("nrmaplists", nrmaplistlist);
        model.addAttribute("surveys", currentUser.getSurveys());

        return "userdash";
    }
}
