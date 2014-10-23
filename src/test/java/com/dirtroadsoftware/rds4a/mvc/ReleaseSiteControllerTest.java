package com.dirtroadsoftware.rds4a.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 *
 */
public class ReleaseSiteControllerTest {
    @InjectMocks
    private ReleaseSiteController releaseSiteController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(releaseSiteController).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/bar")).andDo(print());
    }

}
