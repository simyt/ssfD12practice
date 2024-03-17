package sg.nus.iss.d12practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class rdmGenerator {

    @GetMapping(path = "/")
    public String getRandom(Model model) {
        Random rdm = new Random();
        int n = rdm.nextInt(31);

        String imgUrl = "numbers/numbers/number%d.jpg".formatted(n);
        model.addAttribute("imgUrl", imgUrl);

        return "index";
    }

    @GetMapping(path = "/random")
    public String viewList (Model model) {
        return "random.html";
    }
    
    @PostMapping(path = "/random")
    public String getList(@RequestParam Integer n, Model model) {
        Random rdm = new Random();
        Set<Integer> uniqueNumbers = new HashSet<>(); //to prevent repeated numbers
        
        // Generate unique random numbers
        while (uniqueNumbers.size() < n) {
            int randomNumber = rdm.nextInt(30); // Generate random number between 0 and 29
            uniqueNumbers.add(randomNumber);
        }
        // for (int i : uniqueNumbers) {
        //     System.out.println(i);
        // }
        

        // Convert set to list for easier manipulation
        List<Integer> numbers = new ArrayList<>(uniqueNumbers);
        //System.out.println(numbers);
        // Shuffle the list to randomize the order
        Collections.shuffle(numbers);

        model.addAttribute("numbers", numbers);
        
        return "random.html";
    }
    
}
