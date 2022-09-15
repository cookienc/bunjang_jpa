package shop.makaroni.bunjang.src.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PhoneNumber {
	@ApiModelProperty(notes = "전화번호", example = "01012345678")
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phoneNumber;

	public PhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
