<%@page import="net.bytebuddy.asm.Advice.Local"%>
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
<title>فاتورة شراء</title>
<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">
</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>
	<br>

	<div style="text-align: center;" class="container ">
		<div dir="rtl" class="row ">

			<div class=" shadow">

				<div class="card border-warning text-warning shadow"
					style="max-width: 20rem;">
					<div class="card-header border-warning shadow">
						<h5>فاتورة شراء</h5>
					</div>

					<div class="card-body font-weight-bold ">

						<input disabled="disabled"
							value=" رقم الفاتورة : B - ${billBuy.id}"
							class="form-control text-center btn-outline-warning font-weight-bold  mb-2" />

						<input disabled="disabled"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							class="form-control text-center btn-outline-warning font-weight-bold  mb-2" />


						<input disabled="disabled"
							value="الوحدة : ${billBuy.company.name}"
							class="form-control text-center btn-outline-warning font-weight-bold  mb-2" />


						<form:form method="get" action="add-item-to-buy-bill"
							modelAttribute="item">

							<label>اسم الصنف</label>
							<form:select autofocus="autofocus"
								class="form-control text-center font-weight-bold bg-light"
								path="id">
								<form:options items="${itemsList}" itemLabel="name" />
							</form:select>


							<label>الكمية</label>
							<form:input id="quantity" path="quantity"
								class="form-control text-center font-weight-bold bg-light" />



							<div class="form-group">
								<label>سعر الشراء</label>
								<form:input id="quantity" path="buyPrice"
									class="form-control text-center font-weight-bold bg-light" />
							</div>

							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100 ">اضافة
								للفاتورة</button>

						</form:form>

					</div>
				</div>
			</div>

			<div class="mr-4 col-8 shadow pb-3">

				<div class=" font-weight-bold  shadow"
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-dark  table-striped table-sm shadow">

						<thead class="bg-primary text-white border-primary">
							<tr>
								<th class=" col-1">الصنف</th>
								<th class=" col-1">الكمية</th>
								<th class=" col-1">سعر الشراء</th>
								<th class=" col-1">اجمالي السعر</th>
								<th class=" col-1">العميلة</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="itemTemp" items="${billBuy.billBuyItems}">

								<tr>
									<td class="border-primary pt-2">${itemTemp.item.name}</td>

									<td class="border-primary pt-2">${itemTemp.quantity}</td>

									<td class="border-primary pt-2"><fmt:formatNumber
											value="${itemTemp.buyPrice}" maxFractionDigits="2" /></td>

									<td class="border-primary pt-2"><fmt:formatNumber
											value=" ${itemTemp.buyPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td class="border-primary "><a
										href="delete-buyBillItem?buyBillItemId=${itemTemp.id}"
										class="btn btn-danger btn-sm font-weight-bold"> إلغاء </a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>

				<span
					class=" btn bg-light text-dark float-right mt-sm-4 shadow font-weight-bold">
					اجمالي الفاتورة : <fmt:formatNumber value="${total}"
						maxFractionDigits="2" />
				</span>

				<div class="float-left pt-sm-4">

					<form:form>

						<a href="delete-buyBill?buyBillId=${billBuy.id}"
							class="btn btn-danger shadow font-weight-bold"
							onclick="return confirm('هل انت متأكد من حذف الفاتورة ؟')">
							حذف </a>


						<c:if test="${billBuy.billBuyItems.size() > 0 }">

							<a href="save-buyBill?buyBillId=${billBuy.id}"
								onclick="return confirm('هل انت متأكد من حفظ الفاتورة ؟')"
								class="btn btn-success mr-1 shadow font-weight-bold "> حفظ</a>

							<a href="/show-print-buy-bill?buyBillId=${billBuy.id}"
								onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
								class="btn btn-primary mr-1 shadow font-weight-bold ${billBuyItems.size() eq 0 ? 'disabled' : ''} ">
								طباعة</a>

						</c:if>


						<c:if test="${billBuy.billBuyItems.size() eq 0 }">


							<button class="btn btn-success mr-1 shadow font-weight-bold "
								disabled>حفظ</button>


							<button class="btn btn-primary shadow mr-1  font-weight-bold"
								disabled>طباعة</button>
						</c:if>



					</form:form>

				</div>

			</div>

		</div>

	</div>
</body>
</html>



