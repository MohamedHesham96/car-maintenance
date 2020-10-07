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
<title>دفع لمورد</title>


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">


</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="width: 100%; text-align: center;" class=" ">

		<div dir="rtl" class="row mr-3">

			<div>

				<div class="card border-warning  mr-lg-5" style="max-width: 20rem;">
					<div class="card-header border-warning text-warning">
						<h4>الدفع</h4>
					</div>
					<div class="card-body text-warning font-weight-bold">

						<div class="form-group">
							<label>اسم المورد</label> <input name="companyName"
								value="${company.name}" disabled="disabled"
								class="form-control btn-outline-warning font-weight-bold text-center" />
						</div>

						<div class="form-group">
							<label>الدين</label> <input value="${company.drawee}"
								disabled="disabled"
								class="form-control btn-outline-warning font-weight-bold text-center" />
						</div>

						<form method="get" action="add-company-pay">
							<input hidden="" name="companyId" value="${company.id}">
							<div class="form-group">
								<label>المبلغ</label> <input name="amount"
									class="form-control text-center disable bg-light font-weight-bold " />
							</div>

							<input type="submit"
								class="btn btn-outline-warning btn-lg w-100 "
								value="تحصيل المبلغ" />

						</form>

					</div>
				</div>
				<br>
			</div>


			<div class="mr-4 col-8 ">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table class="table table-striped table-sm  ">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr>
								<th class="col-1">المبلغ</th>
								<th class="col-1">التاريخ</th>
								<th class="col-1">الرصيد</th>
								<th class="col-1">العميلة</th>
							</tr>
						</thead>

						<tbody class="font-weight-bold">
							<c:forEach var="payTemp" items="${company.payList}">

								<tr>

									<td class="border-primary pt-2 pb-2">${payTemp.amount}</td>
									<td class="border-primary pt-2 pb-2">${payTemp.date}</td>
									<td class="border-primary pt-2 pb-2">${payTemp.balanceNow}</td>

									<td class="border-primary"><a
										${payTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
										href="delete-company-pay?companyId=${company.id}&payId=${payTemp.id}"
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