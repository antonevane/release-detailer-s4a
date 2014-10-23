package com.dirtroadsoftware.rds4a;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

/**
 *
 */
public class CalculaterTest {
    @Mock
    private Calculator calc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAbs() {
        when(calc.abs(-20)).thenReturn(-20);
        assertEquals(20, calc.abs(-20));
    }
}
