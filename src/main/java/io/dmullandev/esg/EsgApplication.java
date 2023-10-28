package io.dmullandev.esg;

import static io.dmullandev.esg.constants.EsgAppConstants.ENDPOINTURL_CUSTOMER_CONTROLLER_V1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import io.dmullandev.esg.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EsgApplication {

    @Autowired
    @Value("${esg.csv.file.path}")
    private String datasource;

    @Autowired
    @Value("${esg.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EsgApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void readCsv() throws IOException {
        Stream<String> lines = Files.lines(Paths.get(datasource));

        List<Customer> customers = lines.map(this::buildCustomer).toList();

        customers.forEach(customer -> restTemplate.postForLocation(apiUrl + ENDPOINTURL_CUSTOMER_CONTROLLER_V1, customer));
        lines.close();
    }

    private Customer buildCustomer(String line) {
        String[] lineSplit = line.split(",");
        return Customer.builder()
                       .customerRef(lineSplit[0])
                       .customerName(lineSplit[1])
                       .addressLine1(lineSplit[2])
                       .addressLine2(lineSplit[3])
                       .town(lineSplit[4])
                       .county(lineSplit[5])
                       .country(lineSplit[6])
                       .postcode(lineSplit[7])
                       .build();
    }
}
