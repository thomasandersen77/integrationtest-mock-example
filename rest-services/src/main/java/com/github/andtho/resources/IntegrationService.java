package com.github.andtho.resources;

import com.github.andtho.config.AppConfiguration;
import com.github.andtho.json.StubMapping;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IntegrationService {

    @Inject
    private AppConfiguration config;

    @Inject
    @Setter
    private Client client;

    public Optional<StubMapping> getMapping(String customerId) {
        String mapping_url = config.get_mapping_url() + "/" + customerId;
        log.info("Get mapping with id: [{}]. Url: [{}]", customerId, mapping_url);
        StubMapping value = client.target(mapping_url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<StubMapping>(){});
        return Optional.ofNullable(value);
    }

    public Optional<List<StubMapping>> getMappinList(){
        String mapping_url = config.get_mapping_url();
        log.info("Get mapping list. Url: [{}]", mapping_url);
        List<StubMapping> list = client.target(
                mapping_url)
                .request().buildGet().invoke(new GenericType<List<StubMapping>>(){});
        return Optional.ofNullable(list);
    }
}
