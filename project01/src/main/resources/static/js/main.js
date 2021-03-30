$(document).ready(function () {
    showHide()
    $('.card__container').empty()
    getArticle()
})

function showHide() {
    $('#posting-pen').on('click', function () {
        $('.contents__container').hide();
        $('.posting__contianer').show();
    })

    $('#cancel-btn').on('click', function () {
        $('.posting__contianer').hide();
        $('.contents__container').show();

        $('.input-name').val('')
        $('.input-title').val('')
        $('.posting-textarea').val('')
    })
}

function postArticle() {
    let username = $('.input-name').val()
    let title = $('.input-title').val()
    let contents = $('.posting-textarea').val()
    if (username == '') {
        alert('이름을 적어주세요!')
        return;
    } else if (title == '') {
        alert('제목을 적어주세요!')
        return
    } else if (contents == '') {
        alert('내용을 적어주세요!')
        return
    }
    let data = {'username': username, 'title': title, 'contents': contents}
    $.ajax({
        type:'POST',
        url:'/api/articles',
        contentType:'application/json',
        data: JSON.stringify(data),
        success: function (response){
            alert("게시물 작성이 완료 되었습니다!")
            location.href="/index";
        }
    })
}

function getArticle(){
    $.ajax({
        type:'GET',
        url:'/api/articles',
        success: function (response){
            for(let i=0; i<response.length; i++){
                let articles = response[i]
                addArticle(articles['id'], articles['username'], articles['title'], articles['contents'], articles['modifiedAt'])
            }
        }
    })
}

function addArticle(id, username, title, contents, modifiedAt){
    let tempHtml = `<div class="card">
                    <div class="card-header">
                        <a href="/detail?id=${JSON.stringify(id)}" class="article-title"><h2>${title}</h2></a>
                    </div>
                    <div class="card-body">
                        <p>
                            ${contents}
                        </p>
                    </div>
                    <div class="card-footer">
                        <p class="post-author"> ${username} <span class="post-date">|  ${modifiedAt}</span></p>
                    </div>
                </div>`
    $('.card__container').append(tempHtml)
}

// 로그 아웃
// spring security 에서 /logout 을 요청하면 세션처리
// kakao 사용자도 함께 logout 처리
function logout(){
	Kakao.init('인증키');
}