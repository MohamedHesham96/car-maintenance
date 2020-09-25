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

	<div style="text-align: center;" class="">
		<div dir="rtl" class="row">


			<div class="mr-lg-5">





				<div>

					<div class="card border-primary mb-3" style="max-width: 20rem;">
						<div class="card-header">
							<h5>أضافة وحدة</h5>
						</div>
						<div class="card-body">


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
					</div>

					<br>

					<div class="card border-success mb-3" style="max-width: 20rem;">
						<div class="card-header">
							<h5>بحث وحدة</h5>
						</div>
						<div class="card-body">

							<form method="get" action="search-for-client">

								<div class="form-group">
									<label>اسم الوحدة</label> <input name="clientName"
										class="form-control text-center" />
								</div>


								<button type="submit" class="btn btn-success btn-lg w-100">

									بحث عن وحدة</button>

							</form>

						</div>
					</div>

				</div>

			</div>
			<div class="mr-4 col-8">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

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

									<td class="col-4">${clientTemp.name}</td>
									<td class="col-4">${clientTemp.drawee}</td>
									<td class="col-3"><a type="button"
										class="btn btn-primary btn-sm"
										href="show-collects-client?clientId=${clientTemp.id}"
										onclick="showUpdateForm(this,${itemTemp.id})">التحصيل</a>

										<button type="button" class="btn btn-success btn-sm"
											onclick="showUpdateForm(this,${itemTemp.id})">الفواتير</button>
									</td>


								</tr>

							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>
</html>