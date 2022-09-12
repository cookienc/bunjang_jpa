package shop.makaroni.bunjang.src.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SaveUserRequest {

	@ApiModelProperty(notes = "로그인 아이디", example = "edan1234")
	@NotNull(message = "아이디를 입력해주세요")
	@NotBlank(message = "아이디를 입력해주세요")
	@Size(min = 3, max = 10, message = "아이디의 길이를 3 ~ 10글자 사이로 입력해주세요.")
	private String loginId;

	@ApiModelProperty(notes = "비밀번호(특수문자는 정의된 특수문자만 사용 가능 : ~!@#$%^&*()+|=)", example = "12qw!@#$")
	@NotNull(message = "비밀번호를 입력해주세요")
	@NotBlank(message = "비밀번호를 입력해주세요")
	private String password;

	@ApiModelProperty(notes = "상점 명", example = "상점 1호")
	private String storeName;

	@Builder
	public SaveUserRequest(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public static SaveUserRequest of(Long kakaoId, String key) {
		return SaveUserRequest.builder()
				.loginId(String.valueOf(kakaoId))
				.password(key)
				.build();
	}
}
