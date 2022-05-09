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


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="text-center ">

		<div dir="rtl" class="row">

			<div class="card border-warning mr-lg-5" style="max-width: 20rem;">
				<div class="card-header border-warning text-warning">
					<h4>سجل فواتير</h4>
				</div>
				<div class="card-body text-warning font-weight-bold">

					<div class="form-group ">
						<label>المورد</label> <input name="companyName"
							value="${company.name}" disabled="disabled"
							class="form-control btn-outline-warning text-center font-weight-bold" />
					</div>

					<div class="form-group ">
						<label>الدين</label> <input name="companyName"
							value="${company.drawee}" disabled="disabled"
							class="form-control btn-outline-warning text-center font-weight-bold" />
					</div>

				</div>
			</div>


			<div class="mr-4 col-8">

				<div class="shadow "
					style="position: relative; height: 500px; overflow: auto;">

					<table
						class="table table-dark table-striped table-sm font-weight-bold ">

						<thead class="bg-primary ">
							<tr>
								<th class="col-3">رقم الفاتورة</th>
								<th class="col-2">التاريخ</th>
								<th class="col-3">العميلة</th>
							</tr>
						</thead>

						<tbody class="shadow">

							<c:forEach var="billTemp" items="${company.billBuyList}">

								<tr>

									<td class="border-primary pt-2">${billTemp.id}</td>
									<td class="border-primary pt-2">${billTemp.date}</td>

									<td class="border-primary "><a
										href="change-buy-bill-to-update?buyBillId=${billTemp.id}"
										type="button" class="btn btn-primary btn-sm font-weight-bold"
										${ billTemp.updater != null ? 'hidden' :  '' }>تعديل</a></td>
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