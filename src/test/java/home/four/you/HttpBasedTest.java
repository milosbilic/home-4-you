package home.four.you;

import home.four.you.model.entity.*;
import home.four.you.repository.AdRepository;
import home.four.you.repository.LocationRepository;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static home.four.you.TestUtil.generateId;
import static home.four.you.model.entity.Equipment.*;
import static java.util.Collections.singletonList;
import static net.bytebuddy.utility.RandomString.make;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpBasedTest {

    @LocalServerPort
    int port;

    static {
        RestAssuredConfig.config().getLogConfig()
                .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    protected static final String ADS_URI = "/ads";
    protected static final String AD_URI = "/ads/{id}";

    @Autowired
    protected AdRepository adRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    protected String url(String path) {
        String URL_TEMPLATE = "http://localhost:%s%s";

        return String.format(URL_TEMPLATE, port, path);
    }

    protected HttpHeaders defaultHeaders() {
        var headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    protected Ad createRandomAd() {
        return adRepository.save(new Ad()
                .setTitle(make())
                .setDescription(make())
                .setType(Ad.Type.SALE)
                .setExpirationDate(Instant.now().plus(90, ChronoUnit.DAYS))
                .setPrice(generateId().intValue())
                .setOwner(createUser())
                .setProperty(new Property()
                        .setEquipment(Set.of(TV, BALCONY, FREEZER, FRIDGE))
                        .setBooked(true)
                        .setHeatType(Property.HeatType.GAS)
                        .setArea(generateId().intValue())
                        .setNumberOfRooms(3.5)
                        .setLocation(createLocation())
                        .setHouse(new House()
                                .setCourtyardArea(generateId().intValue())
                                .setNumberOfFloors(3))));
    }

    private Location createLocation() {
        return locationRepository.save(new Location()
                .setName(make())
                .setZipCode(make()));
    }

    private User createUser() {
        return userRepository.save(new User()
                        .setEmail(make())
                        .setFirstName(make())
                        .setLastName(make())
                        .setPassword(make())
                        .setPhone(make()))
                .setRoles(Set.of(roleRepository.findByRole(Role.AuthorityRole.ROLE_ADMIN)));
    }


}
