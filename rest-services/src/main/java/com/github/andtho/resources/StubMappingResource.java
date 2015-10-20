package com.github.andtho.resources;

import com.github.andtho.config.HttpEnpoint;
import com.github.andtho.json.StubMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NoContentException;
import java.util.List;
import java.util.Optional;

@Slf4j
@HttpEnpoint
@Path("mapping")
@Produces("application/json")
public class StubMappingResource {

    @Autowired
    private IntegrationService integrationService;

    @GET
    @Path("{mappingId}")
    public StubMapping getMapping(@PathParam("mappingId") String id) throws NoContentException {
        log.info("GET mapping. ID={}", id);
        Optional<StubMapping> mapping = integrationService.getMapping(id);
        if(mapping.isPresent()) {
            return mapping.get();
        } else {
            throw new NoContentException("No mappings with id = " + id);
        }
    }


    @GET
    public List<StubMapping> getSubMappingList() throws NoContentException {
        Optional<List<StubMapping>> mappings = integrationService.getMappinList();
        if(mappings.isPresent()) {
            return mappings.get();
        } else {
            throw new NoContentException("No mappings");
        }
    }
}
