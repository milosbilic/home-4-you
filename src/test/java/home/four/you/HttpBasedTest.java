package home.four.you;

import home.four.you.model.entity.Ad;
import home.four.you.model.entity.House;
import home.four.you.model.entity.Location;
import home.four.you.model.entity.Property;
import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.repository.LocationRepository;
import home.four.you.repository.RoleRepository;
import home.four.you.repository.UserRepository;
import home.four.you.security.TokenProvider;
import home.four.you.security.auth.authorization.AuthorityRole;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.Set;

import static home.four.you.TestUtil.generateId;
import static home.four.you.model.entity.Ad.Type.SALE;
import static home.four.you.model.entity.Equipment.BALCONY;
import static home.four.you.model.entity.Equipment.FREEZER;
import static home.four.you.model.entity.Equipment.FRIDGE;
import static home.four.you.model.entity.Equipment.TV;
import static home.four.you.model.entity.Equipment.WASHING_MACHINE;
import static home.four.you.model.entity.Property.HeatType.WOOD;
import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_ADMIN;
import static home.four.you.security.auth.authorization.AuthorityRole.ROLE_USER;
import static java.util.Collections.singletonList;
import static net.bytebuddy.utility.RandomString.make;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpBasedTest {

    @LocalServerPort
    int port;

    static {
        RestAssuredConfig.config().
            getLogConfig()
            .enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    protected static final String APPLICATION_JSON = "application/json";

    protected static final String API_PREFIX = "/api";
    protected static final String ADS_URI = API_PREFIX + "/ads";
    protected static final String AD_URI = ADS_URI + "/{id}";
    protected static final String ADS_LATEST_URI = ADS_URI + "/latest";
    protected static final String ADS_SEARCH_URI = ADS_URI + "/search";

    protected static final String USERS_URI = API_PREFIX + "/users";
    protected static final String USER_URI = USERS_URI + "/{id}";
    protected static final String LOGIN_URI = "/auth/login";

    @Autowired
    protected AdRepository adRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenProvider tokenProvider;

    protected String url(String path) {
        final String URL_TEMPLATE = "http://localhost:%s%s";

        return String.format(URL_TEMPLATE, port, path);
    }

    protected HttpHeaders defaultHeaders() {
        var headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

        return headers;
    }

    protected HttpHeaders authenticatedHeaders(User user) {
        var headers = new HttpHeaders();
        headers.setBearerAuth(tokenProvider.createToken(user));

        return headers;
    }

    protected Ad createRandomAd() {
        return adRepository.save(new Ad()
            .setTitle(make())
            .setDescription(make())
            .setType(SALE)
            .setExpirationDate(Instant.now().plus(90, ChronoUnit.DAYS))
            .setPrice(generateId().intValue())
            .setOwner(createRegularUser())
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

    protected User createRegularUser() {
        return createUser(ROLE_USER);
    }

    protected User createAdmin() {
        return createUser(ROLE_ADMIN);
    }

    protected User createUser(AuthorityRole role) {
        return userRepository.save(new User()
            .setEmail(generateRandomEmail())
            .setFirstName(make())
            .setLastName(make())
            .setPassword(make())
            .setPhone(make())
            .setRole(roleRepository.findByName(role)));
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

    protected JSONObject createUserJSON(String email, String password) throws JSONException {
        return createUserJSON()
            .put("email", email)
            .put("password", password)
            .put("repeatedPassword", password);
    }

    protected JSONObject createUserJSON() throws JSONException {
        var password = make();
        return new JSONObject()
            .put("email", generateRandomEmail())
            .put("firstName", make())
            .put("lastName", make())
            .put("password", password)
            .put("repeatedPassword", password)
            .put("phone", make())
            .put("role", ROLE_USER.toString());
    }

    protected JSONObject createLoginRequestJSON(String email, String password) throws JSONException {
        return new JSONObject()
            .put("email", email)
            .put("password", password);
    }

    protected JSONObject createLoginRequestJSON() throws JSONException {
        return createLoginRequestJSON(make(), make());
    }

    protected String generateRandomEmail() {
        String email = make() + '@' + make() + '.' + RandomStringUtils.randomAlphabetic(3);
        return email.toLowerCase();
    }

}
