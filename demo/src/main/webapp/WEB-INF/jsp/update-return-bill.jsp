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

<script type="text/javascript">
	
function showUpdateForm(btn, id) {

	var updateForm = document.getElementById("update-form");

	var itemId = document.getElementById(id).id;
	var itemName = document.getElementById(id).innerText;

	var itemQuantity = document.getElementById("itemQuantity"+id).innerText;
	var itemReturnPrice = document.getElementById("itemReturnPrice"+id).innerText;

	var newId = document.getElementById("newId");
	var newQuantity = document.getElementById("newQuantity");
	var newName = document.getElementById("newName");
	var newReturnPrice = document.getElementById("newReturnPrice");

	newId.value = itemId;
	newName.value = itemName;
	newQuantity.value = itemQuantity;
	newReturnPrice.value = itemReturnPrice;

	
	
	if (updateForm.style.display === "none")  {

		updateForm.style.display = "block";
	}

}
	
	</script>
</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class=" ">

		<div dir="rtl" class="row mr-lg-2 ">

			<div class="card border-primary" style="max-width: 20rem;">
				<div class="card-header ">
					<h5>أضافة للفاتور</h5>
				</div>
				<div class="card-body">

					<label>رقم الفاتورة</label> <input disabled="disabled"
						value="S - ${billReturn.id}"
						class="form-control text-center btn-outline-primary" /> <label>التاريخ</label>
					<input disabled="disabled" value="<%=LocalDate.now().toString()%>"
						class="form-control text-center btn-outline-primary" /> <label>
						اسم الوحدة </label> <input disabled="disabled"
						value="${billReturn.client.name}"
						class="form-control text-center btn-outline-primary" />

					<form:form method="get" action="add-item-to-update-return-bill"
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
			</div>

			<div class=" col-7">

				<div class="shadow "
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered ">

						<thead>
							<tr>
								<th>الصنف</th>
								<th>الكمية</th>
								<th>سعر البيع</th>
								<th>اجمالي السعر</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="itemTemp" items="${billReturn.billReturnItems}">

								<tr>
									<td id="${itemTemp.id}">${itemTemp.item.name}</td>
									<td id="itemQuantity${itemTemp.id}">${itemTemp.quantity}</td>
									<td id="itemReturnPrice${itemTemp.id}"><fmt:formatNumber
											value="${itemTemp.returnPrice}" maxFractionDigits="2" /></td>

									<td><fmt:formatNumber
											value="${itemTemp.returnPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td><button type="button"
											class="btn btn-outline-primary btn-sm"
											onclick="showUpdateForm(this,${itemTemp.id})">تعديل</button>


										<a type="button"
										href="delete-returnBillItemUpdate?returnBillItemId=${itemTemp.id}"
										onclick="return confirm('هل انت متأكد من إلغاء الصنف ؟')"
										class="btn btn-outline-danger btn-sm">إلغاء</a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>

				<span class="btn btn-outline-success float-right mt-sm-4">
					اجمالي: ${total}</span>


				<div class="float-left pt-sm-4 ">

					<form:form>

						<a href="retrive-UpdateReturnBill?returnBillId=${billReturn.id}"
							class="btn btn-warning "
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							إلغاء التحديث </a>

						<a href="delete-updateReturnBill?returnBillId=${billReturn.id}"
							class="btn btn-danger "
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							إلغاء الفاتورة </a>

						<a href="update-returnBill?returnBillId=${billReturn.id}"
							onclick="return confirm('هل انت متأكد من تحديث الفاتورة ؟')"
							class="btn btn-success ${billReturnItems.size() eq 0 ? 'disabled' : ''} ">
							تحديث</a>

						<a href="show-printView?returnBillId=${billReturn.id}"
							onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
							class="btn btn-primary ${billReturnItems.size() eq 0 ? 'disabled' : ''} ">
							طباعة</a>

					</form:form>

				</div>

			</div>


			<div class="card border-primary" style="max-width: 20rem;">
				<div class="card-header ">
					<h5>تعديل الصنف</h5>
				</div>
				<div class="card-body">

					<form:form modelAttribute="updateItem" method="get"
						action="update-returnBillItem">

						<div hidden="" class="form-group">
							<label>اسم الصنف</label>
							<form:input path="id" id="newId" class="form-control text-center" />
						</div>

						<div class="form-group">
							<label>اسم الصنف</label> <input disabled="disabled" id="newName"
								class="form-control text-center btn-outline-primary">
						</div>

						<div class="form-group">
							<label>الكمية</label>
							<form:input path="quantity" id="newQuantity"
								class="form-control text-center" />
						</div>

						<div class="form-group">
							<label>سعر البيع</label>
							<form:input path="returnPrice" id="newReturnPrice"
								class="form-control text-center" />
						</div>

						<button type="submit" class="btn btn-outline-primary btn-lg w-100">تعديل
							الصنف</button>


					</form:form>

				</div>

			</div>

		</div>
	</div>
</body>
</html>