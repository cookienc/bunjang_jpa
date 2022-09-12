package shop.makaroni.bunjang.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PagingCond {
	public static final String ASC = "asc";

	@ApiModelProperty(notes = "페이지 시작 번호", example = "0")
	private Integer start;
	@ApiModelProperty(notes = "페이지 크기", example = "10")
	private Integer offset;
	@ApiModelProperty(notes = "날짜 정렬(최신 순, 오래된 순)", example = "ASC")
	private String dateSort;

	@Builder
	public PagingCond(Integer start, Integer offset, String dateSort) {
		this.start = start;
		this.offset = offset;
		this.dateSort = dateSort;
	}

	public static PagingCond defaultValue() {
		return new PagingCond(0, 5, ASC);
	}

	public PagingCond validate() {
		return PagingCond.builder()
				.start(this.start == null ? 0 : this.start)
				.offset(this.offset == null ? 10 : this.offset)
				.dateSort(this.dateSort == null ? ASC : this.dateSort)
				.build();
	}
}
