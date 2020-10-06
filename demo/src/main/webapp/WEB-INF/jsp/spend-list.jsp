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
<title>المصاريف</title>

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
					<div
						class="card-header border-warning text-warning font-weight-bold">
						<h5>المصاريف</h5>
					</div>
					<div class="card-body font-weight-bold">

						<input name="companyName" value="الخزنة : ${bank.balance}"
							disabled="disabled"
							class="form-control btn-outline-warning text-center mb-2 font-weight-bold" />
						<input name="companyName"
							value="الخزنة اليوم : ${bank.balanceToday}" disabled="disabled"
							class="form-control btn-outline-warning text-center mb-2 font-weight-bold" />
						<input name="companyName"
							value=" اجمالي المصاريف  : ${spendTotal}" disabled="disabled"
							class="form-control btn-outline-warning text-center mb-2 font-weight-bold" />
						<input name="companyName"
							value=" مصاريف اليوم : ${spendTotalToday}" disabled="disabled"
							class="form-control btn-outline-warning text-center mb-2 font-weight-bold" />
						<input name="companyName"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							disabled="disabled"
							class="form-control btn-outline-warning text-center mb-2 font-weight-bold" />

						<form:form method="get" action="add-spend" modelAttribute="spend">

							<label class="font-weight-bold text-warning">المبلغ</label>
							<input name="amount"
								class="form-control text-center disable bg-light font-weight-bold" />

							<div class="form-group text-warning">
								<label>الملاحظة</label> <input name="note"
									class="form-control text-center disable bg-light font-weight-bold" />
							</div>


							<input type="submit"
								class="btn btn-danger font-weight-bold  w-100"
								value="سحب المبلغ" />

						</form:form>
					</div>
				</div>
				<br>
			</div>

			<div class="mr-4 col-8">

				<div style="width: 100%; height: 500px; overflow: auto;"
					class=" shadow">

					<table class="mh-50 table table-striped table-sm   shadow">

						<thead class="bg-primary"">
							<tr>
								<th class=" col-2">المبلغ</th>
								<th class="col-2">الملاحظة</th>
								<th class="col-2">التاريخ</th>
								<th class="col-2"></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="spendTemp" items="${spendsList}">

								<tr>

									<td class="border-primary p-2">${spendTemp.amount}</td>
									<td class="border-primary p-2">${spendTemp.note}</td>
									<td class="border-primary p-2">${spendTemp.date}</td>

									<td class="border-primary"><a
										${spendTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
										href="delete-spend?spendId=${spendTemp.id}"
										onclick="return confirm('هل انت متأكد من الإلغاء ؟')"
										class="btn btn-danger btn-sm "> إلغاء </a></td>
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