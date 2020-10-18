<%@page import="org.apache.taglibs.standard.tag.common.xml.IfTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>حركة صنف</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="align-items: center; width: 100%; text-align: center;"
		class="">

		<div dir="rtl" class="row mr-lg-4">

			<div class=" mr-lg-4">

				<div class="row" id="add-form">

					<div
						class="card  border-warning font-weight-bold pb-4 text-warning"
						style="max-width: 20rem;">
						<div class="card-header border-warning ">
							<h4>اضافة صنف</h4>
						</div>

						<div class="card-body bg-dark ">

							<form:form modelAttribute="item" method="post"
								action="add-new-item">

								<div class="form-group ">
									<label class="">اسم الصنف</label>
									<form:input path="name"
										class="form-control text-center bg-light font-weight-bold" />
								</div>

								<div class="form-group">
									<label>الكمية</label>
									<form:input path="quantity"
										class="form-control text-center bg-light font-weight-bold" />
								</div>

								<div class="form-group">
									<label>سعر الشراء</label>
									<form:input path="buyPrice"
										class="form-control text-center bg-light font-weight-bold" />
								</div>

								<div class="form-group">
									<label>سعر البيع</label>
									<form:input path="sellPrice"
										class="form-control text-center bg-light font-weight-bold" />
								</div>

								<button type="submit"
									class="btn btn-outline-warning btn-lg w-100 ">اضافة
									الصنف</button>

							</form:form>

						</div>
					</div>
				</div>
			</div>

			<div class="mr-4 col-9">

				<div style="width: 100%; height: 470px; overflow: auto;"
					class=" shadow ">

					<table
						class="table  table-striped table-sm border-primary shadow font-weight-bold">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr class=" ">
								<th>الكود</th>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>سعر الشراء</th>
								<th>سعر البيع</th>
								<th>جملة شراء</th>
								<th>جملة بيع</th>
								<th>ربح القطعة</th>
								<th>جملة الربح</th>
								<th>العميلة</th>

							</tr>
						</thead>

						<tbody class=" ">
							<c:forEach var="itemTemp" items="${itemsList}">

								<tr>
									<td class="pt-2 border-warning text-warning"
										id="itemId${itemTemp.id}">${itemTemp.id}</td>
									<td class="pt-2 border-primary " id="itemName${itemTemp.id}">${itemTemp.name}</td>
									<td class="pt-2 border-primary" id="itemQuantity${itemTemp.id}">${itemTemp.quantity}</td>
									<td class="pt-2 border-primary" id="itemBuyPrice${itemTemp.id}">${itemTemp.buyPrice}</td>
									<td class="pt-2 border-primary"
										id="itemIdSellPrice${itemTemp.id}">${itemTemp.sellPrice}</td>

									<td class="pt-2 border-primary"><fmt:formatNumber
											value="${itemTemp.buyPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>
									<td class="pt-2 border-primary"><fmt:formatNumber
											value="${itemTemp.sellPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td class="pt-2 border-primary"><fmt:formatNumber
											value="${itemTemp.sellPrice - itemTemp.buyPrice}"
											maxFractionDigits="2" /></td>
									<td class="pt-2 border-primary"><fmt:formatNumber
											value="${(itemTemp.sellPrice - itemTemp.buyPrice) * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td class=" border-primary ">
										<button type="button"
											class="btn btn-primary btn-sm font-weight-bold"
											onclick="showUpdateForm(this,${itemTemp.id})">تعديل</button>
									</td>
								</tr>

							</c:forEach>

						</tbody>

					</table>

				</div>

				<table
					class="table  table-striped table-sm border-primary shadow font-weight-bold">

					<thead class="bg-primary  shadow "
						style="position: sticky; top: 0;">
						<tr class=" ">

							<th>عدد الاصناف : ${itemsCount}</th>

							<th>جملة شراء : <fmt:formatNumber value="${totalItemsBuys}"
									maxFractionDigits="2" /></th>
							<th>جملة بيع : <fmt:formatNumber value="${totalItemsSales}"
									maxFractionDigits="2" /></th>

							<th>جملة ربح : <fmt:formatNumber
									value="${totalItemsSales - totalItemsBuys}"
									maxFractionDigits="2" /></th>

						</tr>
					</thead>

				</table>
			</div>

		</div>

	</div>
</body>
</html>