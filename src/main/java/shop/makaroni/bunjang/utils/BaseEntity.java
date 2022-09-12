package shop.makaroni.bunjang.utils;

import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@Column(name = "createdAt", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(name = "updatedAt", nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@Column(name = "status", columnDefinition = "char(1) default 'Y'")
	private Character status;
}
