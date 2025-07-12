package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private static List<Country> COUNTRY_LIST;

    @PostConstruct
    public void initCountryList() {
        LOGGER.info("START - initCountryList() method");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Map<String, Country> countryBeans = context.getBeansOfType(Country.class);
            COUNTRY_LIST = new ArrayList<>(countryBeans.values());
            LOGGER.debug("Loaded countries: {}", COUNTRY_LIST);
        } catch (Exception e) {
            LOGGER.error("Error initializing country list from XML: {}", e.getMessage());
            COUNTRY_LIST = new ArrayList<>();
        }

        LOGGER.info("END - initCountryList() method");
    }

    public Country getCountry(String code) {
        LOGGER.info("START - getCountry({}) method", code);

        if (COUNTRY_LIST == null || COUNTRY_LIST.isEmpty()) {
            LOGGER.warn("Country list not initialized or empty. Attempting to re-initialize.");
            initCountryList();
            if (COUNTRY_LIST.isEmpty()) {
                LOGGER.error("Country list is still empty after re-initialization. Cannot find country.");
                throw new CountryNotFoundException();
            }
        }

        Country foundCountry = COUNTRY_LIST.stream()
                .filter(country -> country.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);

        LOGGER.debug("Found country: {}", foundCountry);
        LOGGER.info("END - getCountry({}) method", code);
        return foundCountry;
    }

    public List<Country> getAllCountries() {
        return COUNTRY_LIST;
    }
}
