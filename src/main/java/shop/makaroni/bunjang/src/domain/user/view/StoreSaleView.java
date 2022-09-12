package shop.makaroni.bunjang.src.domain.user.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.makaroni.bunjang.src.domain.item.Item;

import static shop.makaroni.bunjang.config.Constant.CANNOT_FIND_LOCATION;

@Getter
@NoArgsConstructor
public class StoreSaleView {

	@ApiModelProperty(notes = "상품 식별자", example = "1")
	String itemIdx;
	@ApiModelProperty(notes = "상품 이름", example = "프리미어 리그 플라이트")
	String itemName;
	@ApiModelProperty(notes = "가격", example = "195000")
	String price;
	@ApiModelProperty(notes = "지역 정보", example = "지역정보를 찾을 수 없음")
	String location;
	@ApiModelProperty(notes = "상품 이미지", example = "https://static-breeze.nike.co.kr/kr/ko_kr/cmsstatic/product/888681004/DN3602-100_DN3602-100_primary.jpg?gallery")
	String image;
	@ApiModelProperty(notes = "업데이트한 시간ㅓ", example = "12시간 전")
	String time;

	@Builder
	public StoreSaleView(String itemIdx, String itemName, String price, String location, String image, String time) {
		this.itemIdx = itemIdx;
		this.itemName = itemName;
		this.price = price;
		this.location = location;
		this.image = image;
		this.time = time;
	}

	public static StoreSaleView of(Item item) {
		return StoreSaleView.builder()
				.itemIdx(String.valueOf(item.getIdx()))
				.itemName(item.getName())
				.price(String.valueOf(item.getPrice()))
				.location(item.getLocation() == null ? CANNOT_FIND_LOCATION.getMessages() : item.getLocation())
				.image(item.getImage())
				.time(item.getUpdatedAt())
				.build();
	}
}
