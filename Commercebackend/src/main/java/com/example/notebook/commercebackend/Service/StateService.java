package com.example.notebook.commercebackend.Service;

import com.example.notebook.commercebackend.Dao.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.notebook.commercebackend.entity.State;
import java.util.List;


@Service
public class StateService {

    private final StateRepository stateRepository;

    @Autowired
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<State> getStatesByCountryCode(String code) {
        return stateRepository.findByCountryCode(code);
    }

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }
}
