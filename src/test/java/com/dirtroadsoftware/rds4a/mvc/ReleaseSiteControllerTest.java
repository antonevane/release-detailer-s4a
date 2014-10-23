package com.dirtroadsoftware.rds4a.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        mockMvc.perform(post("/foo")
                .content("{\"region\":1,\"site\":12345}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.region", is(1)))
                .andExpect(jsonPath("$.site", is(12345)))
                .andDo(print());
    }

}
