package com.openfinledger.openfinledger.controller;

import com.openfinledger.openfinledger.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class AccountControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAccount() {
        Map<String, String> request = Map.of(
                "ownerName", "Bob",
                "currency", "CNY"
        );

        ResponseEntity<Account> response = restTemplate.postForEntity(
                "/api/v1/accounts",
                request,
                Account.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().ownerName()).isEqualTo("Bob");
    }

    @Test
    void shouldRejectBlankOwnerName() {
        Map<String, String> request = Map.of(
                "ownerName", "",
                "currency", "CNY"
        );

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/v1/accounts",
                request,
                String.class
        );

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }}