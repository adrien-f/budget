package com.adrienf.budget.web;

import com.adrienf.budget.domain.Operation;
import com.adrienf.budget.service.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    protected OperationRepository operationRepository;

    @RequestMapping("/")
    public String dashboard(Model model) {
        Iterable<Operation> all = operationRepository.findAll();
        model.addAttribute("operations", all);
        return "dashboard";
    }
}
