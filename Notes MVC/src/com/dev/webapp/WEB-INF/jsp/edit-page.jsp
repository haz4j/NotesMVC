<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h3>Text of the edited note</h3>

	<form:form action="${pageContext.request.contextPath}/edited/${note.id}" method="POST" commandName="note">

		<table>
			<tr>
				<td>text :</td>
				<td><form:textarea path="text" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Cancel" value="Cancel" /></td>
				<td><input type="submit" name="OK" value="OK" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>