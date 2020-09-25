<%@page import="net.bytebuddy.asm.Advice.Local"%>
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
<title>كشف الأصناف</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript">
	
function showUpdateForm(btn, id) {

	var updateForm = document.getElementById("update-form");

	var itemId = document.getElementById("itemId"+id).innerText;
	var itemQuantity = document.getElementById("itemQuantity"+id).innerText;
	var itemSellPrice = document.getElementById("itemSellPrice"+id).innerText;

	var newId = document.getElementById("newId");
	var newQuantity = document.getElementById("newQuantity");
	var newSellPrice = document.getElementById("newSellPrice");

	newId.value = itemId;
	newQuantity.value = itemQuantity;
	newSellPrice.value = itemSellPrice;

	
	
	if (updateForm.style.display === "none")  {

		updateForm.style.display = "block";
	}

}
	
	</script>
</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="">

		<div dir="rtl" class="row  mr-2">

			<div>

				<div class="form-group">
					<label>رقم الفاتورة</label> <input disabled="disabled"
						value="S - ${billSell.id}"
						class="form-control text-center btn-outline-primary" />
				</div>

				<div class="form-group">
					<label>التاريخ</label> <input disabled="disabled"
						value="<%=LocalDate.now().toString()%>"
						class="form-control text-center btn-outline-primary" />
				</div>

				<div class="form-group">
					<label> اسم الوحدة </label> <input disabled="disabled"
						value="${billSell.client.name}"
						class="form-control text-center btn-outline-primary" />
				</div>

				<form:form method="get" action="add-item-to-update-sell-bill"
					modelAttribute="item">

					<div class="form-group">
						<label>اسم الصنف</label>
						<form:select class="form-control text-center " path="id">
							<form:options items="${itemsList}" itemLabel="name" />
						</form:select>
					</div>


					<div class="form-group">
						<label>الكمية</label>
						<form:input path="quantity" class="form-control text-center" />
					</div>

					<button type="submit" class="btn btn-primary btn-lg w-100">اضافة
						للفاتورة</button>


				</form:form>

			</div>

			<div class="mr-4 col-7 ">

				<div class="shadow"
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered">

						<thead>
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>سعر البيع</th>
								<th>اجمالي السعر</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="itemTemp" items="${billSellItems}">

								<tr>
									<td id="itemId${itemTemp.id}">${itemTemp.item.name}</td>
									<td id="itemQuantity${itemTemp.id}">${itemTemp.quantity}</td>
									<td id="itemSellPrice${itemTemp.id}">${itemTemp.sellPrice}</td>
									<td>${itemTemp.sellPrice * itemTemp.quantity}</td>

									<td><button type="button"
											class="btn btn-outline-secondary btn-sm"
											onclick="showUpdateForm(this,${itemTemp.id})">تعديل</button>


										<a type="button"
										href="delete-sellBillItemUpdate?sellBillItemId=${itemTemp.id}"
										class="btn btn-outline-danger btn-sm">إلغاء</a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>

				<span class="btn btn-outline-success float-right mt-sm-4">
					اجمالي: ${total}</span>


				<div class="float-left pt-sm-4">

					<form:form>


						<a href="delete-sellBill?sellBillId=${billSell.id}"
							class="btn btn-danger "
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							إلغاء </a>


						<a href="update-sellBill?sellBillId=${billSell.id}"
							onclick="return confirm('هل انت متأكد من تحديث الفاتورة ؟')"
							class="btn btn-success ${billSellItems.size() eq 0 ? 'disabled' : ''} ">
							تحديث</a>


						<a href="save-sellBill?sellBillId=${billSell.id}"
							onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
							class="btn btn-primary ${billSellItems.size() eq 0 ? 'disabled' : ''} ">
							طباعة</a>

					</form:form>

				</div>

			</div>


			<div class="row mr-4 " style="display:;" id="update-form">

				<div class="card border-warning " style="max-width: 20rem;">
					<div class="card-header">
						<h4>تعديل صنف</h4>
					</div>
					<div class="card-body">

						<form method="get" action="update-item">

							<div hidden="" class="form-group">
								<label>اسم الصنف</label> <input id="newId"
									class="form-control text-center" />
							</div>

							<div class="form-group">
								<label>الكمية</label> <input id="newQuantity"
									class="form-control text-center" />
							</div>

							<div class="form-group">
								<label>سعر الشراء</label> <input id="newSellPrice"
									class="form-control text-center" />
							</div>

							<button type="submit" class="btn btn-outline-dark btn-lg w-100">تعديل
								الصنف</button>


						</form>

					</div>
				</div>
			</div>

		</div>

	</div>



</body>
</html>