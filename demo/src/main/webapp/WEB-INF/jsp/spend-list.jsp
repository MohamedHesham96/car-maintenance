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

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-primary mr-lg-5" style="max-width: 20rem;">
					<div class="card-header">
						<h5>المصاريف</h5>
					</div>
					<div class="card-body">

						<input name="companyName" value="الخزنة : ${150000}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value="الدين الخارجي : ${25100}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" />

						<form:form method="get" action="add-spend" modelAttribute="spend">

							<div class="form-group">
								<label>المبلغ</label> <input name="amount"
									class="form-control text-center disable" />
							</div>

							<div class="form-group">
								<label>الملاحظة</label> <input name="note"
									class="form-control text-center disable" />
							</div>


							<input type="submit" class="btn btn-outline-danger btn-lg w-100"
								value="سحب المبلغ" />

						</form:form>
					</div>
				</div>
				<br>
			</div>

			<div class="mr-4 col-8">

				<div style="width: 100%; height: 500px; overflow: auto;"
					class=" shadow">

					<table class="mh-50 table table-striped table-sm  table-bordered ">

						<thead>
							<tr>
								<th class="col-2">المبلغ</th>
								<th class="col-2">الملاحظة</th>
								<th class="col-2">التاريخ</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="spendTemp" items="${spendsList}">

								<tr>

									<td class="col-3">${spendTemp.amount}</td>
									<td class="col-3">${spendTemp.note}</td>
									<td class="col-3">${spendTemp.date}</td>

									<td class="col-1 "><a
										${spendTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
										href="delete-spend?spendId=${spendTemp.id}"
										onclick="return confirm('هل انت متأكد من الإلغاء ؟')"
										class="btn btn-outline-danger btn-sm "> إلغاء </a></td>
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