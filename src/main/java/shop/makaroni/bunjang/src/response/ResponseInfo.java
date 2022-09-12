package shop.makaroni.bunjang.src.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseInfo {

	@ApiModelProperty(notes = "응답 코드", example = "200")
	private String code;

	@ApiModelProperty(notes = "응답 메세지")
	private String message;

	public ResponseInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResponseInfo of(SuccessStatus status) {
		return new ResponseInfo(String.valueOf(status.getStatus().value()), status.getMessage());
	}
}
