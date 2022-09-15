package shop.makaroni.bunjang.src.acceptance;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.MediaType;

public class AcceptanceSteps {
	public static RequestSpecification given() {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE);
	}
}
