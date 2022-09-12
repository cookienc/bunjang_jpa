package shop.makaroni.bunjang.src.domain.user.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import shop.makaroni.bunjang.src.domain.inquiry.view.InquirySimpleView;
import shop.makaroni.bunjang.src.domain.review.view.ReviewSimpleView;
import shop.makaroni.bunjang.src.domain.user.User;

import java.util.List;

@Getter
public class StoreInfoView {
	@ApiModelProperty(notes = "가게 식별자", example = "1")
	private String storeId;
	@ApiModelProperty(notes = "가게 이름", example = "상점12호")
	private String storeName;
	@ApiModelProperty(notes = "본인 인증 여부", example = "FALSE")
	private Boolean isCertificated;
	@ApiModelProperty(notes = "가게 사진", example = "https://www.sciencetimes.co.kr/wp-content/uploads/2019/01/190111-1280px-Humpback_stellwagen_edit-%EC%9C%84%ED%82%A4.jpg")
	private String storeImage;
	@ApiModelProperty(notes = "별점", example = "3.3")
	private String rating;
	@ApiModelProperty(notes = "사용자가 찜 한 사람 수", example = "1")
	private String wishLists;
	@ApiModelProperty(notes = "사용자가 작성한 리뷰 수", example = "1")
	private String reviews;
	@ApiModelProperty(notes = "사용자를 팔로워 하는 사람 수", example = "1")
	private String followers;
	@ApiModelProperty(notes = "사용자가 팔로잉 하는 사람 수", example = "1")
	private String followings;
	@ApiModelProperty(notes = "총 판매된 물건 수", example = "1")
	private String soldCount;
	@ApiModelProperty(notes = "연락 가능 시간 (처음)", example = "0")
	private String contactStart;
	@ApiModelProperty(notes = "연락 가능 시간 (마지막)", example = "24")
	private String contactEnd;
	@ApiModelProperty(notes = "구매전 유의 사항", example = "판매자에게 구매 전 유의사항을 확인 후 거래해주세요.")
	private String precaution;
	@ApiModelProperty(notes = "교환/반품/환불 정책", example = "물품의 상태가 기재된 것과 상이할 경우 배송완료일 기준 7일 이내에 환불 및 반품이 가능합니다")
	private String policy;
	@ApiModelProperty(notes = "상점 소개", example = "상점소개가 없습니다")
	private String description;
	@ApiModelProperty(notes = "가입한 날로부터 현재까지의 날의 수", example = "＋2")
	private String openDate;
	@ApiModelProperty(notes = "조회수", example = "15")
	private String hit;
	@ApiModelProperty(notes = "상품 판매 목록")
	private List<StoreSaleView> itemsResponses;
	@ApiModelProperty(notes = "리뷰 목록")
	private List<ReviewSimpleView> reviewsResponses;
	@ApiModelProperty(notes = "문의 목록")
	private List<InquirySimpleView> inquiryResponses;

	@Builder
	public StoreInfoView(String storeId, String storeName, Boolean isCertificated, String storeImage, String rating, String wishLists, String reviews, String followers, String followings, String soldCount, String contactStart, String contactEnd, String precaution, String policy, String description, String openDate, String hit, List<StoreSaleView> itemsResponses, List<ReviewSimpleView> reviewsResponses, List<InquirySimpleView> inquiryResponses) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.isCertificated = isCertificated;
		this.storeImage = storeImage;
		this.rating = rating;
		this.wishLists = wishLists;
		this.reviews = reviews;
		this.followers = followers;
		this.followings = followings;
		this.soldCount = soldCount;
		this.contactStart = contactStart;
		this.contactEnd = contactEnd;
		this.precaution = precaution;
		this.policy = policy;
		this.description = description;
		this.openDate = openDate;
		this.hit = hit;
		this.itemsResponses = itemsResponses;
		this.reviewsResponses = reviewsResponses;
		this.inquiryResponses = inquiryResponses;
	}

	public static StoreInfoView of(User user, String rating, Integer reviewCount, Integer wishListCount, Integer followerCount, Integer followingCount, String soldCount, List<StoreSaleView> itemSalesResponse, List<ReviewSimpleView> reviewInfo, List<InquirySimpleView> inquiryInfo) {
		return StoreInfoView.builder()
				.storeId(String.valueOf(user.getIdx()))
				.storeName(user.getStoreName())
				.isCertificated(user.getIsCertificated())
				.storeImage(user.getStoreImage())
				.rating(rating)
				.wishLists(String.valueOf(wishListCount))
				.reviews(String.valueOf(reviewCount))
				.followers(String.valueOf(followerCount))
				.followings(String.valueOf(followingCount))
				.soldCount(soldCount)
				.contactStart(user.getContactStart())
				.contactEnd(user.getContactEnd())
				.precaution(user.getPrecaution())
				.policy(user.getPolicy())
				.description(user.getDescription())
				.openDate(user.getOpenDate())
				.hit(user.getHit())
				.itemsResponses(itemSalesResponse)
				.reviewsResponses(reviewInfo)
				.inquiryResponses(inquiryInfo)
				.build();
	}
}
