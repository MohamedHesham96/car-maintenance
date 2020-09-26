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
<title>كشف الأصناف</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="text-center ">

		<div dir="rtl" class="row">

			<div class="mr-lg-4">


				<div class="card border-primary mb-3" style="max-width: 20rem;">
					<div class="card-header ">
						<h4>البحث بالوحدة</h4>
					</div>
					<div class="card-body">
						<form method="POST" action="search-sell-bill-by-clientId">

							<div class="form-group">

								<select class="form-control text-center" name="clientId">

									<c:forEach var="clientTemp" items="${clientsList}">

										<option value="${clientTemp.id}">${clientTemp.name}</option>

									</c:forEach>

								</select>
							</div>

							<button type="submit"
								class="btn btn-outline-primary  btn-lg w-100">بحث</button>

						</form>

					</div>
				</div>

				<div class="card  border-primary w-100 mt-sm-4"
					style="max-width: 20rem;">
					<div class="card-header">
						<h4>البحث برقم الفاتورة</h4>
					</div>

					<div class="card-body">

						<form method="POST" action="search-sell-bill-by-id">

							<div class="form-group">
								<input class="form-control text-center" name="billId">

							</div>

							<button type="submit"
								class="btn form-control btn-outline-primary btn-lg w-100">بحث</button>

						</form>

					</div>
				</div>


			</div>




			<div class="mr-4 col-8 ">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered ">

						<thead>
							<tr>
								<th>الرقم</th>
								<th>الوحدة</th>
								<th>النوع</th>
								<th>التاريخ</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="billTemp" items="${billSellList}">

								<tr>

									<td>${billTemp.id}</td>
									<td>${billTemp.client.name}</td>
									<td>${billTemp.late ? "آجل" : "نقدي" }</td>
									<td>${billTemp.date}</td>

									<td><a
										href="change-sell-bill-to-update?sellBillId=${billTemp.id}"
										type="button" class="btn btn-outline-secondary btn-sm">تعديل</a>
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