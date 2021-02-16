// 删除消息
function delMsg(id) {
	if(confirm("删除消息后将无法恢复，确定要删除这条消息吗？")) {
		location.href = "MessageServlet?action=del&id="+id;
	}
}

// 回信
function replyMsg(id) {
	location.href = "MessageServlet?action=reply&id="+id;
}

function makeMsg() {
	location.href = "MessageServlet?action=make_msg";
}

function readMsg(id) {
	location.href = "MessageServlet?action=read_msg&id="+id;
}

function logout() {
	location.href = "LogoutServlet";
}

function page(pageNum) {
	location.href = "MessageServlet?action=to_list&currentPage="+pageNum;
}
//页面跳转
function jumpPage() {
	var pageNumInput = document.getElementById("pageNum");
	var pageNum = pageNumInput.value;
	if(isNaN(pageNum)) {
		alert("填写的页码不合法！");
		return;
	}
	page(pageNum);
}