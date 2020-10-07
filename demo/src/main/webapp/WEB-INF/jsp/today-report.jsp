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


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div dir="rtl" style="width: 50%; margin: 0 auto; text-align: center;"
		class="col-8">

		<div style="height: 510px; overflow: auto;"
			class=" shadow font-weight-bold ">

			<table style="font-size: 16px"
				class="mh-50 table  table-striped table-sm   ">

				<thead class="bg-primary shadow" style="position: sticky; top: 0;">
					<tr>
						<th class="col-2">المفتاح</th>
						<th class="col-2">القيمة</th>
					</tr>
				</thead>

				<tbody class="shadow">

					<tr>
						<td class=" border-primary">الخزنة</td>
						<td class="border-primary"><fmt:formatNumber
								value="${bank.balance}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">الخزنة اليوم</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${bank.balanceToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مبيعات</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalSalesToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مبيعات نقدي</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalPayedSalesToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مبيعات آجل</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalLateSalesToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مشتريات</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalBuysToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مرتجعات</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalReturnsToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">اجمالي الربح</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalGain}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">مصاريف</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${spendTotalToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">صافي ربح</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${totalGain - spendTotalToday}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">رصيد الموردين</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${companyDraweeTotal}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">رصيد الوحدات</td>
						<td class=" border-primary"><fmt:formatNumber
								value="${clientDraweeTotal}" maxFractionDigits="2" /></td>
					</tr>

					<tr>
						<td class=" border-primary">فواتير البيع</td>
						<td class=" border-primary">${sellBillCountToday}</td>
					</tr>

					<tr>
						<td class=" border-primary">فواتير النقدي</td>
						<td class=" border-primary">${payedSellBillCountToday}</td>
					</tr>

					<tr>
						<td class=" border-primary">فواتير الآجل</td>
						<td class=" border-primary">${lateSellBillCountToday}</td>

					</tr>

					<tr>
						<td class=" border-primary">فواتير الشراء</td>
						<td class=" border-primary">${buyBillCountToday}</td>
					</tr>

					<tr>
						<td class=" border-primary">فواتير المرتجع</td>
						<td class=" border-primary">${returnBillCountToday}</td>
					</tr>


				</tbody>
			</table>

		</div>


	</div>



</body>
</html>