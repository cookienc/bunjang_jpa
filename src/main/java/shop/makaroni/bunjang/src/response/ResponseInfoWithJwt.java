package shop.makaroni.bunjang.src.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseInfoWithJwt {

	@ApiModelProperty(notes = "응답 코드", example = "200")
	private String code;
	@ApiModelProperty(notes = "응답 메세지")
	private String message;
	@ApiModelProperty(notes = "JWT 토큰", example = "efajdkljvca124dflvajl")
	private String jwt;

	public ResponseInfoWithJwt(String code, String message, String jwt) {
		this.code = code;
		this.message = message;
		this.jwt = jwt;
	}

	public static ResponseInfoWithJwt of(SuccessStatus status, String jwt) {
		return new ResponseInfoWithJwt(String.valueOf(status.getStatus().value()), status.getMessage(), jwt);
	}
}
