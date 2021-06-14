package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnersControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnersController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() {
        when(ownerService.findAll()).thenReturn(owners);

        try {
            mockMvc.perform(get("/owners")).andExpect(status().isOk())
            .andExpect(view().name("owners/index"))
            .andExpect(model().attribute("owners",hasSize(2)));//200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void listOwnersByIndex() {
        when(ownerService.findAll()).thenReturn(owners);

        try {
            mockMvc.perform(get("/owners/index")).andExpect(status().isOk())
                    .andExpect(view().name("owners/index"))
                    .andExpect(model().attribute("owners",hasSize(2)));//200
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findOwners() {
        try {
            mockMvc.perform(get("/owners/find")).andExpect(view().name("notImplemented"));

            verifyNoMoreInteractions(ownerService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}