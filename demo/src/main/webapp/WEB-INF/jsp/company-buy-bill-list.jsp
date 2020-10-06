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
<title>كشف فواتير - [${company.name}]</title>


<link href="webjars/bootswatch/4.5.2/dist/darkly/_bootswatch.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/_variables.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="text-center ">

		<div dir="rtl" class="row">

			<div class="card border-primary  mr-lg-5" style="max-width: 20rem;">
				<div class="card-header">
					<h4>سجل فواتير</h4>
				</div>
				<div class="card-body">

					<div class="form-group">
						<label> الوحدة</label> <input name="companyName"
							value="${company.name}" disabled="disabled"
							class="form-control btn-outline-primary text-center" />
					</div>

					<div class="form-group">
						<label>الدين</label> <input name="companyName"
							value="${company.drawee}" disabled="disabled"
							class="form-control btn-outline-primary text-center" />
					</div>

				</div>
			</div>


			<div class="mr-4 col-8 ">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered ">

						<thead>
							<tr>
								<th class="col-3">الرقم</th>
								<th class="col-3">التاريخ</th>
								<th class="col-1"></th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="billTemp" items="${company.billBuyList}">

								<tr>

									<td>${billTemp.id}</td>
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