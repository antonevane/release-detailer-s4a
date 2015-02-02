package com.dirtroadsoftware.rds4a.rest.resources.asm;

import com.dirtroadsoftware.rds4a.core.models.entities.MaChemical;
import com.dirtroadsoftware.rds4a.core.models.entities.MaRelease;
import com.dirtroadsoftware.rds4a.core.models.entities.MaSource;
import com.dirtroadsoftware.rds4a.rest.mvc.MaReleaseController;
import com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assembles {@link com.dirtroadsoftware.rds4a.rest.resources.MaReleaseResource} from {@link com.dirtroadsoftware.rds4a.core.models.entities.MaRelease}.
 */
public class MaReleaseResourceAsm extends ResourceAssemblerSupport<MaRelease, MaReleaseResource> {

    public MaReleaseResourceAsm() {
        super(MaReleaseController.class, MaReleaseResource.class);
    }

    @Override
    public MaReleaseResource toResource(MaRelease release) {
        MaReleaseResource res = new MaReleaseResource();

        // Identifiers
        res.setRid(release.getId());
        res.setRtn(release.getRtn());
        res.setRegion(release.getRegion());
        res.setSite(release.getSite());

        // Name and location
        res.setSiteName(release.getSiteName());
        res.setAddress(release.getAddress());
        res.setTown(release.getTown());
        res.setZipCode(release.getZipCode());
        res.setLocations(release.getLocation());

        // Sources
        res.setNumSources(release.getNumSources());

        List<MaSource> sources = release.getSources();
        if (!sources.isEmpty()) {
            List<String> sourceNames = Lists.transform(sources, MaSourceToSource.INSTANCE);
            Joiner joiner = Joiner.on(", ");
            res.setSources(joiner.join(sourceNames));
        }

        List<MaChemical> chemicals = release.getChemicals();
        if (!chemicals.isEmpty()) {
            List<String> chemicalNames = Lists.transform(chemicals, MaChemicalToChemical.INSTANCE);
            Joiner joiner = Joiner.on(", ");
            res.setChemicals(joiner.join(chemicalNames));
        }
        // Status and actions
        res.setActive(release.getActive());
        res.setRaoClass(release.getRaoClass());
        res.setPhase(release.getPhase());

        res.setNumActions(release.getNumActions());

        // Dates
        res.setComplianceStatusDate(release.getStatusDate());
        res.setNotificationDate(release.getNotification());

        Link link = linkTo(MaReleaseController.class).slash(release.getId()).withSelfRel();
        res.add(link);

        Link actionsLink = linkTo(methodOn(MaReleaseController.class).findAllActions(release.getId())).withRel("actions");
        res.add(actionsLink);

        return res;
    }

    enum MaSourceToSource implements Function<MaSource, String> {
        INSTANCE;

        @Override
        public String apply(MaSource source) {
            return source.getSource();
        }
    }
    enum MaChemicalToChemical implements Function<MaChemical, String> {
        INSTANCE;

        @Override
        public String apply(MaChemical chemical) {
            return chemical.getChemical();
        }
    }
}
