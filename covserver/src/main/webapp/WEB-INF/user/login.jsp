<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>로그인</title>
        <!-- <link rel="stylesheet" type="text/css" href="/resources/css/admin.css" /> -->
        <!-- <script src="/resources/js/jquery.js"></script> -->
        <!-- <script>
 function admin_check(){
	 if($.trim($("#admin_id").val()) == ""){
		 alert("관리자 아이디를 입력하세요!");
		 $("#admin_id").val("").focus();
		 return false;
	 }
	 if($.trim($("#admin_pwd").val()) == ""){
		 alert("관리자 비번을 입력하세요!");
		 $("#admin_pwd").val("").focus();
		 return false;
	 }
 }
</script> -->
    </head>

    <body>
        <div id="login_wrap">
            <h2 class="login_title">사용자 로그인</h2>
            <form method="post" action="loginComplete">
                <table id="login_table">
                    <tr>
                        <th>관리자 아이디</th>
                        <td>
                            <input name="userID" id="userID" size="14" tabindex="1" />
                        </td>
                        <th rowspan="2">
                            <input type="submit" value="로그인" />
                        </th>
                    </tr>
                    <tr>
                        <th>관리자 비밀번호</th>
                        <td>
                            <input type="password" name="userPasswd" id="userPasswd" size="14" tabindex="2" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>

    </html>