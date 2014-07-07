<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>New note</h3>
<a href="add">Add new note</a>

	<table class="data">
		<tr>
			<th>note id</th>
			<th>note</th>
			<th>edit note</th>
			<th>delete note</th>
		</tr>
		<c:forEach items="${notes}" var="note">
			<tr>
				<td>${note.id}</td>
				<td>${note.text}</td>
				<td><a href="edit/${note.id}">edit</a></td>
				<td><a href="delete/${note.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
</body>
</html>