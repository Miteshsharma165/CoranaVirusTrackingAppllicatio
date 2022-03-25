<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CoronaVirus Tracker</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

	<div class="container p-0">

		<h1>Corona Virus Tracker</h1>
		<p class="lead">The application list the current number of casses
			reported across the globe</p>


		<table class=" table table-striped">
			<tr>
				<td colspan="4">
					<div class="jumbotron">
						<h1 class="display-4">${totalNumberofcasesToday}</h1>
						<p class="lead">Total Number of cases reported as Today</p>
					</div>
					<hr></hr>
					<span class="text-dark">Total Number Of Cases Increased From Previous Day :<span class="text-danger">  ${totalNumberOfIncresesCase}</span></span>
				</td>

			</tr>
			<tr>
				<td scope="row">Country</td>
				<td scope="row">state</td>
				<td scope="row">Total Number Of Cases</td>
				<td scope="row">Increased Cases</td>
			<tr>
				<c:forEach items="${localStats}" var="localstat">
					<tr>
						<td scope="col">${localstat.country}</td>
						<td scope="col">${localstat.state}</td>
						<td scope="col">${localstat.latestTotalCases}</td>
						<td scope="col">${localstat.increasedCasesPerDay}</td>
					<tr>
				</c:forEach>
		</table>

	</div>

</body>
</html>