package users;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.not;

@MicronautTest
class UsersApiIntegrationTest {

  private static final String USER_ENDPOINT_BASE = "/users";

  @Inject ObjectMapper mapper;

  static Stream<io.restassured.response.Response> apis() {
    return Stream.of(
        RestAssured.given().log().all().when().get(USER_ENDPOINT_BASE + "/abc"),
        RestAssured.given()
            .header(HttpHeaders.AUTHORIZATION, "")
            .log()
            .all()
            .when()
            .get(USER_ENDPOINT_BASE + "/abc"),
        RestAssured.given()
            .body("{}")
            .log()
            .all()
            .when()
            .post(USER_ENDPOINT_BASE + "/syncUser"),
        RestAssured.given()
            .header(HttpHeaders.AUTHORIZATION, "")
            .body("{}")
            .log()
            .all()
            .when()
            .post(USER_ENDPOINT_BASE + "/syncUser"));
  }

  @ParameterizedTest(name = "Api does not work without token {0}")
  @MethodSource("apis")
  void noApiWorkWithoutTokenOrWithOtherToken(io.restassured.response.Response api) {
    validateForbidden(api);
  }

  private void validateForbidden(io.restassured.response.Response request) {
    request.then().statusCode(not(HttpStatus.OK.getCode()));
  }
}