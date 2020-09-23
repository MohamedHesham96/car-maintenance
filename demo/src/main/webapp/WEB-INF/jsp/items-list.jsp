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

	<div style="text-align: center;" class="container">
		<div dir="rtl" class="row">

			<form:form modelAttribute="item" method="post" action="add-new-item">

				<!-- 				<div class="form-group"> -->
				<!-- 					<label>الجهة</label> -->
				<%-- 					<form:select class="form-control text-center" path="companyId"> --%>
				<%-- 						<form:options items="${companiesList}" itemLabel="name" /> --%>
				<%-- 					</form:select> --%>
				<!-- 				</div> -->

				<div class="form-group">
					<label>اسم الصنف</label>
					<form:input path="name" class="form-control text-center" />
				</div>

				<div class="form-group">
					<label>الكمية</label>
					<form:input path="quantity" class="form-control text-center" />
				</div>

				<div class="form-group">
					<label>سعر الشراء</label>
					<form:input path="buyPrice" class="form-control text-center" />
				</div>

				<div class="form-group">
					<label>سعر البيع</label>
					<form:input path="sellPrice" class="form-control text-center" />
				</div>

				<button type="submit" class="btn btn-primary btn-lg w-100">

					اضافة الصنف</button>

			</form:form>


			<div class="mr-4 col-8">
				<table class="table table-striped">

					<thead>
						<tr>
							<th>الكود</th>
							<th>الصنف</th>
							<th>الكمية</th>
							<th>سعر الشراء</th>
							<th>سعر البيع</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="itemTemp" items="${itemsList}">

							<tr>
								<th>${itemTemp.id}</th>
								<td>${itemTemp.name}</td>
								<td>${itemTemp.quantity}</td>
								<td>${itemTemp.buyPrice}</td>
								<td>${itemTemp.sellPrice}</td>

							</tr>

						</c:forEach>

					</tbody>
				</table>

			</div>
		</div>

	</div>
</body>
</html>