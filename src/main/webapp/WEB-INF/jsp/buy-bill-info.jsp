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


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">
</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover; background-color: gray;">

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center; width: 100%;" class="text-center ">


		<div dir="rtl" class="row mt-lg-4"
			style="width: 800px; margin: 0auto;">

			<div class="card border-warning text-warning w-50 shadow"
				style="margin: 0; position: absolute; top: 45%; left: 50%; transform: translate(-50%, -50%); max-width: 24rem;">

				<div class="card-header   border-warning  ">
					<h4>فاتورة شراء</h4>
				</div>

				<div class="card-body">

					<form method="POST" action="save-buy-bill-info">

						<div class="form-group font-weight-bold">
							<label>اسم المورد</label> <select
								class="form-control text-center bg-light font-weight-bold"
								name="companyId">

								<c:forEach var="companyTemp" items="${companiesList}">

									<option value="${companyTemp.id}">${companyTemp.name}</option>

								</c:forEach>

							</select>
						</div>


						<button type="submit" class="btn btn-outline-warning btn-lg w-100">
							فاتورة جديدة</button>

					</form>
				</div>

			</div>


		</div>



	</div>
</body>
</html>