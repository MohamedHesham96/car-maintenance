<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
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
<title>تقرير</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="">
		<div dir="rtl" class="row">

			<div
				style="margin: 0; position: absolute; top: 55%; left: 50%; transform: translate(-50%, -50%);"
				class="col-6">

				<div style="width: 100%; height: 520px; overflow: auto;"
					class=" shadow">


					<table style="font-size: 18px"
						class="mh-50 table   table-striped table-sm  table-bordered ">

						<thead class="bg-info">
							<tr>
								<th class="col-2">المفتاح</th>
								<th class="col-2">القيمة</th>
							</tr>
						</thead>

						<tbody>

							<tr>
								<td class="col-3">الخزنة</td>
								<td class="col-3"><fmt:formatNumber value="${bank.balance}"
										maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">الخزنة اليوم</td>
								<td class="col-3"><fmt:formatNumber
										value="${bank.balanceToday}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">مبيعات</td>
								<td class="col-3"><fmt:formatNumber
										value="${totalSallsToday}" maxFractionDigits="2" /></td>
							</tr>


							<tr>
								<td class="col-3">مشتريات</td>
								<td class="col-3"><fmt:formatNumber
										value="${totalBuysToday}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">اجمالي الربح</td>
								<td class="col-3"><fmt:formatNumber value="${totalGain}"
										maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">مصاريف</td>
								<td class="col-3"><fmt:formatNumber
										value="${spendTotalToday}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">صافي ربح</td>
								<td class="col-3"><fmt:formatNumber
										value="${totalGain - spendTotalToday}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">رصيد الموردين</td>
								<td class="col-3"><fmt:formatNumber
										value="${companyDraweeTotal}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">رصيد الوحدات</td>
								<td class="col-3"><fmt:formatNumber
										value="${clientDraweeTotal}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class="col-3">فواتير البيع</td>
								<td class="col-3">${sellBillCountToday}</td>
							</tr>

							<tr>
								<td class="col-3">فواتير النقدي</td>
								<td class="col-3">${payedSellBillCountToday}</td>
							</tr>

							<tr>
								<td class="col-3">فواتير الآجل</td>
								<td class="col-3">${lateSellBillCountToday}</td>

							</tr>

							<tr>
								<td class="col-3">فواتير الشراء</td>
								<td class="col-3">${buyBillCountToday}</td>
							</tr>

							<tr>
								<td class="col-3">فواتير المرتجع</td>
								<td class="col-3">${returnBillCountToday}</td>
							</tr>


						</tbody>
					</table>

				</div>


			</div>
		</div>

	</div>
</body>
</html>