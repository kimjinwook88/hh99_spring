//카카오톡 개발자 홈페이지에서 개인적으로 등록 필요 (https://developers.kakao.com/console/app)
$(document).ready(function(){
	Kakao.init('인증키 등록');
	
	//카카오 토큰 유효성 여부 체크 후 index 페이지로 이동
	if(Kakao.Auth.getAccessToken() != null){
		kakaoLoginSuccess(checkToken);
	}
});

//카카오톡 로그인 클릭시 작동
function kakaoLogin(){
}

//카카오 로그인 성공 후 닉네임 저장 및 callback(토큰 유효성) 함수 호출
function kakaoLoginSuccess(callback){
}

//카카오톡 토큰 유효성 여부 체크 (로그인 상태면 index 로 이동)
//request = /kakao/token
function checkToken(nickname){
}