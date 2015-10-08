package com.github.andtho.resources;

import com.github.andtho.config.PropertyReader;
import com.github.andtho.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Slf4j
@Service
public class PersonService {

    @Autowired
    PropertyReader propertyReader;

    private Client client = ClientBuilder.newClient();

    public Person getPerson(String ssn) {
        String personInfoUrl = propertyReader.getProperty("personinfo.service.url");
        String url = personInfoUrl + "/" + ssn;
        log.info("Get person with id: [{}]. PersoninfoService Url: [{}]", ssn, url);
        return client.target(url)
                .request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(Person.class);
    }
}
