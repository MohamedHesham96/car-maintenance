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
<title>تقرير اليوم</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-primary mr-lg-5">
					<div class="card-header">
						<h5>التقرير</h5>
					</div>
					<div class="card-body">

						<input name="companyName"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value="الخزنة : ${bank.balance}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value="الخزنة اليوم : ${bank.balanceToday}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> 
							
							
							<input
							name="companyName" value="رصيد الموردين : ${companyDraweeTotal}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> 
								
							<input
							name="companyName" value="رصيد الوحدات : ${clientDraweeTotal}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> 
							
							
							<input
							name="companyName" value="مبيعات اليوم : ${bank.drawee}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value="مصاريف اليوم :  ${spendTotal}"
							disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value=" فواتير البيع : 35" disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value=" فواتير الشراء : 5" disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" /> <input
							name="companyName" value="فواتير المرتجع : 2" disabled="disabled"
							class="form-control btn-outline-primary text-center mb-2" />



					</div>
				</div>
				<br>
			</div>

			<div class="mr-4 col-6">

				<div style="width: 100%; height: 500px; overflow: auto;"
					class=" shadow">

					<table class="mh-50 table table-striped table-sm  table-bordered ">

						<thead>
							<tr>
								<th class="col-2">الصنف</th>
								<th class="col-2">الكمية</th>
								<th class="col-2">الربح</th>
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