package com.app.identiticoders.externals.nasa.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class NasaConfigurationTest {

    @MockBean
    private NasaService nasaConfiguration;

    @Test
    public void getTest() {
    }
}