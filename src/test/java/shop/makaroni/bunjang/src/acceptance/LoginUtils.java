package shop.makaroni.bunjang.src.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class LoginUtils {

	public static ExtractableResponse<Response> join() {
		Map<String, Object> params = new HashMap<>();
		params.put("loginId", "edan1234");
		params.put("password", "1234abce!@#");

		return RestAssured.given().log().all()
				.body(params)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when().post("/users")
				.then().log().all()
				.extract();
	}

}
