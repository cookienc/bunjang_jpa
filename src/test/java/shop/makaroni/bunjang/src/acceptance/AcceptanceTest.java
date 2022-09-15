package shop.makaroni.bunjang.src.acceptance;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import shop.makaroni.bunjang.src.utils.DatabaseCleanup;

import static shop.makaroni.bunjang.src.acceptance.LoginUtils.join;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTest {

	@LocalServerPort
	int port;

	@Autowired
	private DatabaseCleanup databaseCleanup;

	public static String admin = "admin";
	public static String password = "1234abce!@#";

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
		databaseCleanup.execute();
		join(admin, password);
	}
}