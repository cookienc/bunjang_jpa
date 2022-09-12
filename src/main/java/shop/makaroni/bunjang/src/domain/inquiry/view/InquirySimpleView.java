package shop.makaroni.bunjang.src.domain.inquiry.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquirySimpleView {
	@ApiModelProperty(notes = "문의 사항 작성자", example = "규라인상")
	private String name;
	@ApiModelProperty(notes = "작성자의 사진", example = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Sunflower_sky_backdrop.jpg/1200px-Sunflower_sky_backdrop.jpg")
	private String image;
	@ApiModelProperty(notes = "내용", example = "내고 가능한가요?")
	private String post;
	@ApiModelProperty(notes = "문의 사항을 작성한 날짜", example = "1일 전")
	private String date;

	public InquirySimpleView(String name, String image, String post, String date) {
		this.name = name;
		this.image = image;
		this.post = post;
		this.date = date;
	}

	public static InquirySimpleView of(InquirySimpleResponse response) {
		return new InquirySimpleView(response.getName(), response.getImage(), response.getPost(), response.getDate());
	}
}
