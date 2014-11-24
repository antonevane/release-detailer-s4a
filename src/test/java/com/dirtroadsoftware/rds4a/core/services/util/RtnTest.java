package com.dirtroadsoftware.rds4a.core.services.util;

import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class RtnTest {
    @Test
    public void rtnValid() throws Exception {
        Rtn rtn = Rtn.parseRtn("01-012345");
        assertNotNull(rtn);
        assertEquals(12345, rtn.getSite());
        assertEquals(1, rtn.getRegion());
    }

    @Test(expected = ParseException.class)
    public void rtnInvalid() throws Exception {
        Rtn.parseRtn("01#012345");
    }

    @Test
    public void format() throws Exception {
        String formattedRtn = Rtn.format(1, 12345);
        assertEquals("01-0012345", formattedRtn);
    }

}
