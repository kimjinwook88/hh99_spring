$(document).ready(function () {
    backHome()
    getId()
    showHide()
    deleteArticle()
})

function getId() {
    let id = location.search.split('=')[1]
    return getDetail(id)
}

function backHome() {
    $('#home').on('click', function () {
        window.location.href = "/"
    })
}

function getDetail(id) {
    let idx = id
    $.ajax({
        type: 'GET',
        url: `/api/detail/${idx}`,
        success: function (response) {
            addDetail(response['id'], response['username'], response['title'], response['contents'], response['modifiedAt'])
        }
    })
}

function addDetail(id, username, title, contents, modifiedAt) {
    let tempHtml = `<div class ="detail" id="article_id" data-id=${id}>
    <div class="content-header">
          <h1 class="title">${title}</h1>
          <p class="post-author">
            <span class="username">${username}</span> <span class="post-date">| ${modifiedAt}</span>
          </p>
          <hr />
        </div>
        <div class="content-body">
          <p class="contents">
            ${contents}
          </p>
        </div>
    </div>`
    $('.content__container').append(tempHtml);

	let replyHtml = "";
	replyHtml +=  '<div id="replyDiv" style="padding-bottom:15px;">';
	replyHtml +=  '<input type="text" id="replyContent" placeholder="댓글을 입력해 주세요.">';
	replyHtml +=  '<input type="button" value="댓글작성" onclick="postReply();">';
	replyHtml +=  '</div>';
	 $('.content__container').append(replyHtml);
}

function showHide() {
    $('#edit').on('click', function () {
        $('.detail').hide()
        $('.detail__edit').show()
        let title = $('.title').text()
        let contents = $('.contents').text().trim()
        let author = $('.post-author').text().trim()
        $('.post-author-edit').text(author)
        $('.detail-input').val(title)
        $('.detail-textarea').val(contents)
    })
    $('.cancel').on('click', function () {
        $('.detail').show()
        $('.detail__edit').hide()
        $('.detail-input').val('')
        $('.detail-textarea').val('')
    })
}

function editArticle() {
    let id = location.search.split('=')[1]
    let title = $('.detail-input').val();
    let contents = $('.detail-textarea').val();
    let username = $('.username').text()
    if (title == '') {
        alert("수정하실 제목을 적어 주세요!")
        return
    }
    if (contents == '') {
        alert("수정하실 내용을 적어 주세요!")
        return;
    }
    let data = {'username': username, 'title': title, 'contents': contents}
    $.ajax({
        type: "PUT",
        url: `/api/articles/${id}`,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (response) {
            alert("수정이 완료되었습니다!")
            window.location.reload();
        }
    })
}

function deleteArticle() {
    $('#delete').on('click', function () {
        let id = location.search.split('=')[1]
        $.ajax({
            type: "DELETE",
            url:`/api/articles/${id}`,
            success:function (response){
                alert("삭제 되었습니다.")
                window.location.href = "/"
            }
        })
    })
}