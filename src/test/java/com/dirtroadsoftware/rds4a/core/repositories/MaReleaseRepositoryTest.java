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

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MaReleaseRepositoryTest {
    @Autowired
    private MaReleaseRepository repository;

    @Test
    public void findMaReleaseByRegionSite() throws Exception {
        MaRelease release = repository.findMaReleaseByRegionSite(1, 12345);
        assertNotNull(release);
        assertEquals(1, release.getRegion());
        assertEquals(12345, release.getSite());
        assertEquals("1-0012345", release.getRtn());
    }

    @Test
    public void findTownsWithReleasesByRegion() throws Exception {
        List<String> towns = repository.findTownsWithReleasesByRegion(2);
        assertNotNull(towns);
        assertEquals(77, towns.size());
    }

    @Test
    public void findMaReleasesByTown() throws Exception {
        List<MaRelease> releases = repository.findMaReleasesByTown("ORANGE");
        assertNotNull(releases);
        assertEquals(69, releases.size());
    }

    @Test
    public void actionsForMaRelease() throws Exception {
        MaRelease release = repository.findMaReleaseWithActionsByRegionSite(1, 12345);
        assertNotNull(release);
        Date lastDate = null;
        List<MaAction> actions = release.getActions();
        for (MaAction action : actions) {
            Date nextDate = action.getDate();
            if (lastDate == null) {
                lastDate = nextDate;
            } else {
                boolean before = lastDate.before(nextDate);
                boolean same = lastDate.equals(nextDate);
                assertTrue(lastDate + " is not before or equal to " + nextDate, before || same);
                lastDate = nextDate;
            }
        }
    }

    @Test
    public void findExistingMaRelease() throws Exception {
        MaRelease release = repository.findMaRelease(1L);
        assertNotNull(release);
        assertEquals("ADAMS LANDFILL", release.getSiteName());
    }

    @Test
    public void findExistingMaReleaseWithActions() throws Exception {
        MaRelease release = repository.findMaReleaseWithActions(1L);
        assertNotNull(release);
        assertEquals("ADAMS LANDFILL", release.getSiteName());

        PersistenceUtil util = Persistence.getPersistenceUtil();
        assertTrue(util.isLoaded(release, "actions"));
        assertEquals(4, release.getActions().size());
    }


    @Test
    public void findNonExistingMaRelease() throws Exception {
        MaRelease release = repository.findMaRelease(0L);
        assertNull(release);
    }

    @Test
    public void getNumActions() throws Exception {
        MaRelease release = repository.findMaReleaseWithActionsByRegionSite(1, 12345);
        // Check to see that the reference to Actions is not "hollow"
        PersistenceUtil util = Persistence.getPersistenceUtil();
        assertTrue(util.isLoaded(release, "actions"));
        assertEquals(6, release.getActions().size());
    }


    @Test
    public void findMaReleasesByTownWithPagination() throws Exception {
        List<MaRelease> releases = repository.findMaReleasesByTown("TOWNSEND", "notification", "DESC", 11, 15);

        assertEquals(15, releases.size());
        assertEquals(java.sql.Date.valueOf("1999-12-14"), releases.get(3).getNotification());
    }

    @Test
    public void countMaReleases() throws Exception {
        String town = "TOWNSEND";
        Long count = repository.countMaReleases("town", town);

        assertEquals(36l, count.longValue());
    }
}
