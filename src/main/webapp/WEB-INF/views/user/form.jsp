<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link rel="shortcut icon" href="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create User</title>
</head>
<body>
    <div>
        <h1>New</h1>
        <form:form action="save" method="post" modelAttribute="user">
            <table>
                <tr>
                    <td>ID</td>
                    <td><form:input path="id" /></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><form:input path="address" /></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>