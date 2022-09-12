package shop.makaroni.bunjang.src.domain.user.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import shop.makaroni.bunjang.src.domain.user.dto.StoreSearchDto;

@Getter
public class StoreSearchView {

	@ApiModelProperty(notes = "상점 식별자", example = "1")
	private String storeIdx;
	@ApiModelProperty(notes = "상점 이름", example = "안녕")
	private String storeName;
	@ApiModelProperty(notes = "상점 이미지", example = "https://www.sciencetimes.co.kr/wp-content/uploads/2019/01/190111-1280px-Humpback_stellwagen_edit-%EC%9C%84%ED%82%A4.jpg\"")
	private String storeImage;
	@ApiModelProperty(notes = "상점의 팔로우 수", example = "0")
	private String followers;
	@ApiModelProperty(notes = "상점의 팔고있는 아이템 수", example = "4")
	private String items;

	@Builder
	public StoreSearchView(String storeIdx, String storeName, String storeImage, String followers, String items) {
		this.storeIdx = storeIdx;
		this.storeName = storeName;
		this.storeImage = storeImage;
		this.followers = followers;
		this.items = items;
	}

	public static StoreSearchView of(StoreSearchDto dto, Integer followers, Integer storeItems) {
		return StoreSearchView.builder()
				.storeIdx(String.valueOf(dto.getStoreIdx()))
				.storeName(dto.getStoreName())
				.storeImage(dto.getStoreImage())
				.followers(String.valueOf(followers))
				.items(String.valueOf(storeItems))
				.build();
	}
}
