package shop.makaroni.bunjang.src.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.makaroni.bunjang.src.domain.login.LoginRequest;
import shop.makaroni.bunjang.src.domain.user.PhoneNumber;
import shop.makaroni.bunjang.src.domain.user.SmsLoginRequest;
import shop.makaroni.bunjang.src.provider.UserProvider;
import shop.makaroni.bunjang.src.response.ResponseInfo;
import shop.makaroni.bunjang.src.response.ResponseInfoWithCheck;
import shop.makaroni.bunjang.src.response.ResponseInfoWithJwt;
import shop.makaroni.bunjang.src.response.SuccessStatus;
import shop.makaroni.bunjang.src.response.exception.CanNotIssueAuthCodeException;
import shop.makaroni.bunjang.src.service.KakaoService;
import shop.makaroni.bunjang.src.service.LoginService;
import shop.makaroni.bunjang.src.service.NaverService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

import static shop.makaroni.bunjang.src.response.ErrorCode.CANNOT_ISSUE_AUTH_CODE_EXCEPTION;
import static shop.makaroni.bunjang.src.response.SuccessStatus.CHECK_LOGIN_ID_SUCCESS;
import static shop.makaroni.bunjang.src.response.SuccessStatus.LOGIN_SUCCESS;

@Api(tags = "로그인 API")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

	private final UserProvider userProvider;
	private final LoginService loginService;
	private final KakaoService kakaoService;
	private final NaverService naverService;

	@ApiOperation("중복 아이디 검증")
	@GetMapping
	public ResponseEntity<ResponseInfo> checkDuplicateLoginId(@ApiParam(value = "검증할 로그인 아이디", example = "edan1234") @RequestParam String loginId) {
		userProvider.checkDuplicateLoginId(loginId);
		return ResponseEntity.ok(ResponseInfo.of(CHECK_LOGIN_ID_SUCCESS));
	}

	@ApiOperation("ID와 PW로 로그인")
	@PostMapping
	public ResponseEntity<ResponseInfoWithJwt> login(@Valid @RequestBody LoginRequest loginRequest) {
		String jwt = loginService.login(loginRequest);
		return ResponseEntity.ok(ResponseInfoWithJwt.of(LOGIN_SUCCESS, jwt));
	}

	@ApiOperation("카카오 AuthCode로 로그인")
	@GetMapping("/kakao")
	public ResponseEntity<ResponseInfoWithJwt> getJwtWithAuthorizeCode(@ApiParam(value = "AuthCode") @RequestParam String code) throws IOException {
		String accessToken = kakaoService.getToken(code);
		String jwt = kakaoService.getJwt(accessToken);
		return ResponseEntity.ok(ResponseInfoWithJwt.of(LOGIN_SUCCESS, jwt));
	}

	@ApiOperation("카카오로 로그인")
	@GetMapping("/kakao/auth")
	public ResponseEntity<ResponseInfoWithJwt> getJwt(@ApiParam(value = "클라이언트에서 받은 accesstoken") @RequestParam("token") String accessToken) throws IOException {
		String jwt = kakaoService.getJwt(accessToken);
		return ResponseEntity.ok(ResponseInfoWithJwt.of(LOGIN_SUCCESS, jwt));
	}

	@ApiOperation("sms 인증 코드 발송")
	@PostMapping("/sms/auth")
	public ResponseEntity<ResponseInfo> getMessages(HttpSession session, @Valid @RequestBody PhoneNumber phoneNumber) {
		try {
			naverService.sendSms(session, phoneNumber);
		} catch (Exception e) {
			throw new CanNotIssueAuthCodeException(CANNOT_ISSUE_AUTH_CODE_EXCEPTION.getMessages(), e);
		}
		return ResponseEntity.ok().body(ResponseInfo.of(SuccessStatus.ISSUE_AUTH_CODE_SUCCESS));
	}

	@ApiOperation("sms 인증 코드 확인")
	@GetMapping("/sms")
	public ResponseEntity<ResponseInfoWithCheck> smsLogin(HttpSession session, @ApiParam(value = "인증번호 6자리", example = "123456") @RequestParam String authNumber) {
		boolean isCheck = naverService.checkingCode(session, authNumber);
		return ResponseEntity.ok().body(ResponseInfoWithCheck.of(SuccessStatus.AUTH_CODE_MATCH_SUCCESS, isCheck));
	}

	@ApiOperation("sms로 로그인")
	@PostMapping("/sms")
	public ResponseEntity<ResponseInfoWithJwt> smsLogin(@Valid @RequestBody SmsLoginRequest request) {
		String jwt = naverService.smsLogin(request);
		return ResponseEntity.ok().body(ResponseInfoWithJwt.of(LOGIN_SUCCESS, jwt));
	}
}
