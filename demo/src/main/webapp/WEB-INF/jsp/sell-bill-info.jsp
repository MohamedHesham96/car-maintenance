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
<title>فاتورة بيع جديدة</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">


</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">


	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center; width: 100%;" class="text-center ">


		<div dir="rtl" class="row mt-lg-4 "
			style="width: 800px; margin: 0auto;">

			<div class="card border-warning w-50 font-weight-bold shadow"
				style="margin: 0; height: 240px; position: absolute; top: 45%; left: 50%; transform: translate(-50%, -50%); max-width: 24rem;">

				<div class="card-header border-warning text-warning">
					<h4>فاتورة بيع</h4>
				</div>

				<div class="card-body bg-dark text-warning ">

					<form method="POST" action="save-sell-bill-info">

						<div class="form-group ">
							<label>اسم العميل</label> <select
								class="form-control text-center  font-weight-bold bg-light"
								name="clientId">

								<c:forEach var="clientTemp" items="${clientsList}">

									<option value="${clientTemp.id}">${clientTemp.name}</option>

								</c:forEach>

							</select>
						</div>

						<div class="row mt-2">

							<input type="submit" style="background-color: blue;"
								class="btn font-weight-bold btn-lg col-lg-5 mr-3" name="type"
								value="نقدي"> <input type="submit"
								class="btn  btn-danger font-weight-bold btn-lg col-lg-5 mr-3"
								name="type" value="آجـل">

						</div>
					</form>
				</div>

			</div>


		</div>



	</div>
</body>
</html>