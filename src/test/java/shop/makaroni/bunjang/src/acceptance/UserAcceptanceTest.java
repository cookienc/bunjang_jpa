package shop.makaroni.bunjang.src.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.makaroni.bunjang.src.acceptance.LoginUtils.join;

@DisplayName("회원관련 인수 테스트를 진행한다.")
public class UserAcceptanceTest extends AcceptanceTest {

	/**
	 * When 회원가입을 요청하면
	 * Then 성공한다.
	 */
	@DisplayName("회원가입을 테스트 한다.")
	@Test
	void 회원가입_성공_테스트() {
		//when
		ExtractableResponse<Response> 응답 = join();

		//then
		Assertions.assertAll(
				() -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
				() -> assertThat(응답.jsonPath().getString("code")).isEqualTo("201")
		);
	}
}
