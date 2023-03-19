package home.four.you;

import home.four.you.model.entity.*;
import home.four.you.repository.AdRepository;
import home.four.you.repository.LocationRepository;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import home.four.you.security.auth.authorization.AuthorityRole;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static home.four.you.TestUtil.generateId;
import static home.four.you.model.entity.Ad.Type.SALE;
import static home.four.you.model.entity.Equipment.*;
import static home.four.you.model.entity.Property.HeatType.WOOD;
import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_ADMIN;
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

    protected static final String APPLICATION_JSON = "application/json";

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
                .setType(SALE)
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
                .setRole(roleRepository.findByName(ROLE_ADMIN));
    }

    protected JSONObject createHouseAdJSON() throws JSONException {
        return createAdWithoutProperty()
                .put("property", houseJSON());
    }

    protected JSONObject createApartmentAdJSON() throws JSONException {
        return createAdWithoutProperty()
                .put("property", apartmentJSON());
    }

    private JSONObject createAdWithoutProperty() throws JSONException {
        return new JSONObject()
                .put("type", SALE.toString())
                .put("title", make())
                .put("description", make())
                .put("price", generateId());
    }

    private JSONObject houseJSON() throws JSONException {
        return propertyJSON()
                .put("house", new JSONObject()
                        .put("numberOfFloors", 2)
                        .put("courtyardArea", generateId()));
    }

    private JSONObject apartmentJSON() throws JSONException {
        return propertyJSON()
                .put("apartment", new JSONObject()
                        .put("floor", 2));
    }

    private JSONObject propertyJSON() throws JSONException {
        return new JSONObject()
                .put("heatType", WOOD.toString())
                .put("locationId", 2)
                .put("area", generateId())
                .put("numberOfRooms", generateId())
                .put("booked", true)
                .put("equipment", new JSONArray()
                        .put(TV.toString())
                        .put(WASHING_MACHINE.toString()));
    }

    protected List<Object> getEquipment(JSONArray array) throws JSONException {
        var retVal = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            retVal.add(array.get(i));
        }
        return retVal;
    }

}
