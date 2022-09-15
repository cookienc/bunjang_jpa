package shop.makaroni.bunjang.src.acceptance;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static shop.makaroni.bunjang.src.acceptance.AcceptanceSteps.given;

public class LoginUtils {

	public static ExtractableResponse<Response> join() {
		Map<String, Object> params = new HashMap<>();
		params.put("loginId", "edan1234");
		params.put("password", "1234abce!@#");

		return given()
				.body(params)
				.when().post("/users")
				.then().log().all()
				.extract();
	}

	public static ExtractableResponse<Response> join(String loginId, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("loginId", loginId);
		params.put("password", password);

		return given()
				.body(params)
				.when().post("/users")
				.then().log().all()
				.extract();
	}

}
