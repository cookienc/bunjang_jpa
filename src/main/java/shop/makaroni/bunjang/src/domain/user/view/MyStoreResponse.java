package shop.makaroni.bunjang.src.domain.user.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.makaroni.bunjang.src.domain.user.User;

import java.util.List;

@Getter
@NoArgsConstructor
public class MyStoreResponse {

	@ApiModelProperty(notes = "가게 식별자", example = "1")
	private String storeId;
	@ApiModelProperty(notes = "가게 이름", example = "평화시장")
	private String storeName;
	@ApiModelProperty(notes = "본인 인증 여부", example = "TRUE")
	private Boolean isCertificated;
	@ApiModelProperty(notes = "가게 이미지", example = "http://www.traveli.co.kr/repository/look/contents/look_p170_m.jpg")
	private String storeImage;
	@ApiModelProperty(notes = "가게 별점", example = "4.5")
	private String rating;
	@ApiModelProperty(notes = "사용자가 찜 한 사람의 수", example = "2")
	private String wishLists;
	@ApiModelProperty(notes = "사용자가 작성한 리뷰 수", example = "13")
	private String reviews;
	@ApiModelProperty(notes = "사용자가 팔로워 하는 리뷰 수", example = "3")
	private String followers;
	@ApiModelProperty(notes = "사용자가 팔로잉 하는 사람 수", example = "2")
	private String followings;
	@ApiModelProperty(notes = "상품 판매 리스트")
	private List<StoreSaleView> itemsResponses;

	@Builder
	public MyStoreResponse(String storeId, String storeName, Boolean isCertificated, String storeImage, String rating, String wishLists, String reviews, String followers, String followings, List<StoreSaleView> itemsResponses) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.isCertificated = isCertificated;
		this.storeImage = storeImage;
		this.rating = rating;
		this.wishLists = wishLists;
		this.reviews = reviews;
		this.followers = followers;
		this.followings = followings;
		this.itemsResponses = itemsResponses;
	}

	public static MyStoreResponse of(User user, Integer reviewCount, Integer wishListCount, Integer followerCount, Integer followingcount, String rating, List<StoreSaleView> myStoreItem) {
		return MyStoreResponse.builder()
				.storeId(String.valueOf(user.getIdx()))
				.storeName(user.getStoreName())
				.storeImage(user.getStoreImage())
				.isCertificated(user.getIsCertificated())
				.rating(rating)
				.wishLists(String.valueOf(wishListCount))
				.reviews(String.valueOf(reviewCount))
				.followers(String.valueOf(followerCount))
				.followings(String.valueOf(followingcount))
				.itemsResponses(myStoreItem)
				.build();
	}
}
