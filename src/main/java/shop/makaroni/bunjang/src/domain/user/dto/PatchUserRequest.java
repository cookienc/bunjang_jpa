package shop.makaroni.bunjang.src.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class PatchUserRequest {

	@ApiModelProperty(notes = "가게 이미지", example = "image")
	String image;
	@ApiModelProperty(notes = "가게 이름", example = "새로운 가게 이름")
	String storeName;
	@ApiModelProperty(notes = "가게 주소", example = "url")
	String storeUrl;

	@ApiModelProperty(notes = "연락 가능 시간(시작 시간)", example = "0")
	@Range(min=0, max = 24, message = "0과 24사이로 입력해주세요.")
	Integer contactStart;
	@ApiModelProperty(notes = "연락 가능 시간(끝 시간)", example = "24")
	@Range(min=0, max = 24, message = "0과 24사이로 입력해주세요.")
	Integer contactEnd;

	@ApiModelProperty(notes = "상점 소개", example = "연락이 잘되는 가게입니다.")
	String description;
	@ApiModelProperty(notes = "교환/반품/환불 정책", example = "환불 보장")
	String policy;
	@ApiModelProperty(notes = "구매전 유의사항", example = "택배비는 포함이 안됩니다.")
	String precaution;

	public PatchUserRequest(String image, String storeName, String storeUrl, Integer contactStart, Integer contactEnd, String description, String policy, String precaution) {
		this.image = image;
		this.storeName = storeName;
		this.storeUrl = storeUrl;
		this.contactStart = contactStart;
		this.contactEnd = contactEnd;
		this.description = description;
		this.policy = policy;
		this.precaution = precaution;
	}
}
