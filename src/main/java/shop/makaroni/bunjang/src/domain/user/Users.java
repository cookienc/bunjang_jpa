package shop.makaroni.bunjang.src.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import shop.makaroni.bunjang.src.domain.user.dto.SaveUserRequest;
import shop.makaroni.bunjang.utils.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity(name = "User")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(nullable = false, length = 10)
	private String loginId;
	@Column(nullable = false, length = 200)
	private String password;
	@Column(length = 10)
	private String storeName;

	@Column(columnDefinition = "integer default 0")
	private Integer contactStart;
	@Column(columnDefinition = "integer default 24")
	private Integer contactEnd;
	@Column(columnDefinition = "tinyint default 0")
	private Boolean isCertificated;

	private String storeUrl;
	private String storeImage;
	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String policy;
	@Column(length = 1000)
	private String precaution;

	@Column(columnDefinition = "int default 0")
	private Integer hit;

	@Builder
	public Users(Long idx, String loginId, String password, String storeName, Integer contactStart, Integer contactEnd, Boolean isCertificated, String storeUrl, String storeImage, String description, String policy, String precaution, Integer hit) {
		this.idx = idx;
		this.loginId = loginId;
		this.password = password;
		this.storeName = storeName;
		this.contactStart = contactStart;
		this.contactEnd = contactEnd;
		this.isCertificated = isCertificated;
		this.storeUrl = storeUrl;
		this.storeImage = storeImage;
		this.description = description;
		this.policy = policy;
		this.precaution = precaution;
		this.hit = hit;
	}

	public static Users of(SaveUserRequest request, String encodePassword) {
		return Users.builder()
				.loginId(request.getLoginId())
				.password(encodePassword)
				.storeName(request.getStoreName())
				.build();
	}
}