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
<title>كشف فواتير - [${client.name}]</title>


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
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
							value="${client.name}" disabled="disabled"
							class="form-control btn-outline-primary text-center" />
					</div>

					<div class="form-group">
						<label>الدين</label> <input name="companyName"
							value="${client.drawee}" disabled="disabled"
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
								<th class="col-3">النوع</th>
								<th class="col-3">التاريخ</th>
								<th class="col-2"></th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="billTemp" items="${client.billSellList}">

								<tr>

									<td>${billTemp.id}</td>
									<td>${billTemp.late ? "آجل" : "نقدي" }</td>
									<td>${billTemp.date}</td>

									<td><a
										href="change-sell-bill-to-update?sellBillId=${billTemp.id}"
										type="button" class="btn btn-priamry btn-sm"
										${ billTemp.updater != null ? 'hidden' :  '' }> تعديل </a></td>
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