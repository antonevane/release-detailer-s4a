package com.dirtroadsoftware.rds4a.rest.mvc;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.services.MaActionService;
import com.dirtroadsoftware.rds4a.core.services.exceptions.MaActionNotFoundException;
/**
 *
 */
public class MaActionControllerTest {
    /** Mock service primed with responses */
    @Mock
    private MaActionService service;

    /** Controller where mock service is injected */
    @InjectMocks
    private MaActionController controller;

    // Spring MockMVC
    private MockMvc mockMvc;

    @Before
    public void setup() {
        // Mockito initialization
        MockitoAnnotations.initMocks(this);

        // Spring MockMVC initialization
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getExistingAction() throws Exception {
        MaAction action = new MaAction();
        action.setId(123L);
        action.setDate(new LocalDate("2008-12-05").toDate());
        action.setStatus("ISSUED");
        action.setAction("NOR");
        action.setRtn("1-001111");

        when(service.findActionById(anyLong())).thenReturn(action);

        mockMvc.perform(get("/rest/actions/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rid", is(123)))
                .andExpect(jsonPath("$.date", is("2008-12-05")))
                .andExpect(jsonPath("$.status", is(action.getStatus())))
                .andExpect(jsonPath("$.rtn", is(action.getRtn())))
                .andExpect(jsonPath("$.action", is(action.getAction())))
                .andExpect(jsonPath("$.links[0].href", endsWith("actions/123")))
                .andExpect(jsonPath("$.links[0].rel", is(Link.REL_SELF)))
                ;

    }

    @Test
    public void getNonExistingAction() throws Exception {
        when(service.findActionById(anyLong())).thenThrow(new MaActionNotFoundException());

        mockMvc.perform(get("/rest/actions/123"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }
}
