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
<title>كشف الأصناف</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>
	<br>

	<div style="text-align: center;" class="container ">
		<div dir="rtl" class="row">

			<div>

				<div class="card border-primary" style="max-width: 20rem;">
					<div class="card-header">
						<h5>فاتورة شراء</h5>
					</div>

					<div class="card-body">

						<input disabled="disabled"
							value=" رقم الفاتورة : B - ${billBuy.id}"
							class="form-control text-center btn-outline-primary  mb-2" /> <input
							disabled="disabled"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							class="form-control text-center btn-outline-primary  mb-2" /> <input
							disabled="disabled" value="الوحدة : ${billBuy.company.name}"
							class="form-control text-center btn-outline-primary  mb-2" />


						<form:form method="get" action="add-item-to-buy-bill"
							modelAttribute="item">

							<label>اسم الصنف</label>
							<form:select class="form-control text-center " path="id">
								<form:options items="${itemsList}" itemLabel="name" />
							</form:select>


							<div class="form-group">
								<label>الكمية</label>
								<form:input id="quantity" path="quantity"
									class="form-control text-center " />
							</div>



							<div class="form-group">
								<label>سعر الشراء</label>
								<form:input id="quantity" path="buyPrice"
									class="form-control text-center " />
							</div>


							<button type="submit" class="btn btn-primary btn-lg w-100 ">اضافة
								للفاتورة</button>


						</form:form>

					</div>
				</div>
			</div>

			<div class="mr-4 col-8 ">

				<div class="shadow"
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered">

						<thead>
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>سعر الشراء</th>
								<th>اجمالي السعر</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="itemTemp" items="${billBuy.billBuyItems}">

								<tr>
									<td>${itemTemp.item.name}</td>

									<td>${itemTemp.quantity}</td>
									<td><fmt:formatNumber value="${itemTemp.buyPrice}"
											maxFractionDigits="2" /></td>
									<td><fmt:formatNumber
											value=" ${itemTemp.buyPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td><a
										href="delete-buyBillItem?buyBillItemId=${itemTemp.id}"
										class="btn btn-outline-danger btn-sm"> إلغاء </a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>




				<span class="btn btn-outline-success float-right mt-sm-4">
					اجمالي: ${total}</span>



				<div class="float-left pt-sm-4 ">


					<form:form>

						<a href="delete-buyBill?buyBillId=${billBuy.id}"
							class="btn btn-danger "
							onclick="return confirm('هل انت متأكد من حذف الفاتورة ؟')">
							حذف </a>


						<a href="save-buyBill?buyBillId=${billBuy.id}"
							onclick="return confirm('هل انت متأكد من حفظ الفاتورة ؟')"
							class="btn btn-success ${billBuyItems.size() eq 0 ? 'disabled' : ''} ">
							حفظ</a>


						<a href="/show-print-buy-bill?buyBillId=${billBuy.id}"
							onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
							class="btn btn-primary ${billBuyItems.size() eq 0 ? 'disabled' : ''} ">
							طباعة</a>

					</form:form>

				</div>

			</div>

		</div>

	</div>
</body>
</html>



