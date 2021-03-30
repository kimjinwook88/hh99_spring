	/* 	
	 	- 회원가입 버튼을 클릭하기
		- 닉네임, 비밀번호, 비밀번호 확인을 입력하기
		- 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
		- 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
		- 비밀번호 확인은 비밀번호와 정확하게 일치하기
		- 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다." 라는 에러메세지를 프론트엔드에서 보여주기
		- 회원가입 버튼을 누르고 에러메세지가 발생하지 않는다면 `로그인 페이지`로 이동시키기
	 */
 
	//가입하기
	//client, server 둘다 유효성 및 조건 체크
	function register(){
		let username = $('.username').val();
	    let password = $('.password').val();
	    let passwordNen = $('.password').val();
	    let passwordConfirm = $('.passwordConfirm').val();
	    let data = {'username': username, 'password': password, 'passwordNen': passwordNen, 'passwordConfirm': passwordConfirm};
	    
	    //닉네임 체크
		if (checkName() != "") { 
            alert(checkName());
            return false;
        }
        
		//비밀번호 체크
        if (checkPass() != "") {
            alert(checkPass());
            return false;
        }
        
       // 닉네임 중복 체크 (중복체크를 위해 db조회 필요 ajax 활용)
       // ajax 특성상 비동기 처리이기 때문에 중복확인 ajax 후에 회원가입 ajax가 실행되지 않도록 콜백으로 처리.
        checkNameDuplication(function(response){ 
    		if(response.username){
    			alert("닉네임 중복 입니다.");
    			return false;
    		}else{
    			//회원가입 ajax 호출
    		}
    	});
	}
	
	// 닉네임 체크 (정규식 활용)
	function checkName(){
		let err_msg = "";
		return err_msg;
	}
	
	// 닉네임 중복 체크 (ajax db 조회 체크 후 callback 함수로 response 전달)
	function checkNameDuplication(callback){
	}
	
	//비밀번호 체크
	function checkPass(){
		let err_msg = "";
		return err_msg;
	}