package shop.makaroni.bunjang.src.domain.review.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSimpleView {
	@ApiModelProperty(notes = "리뷰 쓴 사람의 이름", example = "규라인상")
	private String reviewerName;
	@ApiModelProperty(notes = "리뷰 쓴 사람의 프로필 이미지", example = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Sunflower_sky_backdrop.jpg/1200px-Sunflower_sky_backdrop.jpg")
	private String reviewerImage;
	@ApiModelProperty(notes = "내용", example = "친절해요")
	private String post;
	@ApiModelProperty(notes = "별점", example = "5")
	private String rating;
	@ApiModelProperty(notes = "리뷰 쓴 날짜", example = "1일 전")
	private String date;

	@Builder
	public ReviewSimpleView(String reviewerName, String reviewerImage, String post, String rating, String date) {
		this.reviewerName = reviewerName;
		this.reviewerImage = reviewerImage;
		this.post = post;
		this.rating = rating;
		this.date = date;
	}
}
