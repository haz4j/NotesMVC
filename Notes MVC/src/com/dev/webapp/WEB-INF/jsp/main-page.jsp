<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<!-- <link href="http://localhost:8080/Notes_MVC/css/style.css" type="text/css" rel="stylesheet" /> -->
<style>

body{
font-family: Tahoma, sence-serif;
}
.data td{border-bottom: 1px solid #ccc ; padding: 5px 0;}
.data{ width: 800px; margin: 0 auto;}
h3 {text-align: center;}
p {text-align: center;}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notebook</title>
</head>
<body>

<h3>Notebook</h3>
<p><a href="add">Add new note</a></p>

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