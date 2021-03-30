$(document).ready(function () {
	setTimeout(function(){
		 getReply();
	},200);
});


/*
	댓글 입력
	로그인 체크, 내용 입력 여부 체크, post ajax 호출 
*/
function postReply(){
}

/*
	댓글 조회
*/
function getReply(){
}

/*
	조회 후 댓글 html 그리기
	로그인한 사용자와 댓글작성자 비교하여 수정, 삭제 버튼 노출 여부 판단
*/
function addReply(data){
	$(".replyContents").remove();
	for(i=0; i<data.length; i++){
		var html = "";
		html += "<div class='replyContents'>";
		// 여기에 html 추가 
		html += "</div>";
		$(".content__container").append(html);
	}
}

/* 댓글 삭제 */
function deleteReply(id){
}

/* 댓글 수정 */
function updateReply(id, obj){
}

