package shop.makaroni.bunjang.src.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SmsLoginRequest {
	@ApiModelProperty(value = "이름", example = "홍길동")
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;

	@ApiModelProperty(value = "주민등록번호 앞 6자리", example = "001225")
	@NotBlank(message = "주민등록번호를 입력해주세요.")
	private String birthNumber;

	@ApiModelProperty(value = "전화번호", example = "01012345678")
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phoneNumber;

	@ApiModelProperty(value = "인증 유무", example = "true")
	@NotNull(message = "인증 유무를 입력해주세요.")
	private Boolean isChecked;
}
