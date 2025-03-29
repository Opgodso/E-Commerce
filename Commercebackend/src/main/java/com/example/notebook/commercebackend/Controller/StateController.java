package com.example.notebook.commercebackend.Controller;

import com.example.notebook.commercebackend.Service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.notebook.commercebackend.entity.State;
import java.util.List;

@RestController
@RequestMapping("/states")
@CrossOrigin("http://localhost:4200")
public class StateController {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping
    public List<State> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping("/byCountryCode")
    public List<State> getStatesByCountryCode(@RequestParam String code) {
        return stateService.getStatesByCountryCode(code);
    }
}

