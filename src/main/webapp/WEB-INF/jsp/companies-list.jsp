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


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">



</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">


	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="container">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-warning mb-3" style="max-width: 20rem;">
					<div class="card-header border-warning text-warning">
						<h5>أضافة مورد</h5>
					</div>
					<div class="card-body">


						<form:form modelAttribute="company" method="post"
							action="add-new-company">

							<div class="form-group text-warning font-weight-bold">
								<label>اسم المورد</label>
								<form:input path="name"
									class="form-control text-center bg-light" />
							</div>


							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100">اضافة مورد</button>

						</form:form>
					</div>
				</div>
				<br>

				<div class="card border-warning mb-3" style="max-width: 20rem;">
					<div class="card-header border-warning text-warning">
						<h5>البحث عن مورد</h5>
					</div>
					<div class="card-body">

						<form method="get" action="search-for-company">

							<div class="form-group text-warning font-weight-bold">
								<label>اسم المورد</label> <input name="companyName"
									class="form-control text-center bg-light" />
							</div>


							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100">بحث عن
								مورد</button>

						</form>

					</div>
				</div>

			</div>

			<div class="mr-4 col-9">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table class="table table-dark table-striped table-sm  shadow ">


						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr>
								<th class="col-3">اسم المورد</th>
								<th class="col-2">الدين</th>
								<th class="col-3">العميلة</th>
							</tr>
						</thead>

						<tbody class="font-weight-bold">
							<c:forEach var="companyTemp" items="${companiesList}">

								<tr>

									<td class="border-primary pt-2">${companyTemp.name}</td>
									<td dir="ltr" class="border-primary pt-2">${companyTemp.drawee}</td>
									<td class="border-primary "><a type="button"
										class="btn btn-warning btn-sm font-weight-bold"
										href="show-pays-company?companyId=${companyTemp.id}">الدفع</a>

										<a type="button"
										class="btn btn-primary btn-sm font-weight-bold"
										href="show-company-bills-list?companyId=${companyTemp.id}">الفواتير</a>
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