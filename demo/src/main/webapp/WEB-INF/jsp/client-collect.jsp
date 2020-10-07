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
<title>تحصيل الوحدة</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-warning mr-lg-5" style="max-width: 20rem;">
					<div class="card-header border-warning text-warning">
						<h4>التحصيل</h4>
					</div>
					<div class="card-body text-warning font-weight-bold">

						<div class="form-group">
							<label>اسم الوحدة</label> <input name="companyName"
								value="${client.name}" disabled="disabled"
								class="form-control btn-outline-warning text-center" />
						</div>

						<div class="form-group">
							<label>الدين</label> <input name="companyName"
								value="${client.drawee}" disabled="disabled"
								class="form-control btn-outline-warning text-center font-weight-bold" />
						</div>

						<form method="get" action="add-client-collect">
							<input hidden="" name="clientId" value="${client.id}">
							<div class="form-group">
								<label>المبلغ</label> <input name="amount"
									class="form-control text-center disable bg-light font-weight-bold" />
							</div>

							<input type="submit" class="btn btn-outline-warning btn-lg w-100"
								value="تحصيل المبلغ" />

						</form>

					</div>
				</div>
				<br>
			</div>

			<div class="mr-4 col-8 ">
				<div style="position: relative; height: 500px; overflow: auto;">
					<table
						class="table table-striped table-sm  shadow font-weight-bold">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr>
								<th class="col-2">المبلغ</th>
								<th class="col-2">التاريخ</th>
								<th class="col-2">الرصيد</th>
								<th class="col-2">العملية</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="collectTemp" items="${client.collectList}">

								<tr>

									<td class="border-primary pt-2">${collectTemp.amount}</td>
									<td class="border-primary pt-2">${collectTemp.date}</td>
									<td class="border-primary pt-2">${collectTemp.balanceNow}</td>

									<td class="border-primary"><a
										${collectTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
										href="delete-client-collect?clientId=${client.id}&collectId=${collectTemp.id}"
										onclick="return confirm('هل انت متأكد من الإلغاء ؟')"
										class="btn btn-danger btn-sm font-weight-bold"> إلغاء </a></td>

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