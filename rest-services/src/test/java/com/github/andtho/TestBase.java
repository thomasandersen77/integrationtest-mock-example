package com.github.andtho;

import com.github.andtho.config.AppConfiguration;
import com.github.andtho.util.HttpUtils;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfiguration.class})
public abstract class TestBase extends JerseyTest {
    protected static final int PORT_NUMBER = HttpUtils.dynamicPort();

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(
            WireMockConfiguration.wireMockConfig().port(PORT_NUMBER));

    public TestBase() {
        super(new GrizzlyTestContainerFactory());
        set(TestProperties.LOG_TRAFFIC, Boolean.TRUE);
    }

}
