let commentBtn = document.getElementById('button-addon2');
let commentInput = document.getElementById('commentInput');
let commentSection = document.getElementById('commentSection');
let commentCount = document.getElementById('commentCount');
let toggleCommentsLink = document.getElementById('toggleCommentsLink');
commentBtn.addEventListener('click', postComment);
toggleCommentsLink.addEventListener('click', function(e) {
    e.preventDefault();
    loadAndToggleComments();
});


window.addEventListener('load', loadAllComments);

function postComment(e) {
    fetch('http://localhost:8080/recipes/' + commentBtn.value + '/add-comment',
        {
            method: 'POST',
            body: commentInput.value
        }).then(r => {
        commentInput.value = '';
        loadAllComments();
    });
}

function loadAndToggleComments() {
    loadAllComments();
    toggleCommentsVisibility();
}
function toggleCommentsVisibility(event) {
    event.preventDefault();
    if (commentSection.style.display === 'none') {
        commentSection.style.display = 'block';
    } else {
        commentSection.style.display = 'none';
    }
}
function loadAllComments() {
    const allComments = [];
    fetch('http://localhost:8080/recipes/' + commentBtn.value + '/comments').then(r => r.json())
        .then(data => {
            for (let d of data) {
                allComments.push(d);
            }
        }).then(p => {
        commentSection.innerHTML = '';
        commentCount.innerText = 'See All Comments(' + allComments.length + ')';
        for (let c of allComments) {

            const div1 = document.createElement('div');
            div1.className = 'card p-3';
            const div2 = document.createElement('div');
            div2.className = 'd-flex justify-content-between align-items-center';
            const div3 = document.createElement('div');
            div3.className = 'user d-flex flex-row align-items-center gap-1';
            const image = document.createElement('img');
            image.src = c.userPic;
            image.className = 'user-img rounded-circle mr-2';
            image.width = 30;
            const span = document.createElement('span');
            span.className='gap-2 align-items-center d-flex flex-row';
            const smallUsername = document.createElement('small');
            smallUsername.className = 'font-weight-bold text-primary'
            smallUsername.innerText = c.userNames;
            const smallContent = document.createElement('small');
            smallContent.className = 'font-weight-bold';
            smallContent.innerText = c.content;
            const smallPostedOn = document.createElement('small');
            smallPostedOn.innerText = c.postedOn;
            div1.appendChild(div2);
            div2.appendChild(div3);
            div3.appendChild(image);
            div3.appendChild(span);
            span.appendChild(smallUsername);
            span.appendChild(smallContent);
            div2.appendChild(smallPostedOn);
            commentSection.appendChild(div1);
        }
    });


}