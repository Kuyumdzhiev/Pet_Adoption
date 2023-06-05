package com.example.service;

import com.example.persistence.binding.ShelterAddBindingModel;
import com.example.persistence.entities.SheltersEntity;
import com.example.persistence.view.AddShelterViewModel;

import java.util.List;

public interface ShelterService {
    AddShelterViewModel getAddShelterViewModel();
    String addShelter(ShelterAddBindingModel shelterViewModel);
    AddShelterViewModel getShelterForEditing(long id);
    String updateShelter(long id, ShelterAddBindingModel shelterViewModel);
}