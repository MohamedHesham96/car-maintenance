<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>كشف فواتير البيع</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="text-center mr-lg-4">

		<div dir="rtl" class="row">

			<div class="mr-lg-4">

				<div class="card border-primary mb-3" style="max-width: 20rem;">
					<div class="card-header ">
						<h5>البحث بالوحدة</h5>
					</div>
					<div class="card-body">
						<form method="POST" action="search-buy-bill-by-companyId">

							<div class="form-group">

								<select class="form-control text-center" name="companyId">

									<c:forEach var="companyTemp" items="${companiesList}">

										<option value="${companyTemp.id}">${companyTemp.name}</option>

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
						<h5>البحث برقم الفاتورة</h5>
					</div>

					<div class="card-body">

						<form method="POST" action="search-buy-bill-by-id">

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

							<c:forEach var="billTemp" items="${billBuyList}">

								<tr>

									<td>${billTemp.id}</td>
									<td>${billTemp.company.name}</td>
									<td>${billTemp.date}</td>

									<td><a
										href="change-buy-bill-to-update?buyBillId=${billTemp.id}"
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