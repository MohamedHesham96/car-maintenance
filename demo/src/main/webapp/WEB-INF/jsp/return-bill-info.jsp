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
<title>فاتورة مرتجع جديدة</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center; width: 100%;" class="text-center ">


		<div dir="rtl" class="row mt-lg-4 "
			style="width: 800px; margin: 0 auto;">

			<div class="card border-warning w-50 font-weight-bold shadow"
				style="margin: 0; position: absolute; top: 45%; left: 50%; transform: translate(-50%, -50%); max-width: 24rem;">

				<div class="card-header border-warning text-warning ">
					<h4>فاتورة مرتجع</h4>
				</div>

				<div class="card-body ">

					<form method="POST" action="save-return-bill-info">

						<div class="form-group text-warning">
							<label>اسم العميل</label> <select
								class="form-control text-center bg-light font-weight-bold"
								name="clientId">

								<c:forEach var="clientTemp" items="${clientsList}">

									<option value="${clientTemp.id}">${clientTemp.name}</option>

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