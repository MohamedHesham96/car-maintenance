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
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>

	<br>

	<div style="width: 82.5%; text-align: center;" class=" ">

		<div dir="rtl" class="row mr-3">

			<div class="card border-warning font-weight-bold  text-warning "
				style="min-width: 16rem;">
				<div class="card-header border-warning ">
					<h4>تقارير</h4>
				</div>

				<div id="" class="card-body bg-dark ">

					<form method="post" action="show-report">
						<label>تاريخ اليوم</label> <input type="date" name="date"
							value="${date eq '' ? '' : date}"
							class="form-control text-center  bg-light font-weight-bold mb-2 datepicker">

						<button type="submit"
							class="btn btn-outline-warning btn-lg w-100 mt-1" value="date"
							name="action">عرض تقرير اليوم</button>

					</form>

					<hr class="mt-4 mb-3 bg-warning ">

					<form method="post" action="show-report">
						<label>التاريخ من</label> <input type="date" name="dateFrom"
							value="${dateFrom eq '' ? '' : dateFrom}"
							class="form-control text-center  bg-light font-weight-bold mb-2 datepicker">

						<label>التاريخ إلى</label> <input type="date" name="dateTo"
							value="${dateTo eq '' ? '' : dateTo}"
							class="form-control text-center  bg-light font-weight-bold mb-2 datepicker">

						<button type="submit"
							class="btn btn-outline-warning btn-lg w-100 mt-1"
							value="dateFromDateTo" name="action">عرض تقرير الفترة</button>

					</form>

				</div>
			</div>

			<div dir="rtl" class="col-6 mr-2">

				<div style="height: 490px; overflow: auto;"
					class=" shadow font-weight-bold ">

					<table style="font-size: 16px"
						class="table table-dark table-striped table-sm">

						<thead class="bg-primary shadow" style="position: sticky; top: 0;">
							<tr>
								<th class="col-1">المفتاح</th>
								<th class="col-1">القيمة</th>
							</tr>
						</thead>

						<tbody dir="ltr" class="shadow">

							<tr>
								<td class=" border-primary">الخزنة</td>
								<td class="border-primary"><fmt:formatNumber
										value="${balance}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">الخزنة اليوم</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${balanceToday}" maxFractionDigits="2" /></ ttd>
							</tr>

							<tr>
								<td class=" border-primary">مبيعات</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalSales}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مبيعات نقدي</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalPayedSales}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مبيعات آجل</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalLateSales}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مشتريات</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalBuys}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مرتجع عملاء</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalReturns}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مرتجع موردين</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalCompaniesReturns}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">اجمالي الربح</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalGain}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">مصاريف</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${spendTotal}" maxFractionDigits="2" /></td>
							</tr>

							<tr>
								<td class=" border-primary">صافي ربح</td>
								<td class=" border-primary"><fmt:formatNumber
										value="${totalGain - spendTotal}" maxFractionDigits="2" /></td>
							</tr>

							<!-- 							<tr> -->
							<!-- 								<td class=" border-primary">رصيد الموردين</td> -->
							<%-- 								<td class=" border-primary"><fmt:formatNumber --%>
							<%-- 										value="${companyDraweeTotal}" maxFractionDigits="2" /></td> --%>
							<!-- 							</tr> -->

							<!-- 							<tr> -->
							<!-- 								<td class=" border-primary">رصيد الوحدات</td> -->
							<%-- 								<td class=" border-primary"><fmt:formatNumber --%>
							<%-- 										value="${clientDraweeTotal}" maxFractionDigits="2" /></td> --%>
							<!-- 							</tr> -->

							<tr>
								<td class=" border-primary">فواتير البيع</td>
								<td class=" border-primary">${sellBillCount}</td>
							</tr>

							<tr>
								<td class=" border-primary">فواتير النقدي</td>
								<td class=" border-primary">${payedSellBillCount}</td>
							</tr>

							<tr>
								<td class=" border-primary">فواتير الآجل</td>
								<td class=" border-primary">${lateSellBillCount}</td>

							</tr>

							<tr>
								<td class=" border-primary">فواتير الشراء</td>
								<td class=" border-primary">${buyBillCount}</td>
							</tr>

							<tr>
								<td class=" border-primary">فواتير مرتجع عميل</td>
								<td class=" border-primary">${returnBillCount}</td>
							</tr>

							<tr>
								<td class=" border-primary">فواتير مرتجع مورد</td>
								<td class=" border-primary">${companyReturnBillCount}</td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>

		</div>
	</div>
</body>
</html>