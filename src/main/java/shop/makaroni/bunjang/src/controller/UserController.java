package shop.makaroni.bunjang.src.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.makaroni.bunjang.src.domain.item.State;
import shop.makaroni.bunjang.src.domain.user.dto.PatchUserRequest;
import shop.makaroni.bunjang.src.domain.user.dto.SaveUserRequest;
import shop.makaroni.bunjang.src.domain.user.view.MyStoreResponse;
import shop.makaroni.bunjang.src.domain.user.view.StoreInfoView;
import shop.makaroni.bunjang.src.domain.user.view.StoreSaleView;
import shop.makaroni.bunjang.src.domain.user.view.StoreSearchView;
import shop.makaroni.bunjang.src.provider.UserProvider;
import shop.makaroni.bunjang.src.response.ResponseInfo;
import shop.makaroni.bunjang.src.response.exception.EmptyParamEx;
import shop.makaroni.bunjang.src.service.InquiryService;
import shop.makaroni.bunjang.src.service.UserService;
import shop.makaroni.bunjang.utils.PagingCond;
import shop.makaroni.bunjang.utils.QueryStringArgResolver;
import shop.makaroni.bunjang.utils.auth.Login;
import shop.makaroni.bunjang.utils.auth.Secured;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static shop.makaroni.bunjang.src.response.ErrorCode.EMPTY_PARAM_EXCEPTION;
import static shop.makaroni.bunjang.src.response.SuccessStatus.PATCH_SUCCESS;
import static shop.makaroni.bunjang.src.response.SuccessStatus.SAVE_SUCCESS;
import static shop.makaroni.bunjang.src.response.SuccessStatus.WITHDRAWAL_SUCCESS;

@Api(tags = "회원 관리 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserProvider userProvider;
	private final UserService userService;
	private final InquiryService inquiryService;

	@ApiOperation("ID와 PW로 회원가입")
	@PostMapping
	public ResponseEntity<ResponseInfo> save(@Valid @RequestBody SaveUserRequest request) {
		Long userIdx = userService.saves(request);
		String url = "/users/" + userIdx;
		return ResponseEntity.created(URI.create(url)).body(ResponseInfo.of(SAVE_SUCCESS));
	}

	@ApiOperation("MY탭")
	@GetMapping
	public ResponseEntity<MyStoreResponse> getMyStore(
			@ApiParam(value = "회원 식별자", required = true, example = "1") @Login Long userIdx) {
		return ResponseEntity.ok(userProvider.getMyStore(userIdx));
	}

	@ApiOperation("사용자 판매 상품 목록 조회")
	@GetMapping("/items")
	public ResponseEntity<List<StoreSaleView>> getMyStoreItem(
			@ApiParam(value = "회원 식별자", required = true, example = "1") @Login Long userIdx,
			@ApiParam(value = "판매 조건", required = true, example = "Y", defaultValue = "Y") @RequestParam("condition") String condition,
			@QueryStringArgResolver PagingCond pagingCond) {
		State.valid(condition);
		return ResponseEntity.ok(userProvider.getMyStoreItem(userIdx, condition, pagingCond));
	}

	@ApiOperation("사용자 판매 상품 목록 검색")
	@GetMapping("/items/search")
	public ResponseEntity<List<StoreSaleView>> getMyStoreItem(
			@ApiParam(value = "회원 식별자", required = true, example = "1") @Login Long userIdx,
			@ApiParam(value = "상품 명", required = true, defaultValue = "")
			@RequestParam(value = "itemName", defaultValue = "") String itemName,
			@ApiParam(value = "판매 조건", required = true, example = "Y", defaultValue = "Y")
			@RequestParam("condition") String condition,
			@QueryStringArgResolver PagingCond pagingCond) {
		State.valid(condition);
		return ResponseEntity.ok(userProvider.searchStoreItemByName(userIdx, itemName, condition, pagingCond));
	}

	@ApiOperation("회원 수정")
	@PatchMapping
	public ResponseEntity<ResponseInfo> update(
			@ApiParam(value = "회원 식별자", required = true, example = "1") @Login Long userIdx,
			@Valid @RequestBody PatchUserRequest request) {
		userService.update(userIdx, request);
		return ResponseEntity.ok(ResponseInfo.of(PATCH_SUCCESS));
	}

	@ApiOperation("회원 탈퇴")
	@PatchMapping("/d")
	public ResponseEntity<ResponseInfo> delete(@ApiParam(value = "회원 식별자", required = true, example = "1") @Login Long userIdx) {
		userService.delete(userIdx);
		return ResponseEntity.ok(ResponseInfo.of(WITHDRAWAL_SUCCESS));
	}

	@ApiOperation("상점 화면 출력")
	@Secured
	@GetMapping("/stores/{storeIdx}")
	public ResponseEntity<StoreInfoView> getStoreById(
            @ApiParam(value = "상점 식별자", required = true, example = "1") @PathVariable Long storeIdx) {
		return ResponseEntity.ok(userProvider.getStoreById(storeIdx));
	}

	@ApiOperation("상점 검색")
	@Secured
	@GetMapping("/stores/search")
	public ResponseEntity<List<StoreSearchView>> searchStoreByName(@ApiParam(value = "상점 이름") @RequestParam String name) {
		if (name.isBlank()) {
			throw new EmptyParamEx(EMPTY_PARAM_EXCEPTION.getMessages());
		}
		return ResponseEntity.ok(userProvider.searchStoreByName(name));
	}
}
