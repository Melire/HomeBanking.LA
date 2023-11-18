package com.lamlvbank.homebanking.controller_test;

import com.lamlvbank.homebanking.model.AccountType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountTypeWebClient_test {
    @Autowired
 WebTestClient client;
    @Test
    public void findAllAccountTypeTest() {
        client.get().uri("/apiHB/accTypes").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(AccountType[].class)
                .consumeWith(response->{
                    List<AccountType>acctypes= Arrays.asList(response.getResponseBody());
                    assertFalse(acctypes.isEmpty());
                    assertEquals(5, acctypes.size());
        });

    }


}
