<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>회원가입</title>
    </head>

    <body>
        <div id="login_wrap">
            <h2 class="signup_title">회원가입</h2>
            <form method="post" action="signupComplete">
                <table id="signup_table">
                    <tr>
                        <th>아이디</th>
                        <td>
                            <input name="userID" id="userID" size="14" tabindex="1" />
                        </td>
                        <th rowspan="2">
                            <input type="submit" value="signup" />
                            <input type="reset" value="reset"/>
                        </th>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" name="userPasswd" id="userPasswd" size="20" tabindex="2" />
                        </td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td>
                            <input name="userName" id="userName" size="8" tabindex="4" />
                        </td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td>
                            <input name="userPhone" id="userPhone" size="8" tabindex="5" />
                        </td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <input name="userEmail" id="userEmail" size="30" tabindex="6" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>

    </html>