package com.dirtroadsoftware.rds4a.core.repositories;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MaActionRepositoryTest {
    @Autowired
    private MaActionRepository repository;

    @Test
    @Transactional
    public void findExsitingMaAction() throws Exception {
        MaAction action = repository.findOne(44025L); // RTN 1-12345
        assertNotNull(action);
        assertEquals("1-0012345", action.getRelease().getRtn());
    }


    @Test
    public void findMaActionsByTown() throws Exception {
        String town = "Bolton";
        String sortBy = "date";
        String sortHow = "ASC";
        List<MaAction> actions = repository.findMaActionsByTown(town, sortBy, sortHow, 5, 10);

        assertEquals(10, actions.size());
        assertEquals("RLFA", actions.get(5).getAction());
        assertEquals("2-0010351", actions.get(5).getRelease().getRtn());
    }

    @Test
    public void findMaActionsByDate() throws Exception {
        String date = "2013-06-03";
        String sortBy = "raoClass";
        String sortHow = "ASC";
        List<MaAction> actions = repository.findMaActionsByDate(date, sortBy, sortHow, 0, 8);

        assertEquals(8, actions.size());

        // Check to see that the reference to Actions is not "hollow"
        PersistenceUtil util = Persistence.getPersistenceUtil();
        assertTrue(util.isLoaded(actions.get(7), "release"));

        assertEquals("A2", actions.get(7).getRelease().getRaoClass());
        assertEquals("4-0024584", actions.get(7).getRelease().getRtn());
    }

    @Test
    public void countMaActionsForTown() throws Exception {
        String town = "ORANGE";
        Long count = repository.countMaActions("release.town", town);

        assertEquals(1105l, count.longValue());
    }
}
