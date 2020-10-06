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
<title>طباعة</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="header.jsp"%>
	<br>

	<div style="text-align: center;" class="container ">
		<div dir="rtl" class="row">

			<div class="shadow ">

				<div class="card border-warning text-warning  "
					style="max-width: 20rem;">
					<div class="card-header border-warning">
						<h5>فاتورة مرتجع</h5>
					</div>

					<div class="card-body font-weight-bold ">

						<input disabled="disabled"
							value="رقم الفاتورة : R - ${billReturn.id}"
							class="form-control text-center btn-outline-warning  mb-2 font-weight-bold" />

						<input disabled="disabled"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							class="form-control text-center btn-outline-warning  mb-2 font-weight-bold" />

						<input disabled="disabled"
							value="الوحدة : ${billReturn.client.name}"
							class="form-control text-center btn-outline-warning  mb-2 font-weight-bold" />

						<input disabled="disabled"
							value="البائع : ${billReturn.user.name}"
							class="form-control text-center btn-outline-warning  mb-2 font-weight-bold" />

						<form:form method="get" action="add-item-to-return-bill"
							modelAttribute="item">

							<label>اسم الصنف</label>
							<form:select class="form-control text-center bg-light" path="id">
								<form:options items="${itemsList}" itemLabel="name" />
							</form:select>


							<div class="form-group">
								<label>الكمية</label>
								<form:input id="quantity" path="quantity"
									class="form-control text-center bg-light" />
							</div>


							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100 ">اضافة
								للفاتورة</button>


						</form:form>

					</div>
				</div>
			</div>

			<div class="mr-4 col-8 shadow pb-3">

				<div class="shadow"
					style="position: relative; height: 425px; overflow: auto;">

					<table
						class="table table-dark table-striped table-sm font-weight-bold shadow">

						<thead class="bg-primary">
							<tr>
								<th class="col-2">الصنف</th>
								<th class="col-2">الكمية</th>
								<th class="col-2">سعر البيع</th>
								<th class="col-2">اجمالي السعر</th>
								<th class="col-2"></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="itemTemp" items="${billReturn.billReturnItems}">

								<tr>
									<td class="border-primary pt-2">${itemTemp.item.name}</td>

									<td class="border-primary pt-2">${itemTemp.quantity}</td>
									<td class="border-primary pt-2"><fmt:formatNumber
											value="${itemTemp.returnPrice}" maxFractionDigits="2" /></td>
									<td class="border-primary pt-2"><fmt:formatNumber
											value=" ${itemTemp.returnPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td class="border-primary"><a
										href="delete-returnBillItem?returnBillItemId=${itemTemp.id}"
										class="btn btn-danger btn-sm"> إلغاء </a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>

				<input
					class="btn btn-outline-success float-right mt-sm-4 shadow font-weight-bold"
					value="اجمالي : <fmt:formatNumber value="${total}" maxFractionDigits="2" />">

				<div class="float-left pt-sm-4 ">

					<form:form>

						<a href="delete-returnBill?returnBillId=${billReturn.id}"
							class="btn btn-danger shadow font-weight-bold"
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							حذف </a>


						<a href="save-returnBill?returnBillId=${billReturn.id}"
							onclick="return confirm('هل انت متأكد من حفظ الفاتورة ؟')"
							class="btn btn-success mr-1 shadow font-weight-bold ${billReturnItems.size() eq 0 ? 'disabled' : ''} ">
							حفظ</a>


						<a href="show-printView?returnBillId=${billReturn.id}"
							onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
							class="btn btn-primary mr-1 shadow font-weight-bold ${billReturnItems.size() eq 0 ? 'disabled' : ''} ">
							طباعة</a>

					</form:form>

				</div>



			</div>


		</div>

	</div>
</body>
</html>



