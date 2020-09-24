<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>كشف الوحدات</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="container">
		<div dir="rtl" class="row">

			<div>


				<form method="get" action="search-for-client">

					<div class="form-group">
						<label>اسم الوحدة</label> <input name="clientName"
							class="form-control text-center" />
					</div>


					<button type="submit" class="btn btn-success btn-lg w-100">

						بحث عن وحدة</button>

				</form>
				<br>

				<hr>

				<form:form modelAttribute="client" method="post"
					action="add-new-client">

					<div class="form-group">
						<label>اسم الوحدة</label>
						<form:input path="name" class="form-control text-center" />
					</div>


					<button type="submit" class="btn btn-primary btn-lg w-100">

						اضافة وحدة</button>

				</form:form>
			</div>

			<div class="mr-4 col-8">
				<table class="table table-striped table-sm table-bordered shadow">

					<thead>
						<tr>
							<th>اسم الوحدة</th>
							<th>الدين</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="clientTemp" items="${clientsList}">

							<tr>

								<td>${clientTemp.name}</td>
								<td>${clientTemp.drawee}</td>

							</tr>

						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>

	</div>
</body>
</html>