<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>فاتورة شراء</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">

</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">


	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;"
		class="text-center mr-lg-4 text-warning">

		<div dir="rtl" class="row w-100">

			<div class="mr-lg-4 ">

				<div class="card border-warning mb-3" style="max-width: 20rem;">
					<div class="card-header  border-warning  ">
						<h5>البحث بالمورد</h5>
					</div>
					<div class="card-body ">
						<form method="POST" action="search-buy-bill-by-companyId">

							<div class="form-group">

								<select
									class="form-control text-center bg-light font-weight-bold"
									name="companyId">

									<c:forEach var="companyTemp" items="${companiesList}">

										<option value="${companyTemp.id}">${companyTemp.name}</option>

									</c:forEach>

								</select>
							</div>

							<button type="submit"
								class="btn btn-outline-warning  btn-lg w-100">بحث</button>

						</form>

					</div>
				</div>

				<div class=" card border-warning  mb-3" style="max-width: 20rem;">
					<div class="card-header  border-warning  ">
						<h5>البحث برقم الفاتورة</h5>
					</div>

					<div class="card-body text-warning">


						<form method="POST" action="search-buy-bill-by-id">

							<div class="form-group">
								<input
									class="form-control text-center bg-light font-weight-bold"
									name="billId">

							</div>

							<button type="submit"
								class="btn form-control btn-outline-warning btn-lg w-100">بحث</button>

						</form>

					</div>
				</div>


			</div>



			<div class="mr-4 col-8 ">

				<div class="shadow"
					style="position: relative; height: 500px; overflow: auto;">

					<table
						class="table table-dark  table-striped table-sm shadow font-weight-bold">

						<thead class="bg-primary">
							<tr>
								<th class="col-2">الرقم</th>
								<th class="col-2">المورد</th>
								<th class="col-2">التاريخ</th>
								<th class="col-2">العميلة</th>
							</tr>
						</thead>

						<tbody class="shadow">

							<c:forEach var="billTemp" items="${billBuyList}">

								<tr>

									<td class="border-primary pt-2">${billTemp.id}</td>
									<td class="border-primary pt-2">${billTemp.company.name}</td>
									<td class="border-primary pt-2">${billTemp.date}</td>

									<td class="border-primary"><a
										${ billTemp.updater != null ? 'hidden' :  '' }
										href="view-buy-bill?buyBillId=${billTemp.id}" type="button"
										class="btn btn-warning btn-sm font-weight-bold">عرض</a> <a
										${ billTemp.updater != null ? 'hidden' :  '' }
										href="change-buy-bill-to-update?buyBillId=${billTemp.id}"
										type="button" class="btn btn-primary btn-sm font-weight-bold">تعديل</a></td>
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