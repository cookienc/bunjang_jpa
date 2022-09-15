package shop.makaroni.bunjang.src.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static shop.makaroni.bunjang.src.acceptance.AcceptanceSteps.given;

@DisplayName("로그인 인수 테스트를 진행한다.")
public class LoginAcceptanceTest extends AcceptanceTest {

	/**
	 * When 회원가입을 한 아이디와 비밀번호를 이용하여 로그인을 하면,
	 * Then 성공한다.
	 */
	@DisplayName("ID와 PW로 로그인을 하면 성공한다.")
	@Test
	void ID와_PW로_로그인_한다() {
		//when
		ExtractableResponse<Response> 응답 = given()
				.body(Map.of("loginId", admin,
						"password", password))
				.when().post("/login")
				.then().log().all()
				.extract();

		//then
		assertAll(
				() -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.OK.value()),
				() -> assertThat(응답.jsonPath().getString("jwt")).isNotBlank()
		);
	}
}
