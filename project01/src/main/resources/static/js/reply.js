
$(document).ready(function () {
	setTimeout(function(){
		 getReply();
	},200);
});

function postReply(){
	 if(global_username == ""){
		alert("로그인 해주세요.");
		return false;
	 }

	 let data = {'username': global_username, 'article_id': parseInt($("#article_id").attr("data-id")), 'contents': $("#replyContent").val()};
	 
	 $.ajax({
        type:'POST',
        url:'/api/reply',
        contentType: 'application/json',
        data: JSON.stringify(data),
        async : false,
        beforeSend : function(xhr, opts) {
           	if(data.contents == null || data.contents == ""){
				alert("댓글 내용을 입력해주세요.");
				return false;
	 		}
        },
        success: function (response){
            alert("댓글을 등록했습니다.");
            getReply();
        },
        error : function(err){
        	
        }
    });
}

function getReply(){
	 let article_id = parseInt($("#article_id").attr("data-id"));
	 $.ajax({
        type:'GET',
        url:`/api/reply`,
        data : {"article_id" : article_id},
        beforeSend : function(xhr, opts) {
        },
        success: function (response){
			console.log(response);
        	addReply(response);
        }
    });
}

function addReply(data){
	$(".replyContents").remove();
	for(i=0; i<data.length; i++){
		var html = "";
		html += "<div class='replyContents'>";
		if(data[i].username != global_username){
			html += "<input type='text' value='"+data[i].contents+"' readonly/ > ";
		}else{
			html += "<input type='text' value='"+data[i].contents+"'/ > ";
		}
		html += "<span>"+data[i].username+"</span> ";
		if(data[i].username == global_username){
		html += "<input type='button' value='수정' onclick='updateReply("+data[i].id+", this);'/>";
		html += "<input type='button' value='삭제' onclick='deleteReply("+data[i].id+");'/>";
		}
		html += "</div>";
		
		$(".content__container").append(html);
	}
}

function deleteReply(id){
    $.ajax({
    	method: "DELETE",
        url:`/api/reply/${id}`,
		beforeSend : function(xhr, opts) {
			if(!confirm("삭제하시겠습니까?")){
				return false;
			}
        },
        success:function (response){
            alert("삭제 되었습니다.")
           getReply();
        }
    });
}

function updateReply(id, obj){
	let data = {'username': global_username, 'article_id': parseInt($("#article_id").attr("data-id")), 'contents': $(obj).prev().prev().val()};
 	
    $.ajax({
    	method: "PUT",
        url:`/api/reply/${id}`,
        contentType: 'application/json',
        data: JSON.stringify(data),
		beforeSend : function(xhr, opts) {
			if(!confirm("수정하시겠습니까?")){
				return false;
			}
			if(data.contents == null || data.contents == ""){
				alert("댓글 내용을 입력해주세요.");
				return false;
	 		}
        },
        success:function (response){
            alert("수정 되었습니다.")
           getReply();
        }
    });
}

