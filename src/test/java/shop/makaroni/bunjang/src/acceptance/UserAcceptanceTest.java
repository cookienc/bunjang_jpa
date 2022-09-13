package shop.makaroni.bunjang.src.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원관련 인수 테스트를 진행한다.")
public class UserAcceptanceTest extends AcceptanceTest {

	@DisplayName("회원가입을 테스트 한다.")
	@Test
	void 회원가입_성공_테스트() {
		//given
		Map<String, Object> params = new HashMap<>();
		params.put("loginId", "edan1234");
		params.put("password", "1234abce!@#");

		//when
		ExtractableResponse<Response> 응답 = RestAssured.given().log().all()
				.body(params)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post("/users")
				.then().log().all()
				.extract();

		//then
		Assertions.assertAll(
				() -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
				() -> assertThat(응답.jsonPath().getString("code")).isEqualTo("201")
		);
	}
}
