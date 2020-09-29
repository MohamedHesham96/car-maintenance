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
<title>كشف الموردين</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="container">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-success mb-3" style="max-width: 20rem;">
					<div class="card-header">
						<h5>أضافة مورد</h5>
					</div>
					<div class="card-body">


						<form:form modelAttribute="company" method="post"
							action="add-new-company">

							<div class="form-group">
								<label>اسم المورد</label>
								<form:input path="name" class="form-control text-center" />
							</div>


							<button type="submit" class="btn btn-primary btn-lg w-100">اضافة
								مورد</button>

						</form:form>
					</div>
				</div>
				<br>

				<div class="card border-primary mb-3" style="max-width: 20rem;">
					<div class="card-header">
						<h5>البحث عن مورد</h5>
					</div>
					<div class="card-body">

						<form method="get" action="search-for-company">

							<div class="form-group">
								<label>اسم المورد</label> <input name="companyName"
									class="form-control text-center" />
							</div>


							<button type="submit" class="btn btn-success btn-lg w-100">بحث
								عن مورد</button>

						</form>

					</div>
				</div>

				<br>



			</div>

			<div class="mr-4 col-8">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered shadow">

						<thead>
							<tr>
								<th class="col-4">اسم المورد</th>
								<th class="col-4">الدين</th>
								<th class="col-1"></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="companyTemp" items="${companiesList}">

								<tr>

									<td>${companyTemp.name}</td>
									<td>${companyTemp.drawee}</td>
									<td class="col-3"><a type="button"
										class="btn btn-primary btn-sm"
										href="show-pays-company?companyId=${companyTemp.id}">الدفع</a>

										<button type="button" class="btn btn-success btn-sm">الفواتير</button>
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