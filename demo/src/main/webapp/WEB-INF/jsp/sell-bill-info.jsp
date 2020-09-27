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

	<div style="text-align: center; width: 100%;" class="text-center">


		<div dir="rtl" class="row mt-lg-4" style="margin-right: 37.5%;">

			<div class="card border-primary w-50" style="max-width: 20rem;">

				<div class="card-header">
					<h4>فاتورة بيع</h4>
				</div>

				<div class="card-body">

					<form method="POST" action="save-sell-bill-info">

						<div class="form-group">
							<label>اسم الوحدة</label> <select
								class="form-control text-center" name="clientId">

								<c:forEach var="clientTemp" items="${clientsList}">

									<option value="${clientTemp.id}">${clientTemp.name}</option>

								</c:forEach>

							</select>
						</div>


						<label>آجل</label> <input type="checkbox" name="late">

						<button type="submit" class="btn btn-outline-primary btn-lg w-100">
							فاتورة جديدة</button>

					</form>
				</div>

			</div>


		</div>



	</div>
</body>
</html>