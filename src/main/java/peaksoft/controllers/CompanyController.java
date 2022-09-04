package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import peaksoft.model.Company;

import peaksoft.service.CompanyService;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyRepository) {
        this.companyService = companyRepository;
    }

    @GetMapping("/allCompany")
    public String getCompaniesPage(Model model){
        model.addAttribute("allCompany", companyService.getAllCompanies());
        return "company/companyMainPage";
    }


      @GetMapping("/new")
      public String newCompany(Model model){
        model.addAttribute("newCompany",new Company());
        return "company/newCompany";
      }

      @PostMapping("/save")
      public String saveCompany(@ModelAttribute("newCompany")Company company){
        companyService.save(company);
        return "redirect:/allCompany";
      }
//
//    @RequestMapping(value = "/edit/{id}")
//    public ModelAndView edit(@PathVariable Long id){
//        ModelAndView view = new ModelAndView("company/editCompany");
//        Company company = companyService.getById(id);
//        view.addObject(company);
//        return view;
//    }

    @GetMapping("/{id}/edit")
      public String editCompany(@PathVariable("id") Long id,Model model){
        model.addAttribute("company", companyService.getById(id));
        return "company/editCompany";

      }
      @PostMapping("/{id}/update")
      public String updateCompany(@ModelAttribute("company") Company company,@PathVariable Long id){
        companyService.updateCompany(id,company);
        return "redirect:/allCompany";
      }

      @PostMapping("{id}/delete")
      public String deleteCompany(@PathVariable("id") Long id){
        companyService.deleteCompany(id);
        return "redirect:/allCompany";

      }

}
