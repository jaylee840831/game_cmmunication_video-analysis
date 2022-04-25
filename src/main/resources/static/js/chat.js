const url = '';
let stompClient;
let selectedUser;
let newMessages = new Map();
let isArray=true; //判斷是否是尚未接收訊息的列表
let isRead=false; //判斷訊息是否已讀

function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            if (selectedUser === data.fromLogin) {
				isArray=false;
                render(data.message, data.fromLogin);
            } else {
				isArray=true;
				const messageList = [];
				
				//訊息是否被讀過?  若是讀過，則前面的訊息刪除 newMessages=[]，再加入未接收的訊息
				if(isRead){
					//若傳訊息者不是正在與接收訊息者聊天，則會先把訊息依照傳訊息者的姓名存成map，等待接收訊息者點開接收
					newMessages.length=0;
					messageList.push(data.message);
	                newMessages.set(data.fromLogin, messageList);

					isRead=false;
				}
				else{
					//若傳訊息者不是正在與接收訊息者聊天，則會先把訊息依照傳訊息者的姓名存成map，等待接收訊息者點開接收
					if(newMessages.get(data.fromLogin)!=null){
						newMessages.get(data.fromLogin).forEach(element => messageList.push(element));
					}
					messageList.push(data.message);
	                newMessages.set(data.fromLogin, messageList);
				}
				
				$('span').remove('#newMessage_' + data.fromLogin);
                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="background-color: red; padding:5px 5px;border-top-left-radius: 50% 50%; border-top-right-radius: 50% 50%; border-bottom-right-radius: 50% 50%; border-bottom-left-radius: 50% 50%;"> '+newMessages.get(data.fromLogin).length+'</span>');
            }
        });
    });
}

function sendMsg(from, text) {
	    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: from,
        message: text
    }));
}

function registration(userName) {
    //let userName = document.getElementById("userName").value;
    $.get(url + "/registration/" + userName, function (response) {
        connectToChat(userName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!")
        }
    })
}

function selectUser(userName,image) {
	$("#history-ul").html('');
	isRead=true;
	registration(userName)
	
    console.log("selecting users: " + userName);
	//console.log("selecting users image: " + image);
    selectedUser = userName;
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
		if(isArray){
			for (const [key, value] of newMessages.entries()) {
				if(key===userName){
					for(var i=0;i<value.length;i++){
						render(value[i], userName);
					}
				}
			}
		}
		else{
        	render(newMessages.get(userName), userName);
		}
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append(userName);

	$('#selectUser-image').show().attr('src', 'data:image/png;base64,'+image);
}

function fetchAll() {
    $.get(url + "/fetchAllUser", function (response) {
        let users = response;
        let usersTemplateHTML = "";
       for (const [key, value] of Object.entries(users)) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" style="color:#E0E0E0;" onclick="selectUser(\'' + key + '\' , \'' + value + '\')"><li class="clearfix">\n' +
                '                <img src="data:image/png;base64,'+value+'" width="70px" height="70px" alt="avatar" style="border-top-left-radius: 50% 50%; border-top-right-radius: 50% 50%; border-bottom-right-radius: 50% 50%; border-bottom-left-radius: 50% 50%;" />\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + key + '" class="name">' + key + '</div>\n' +
                //'                    <div class="status">\n' +
                //'                        <i class="fa fa-circle offline"></i>\n' +
                //'                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}