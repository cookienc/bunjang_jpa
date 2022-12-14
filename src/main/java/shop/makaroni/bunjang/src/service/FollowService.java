package shop.makaroni.bunjang.src.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.makaroni.bunjang.src.dao.FollowDao;
import shop.makaroni.bunjang.src.provider.FollowProvider;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

	private final FollowDao followDao;
	private final FollowProvider followProvider;

	public Long doFollow(Long userIdx, Long storeIdx) {
		followProvider.isAlreadyExist(userIdx, storeIdx);
		return followDao.doFollow(userIdx, storeIdx);
	}

	public void delete(Long userIdx, Long storeIdx) {
		if (followDao.alreadyExistFollow(userIdx, storeIdx)) {
			followDao.delete(userIdx, storeIdx);
		}
	}

	public Long saveNotification(Long userIdx, Long storeIdx) {
		followProvider.isAlreadyNotification(userIdx, storeIdx);
		followDao.saveNotification(userIdx, storeIdx);
		return followDao.findIdByUserIdAndStoreId(userIdx, storeIdx);
	}

	public void deleteNotification(Long userIdx, Long storeIdx) {
		followProvider.notFollow(userIdx, storeIdx);
		followDao.deleteNotification(userIdx, storeIdx);
	}
}
