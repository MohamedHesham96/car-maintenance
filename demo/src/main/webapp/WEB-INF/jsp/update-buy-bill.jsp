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
<title>تعديل فاتورة بيع</title>


<link href="webjars/bootswatch/4.5.2/dist/darkly/_bootswatch.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/_variables.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">
<script type="text/javascript">
	
function showUpdateForm(btn, id) {

	var updateForm = document.getElementById("update-form");

	var itemId = document.getElementById(id).id;
	var itemName = document.getElementById(id).innerText;

	var itemQuantity = document.getElementById("itemQuantity"+id).innerText;
	var itemBuyPrice = document.getElementById("itemBuyPrice"+id).innerText;

	var newId = document.getElementById("newId");
	var newQuantity = document.getElementById("newQuantity");
	var newName = document.getElementById("newName");
	var newBuyPrice = document.getElementById("newBuyPrice");

	newId.value = itemId;
	newName.value = itemName;
	newQuantity.value = itemQuantity;
	newBuyPrice.value = itemBuyPrice;

	
	
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
				<div class="card-header ">${view ? '<h5>عرض للفاتور</h5>' :  '<h5>أضافة للفاتور</h5>'}

				</div>
				<div class="card-body">

					<input disabled="disabled"
						value=" رقم الفاتورة : B - ${billBuy.id}"
						class="form-control text-center btn-outline-primary mb-2" /> <input
						disabled="disabled"
						value="التاريخ : <%=LocalDate.now().toString()%>"
						class="form-control text-center btn-outline-primary mb-2" /> <input
						disabled="disabled" value="اسم المورد : ${billBuy.company.name}"
						class="form-control text-center btn-outline-primary mb-2" /> <input
						${view ? '' :  'hidden'  } disabled="disabled"
						value="اسم البائع : ${billBuy.user.name}"
						class="form-control text-center btn-outline-primary mb-2" />


					<div ${view ? 'hidden' :  ''  }>


						<form:form method="get" action="add-item-to-update-buy-bill"
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
			</div>

			<div class=" col-7">

				<div class="shadow "
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-striped table-sm table-bordered ">

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
									<td id="${itemTemp.id}">${itemTemp.item.name}</td>
									<td id="itemQuantity${itemTemp.id}">${itemTemp.quantity}</td>
									<td id="itemBuyPrice${itemTemp.id}"><fmt:formatNumber
											value="${itemTemp.buyPrice}" maxFractionDigits="2" /></td>

									<td><fmt:formatNumber
											value="${itemTemp.buyPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td ${view ? 'hidden' :  ''  }><button type="button"
											class="btn btn-outline-primary btn-sm"
											onclick="showUpdateForm(this,${itemTemp.id})">تعديل</button>


										<a type="button"
										href="delete-buyBillItemUpdate?buyBillItemId=${itemTemp.id}"
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

						<a ${view ? 'hidden' :  ''  }
							href="retrieve-UpdateBuyBill?buyBillId=${billBuy.id}"
							class="btn btn-warning "
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							إلغاء التحديث </a>

						<a href="delete-updateBuyBill?buyBillId=${billBuy.id}"
							class="btn btn-danger "
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							إلغاء الفاتورة </a>

						<a ${view ? 'hidden' :  ''  }
							href="update-buyBill?buyBillId=${billBuy.id}"
							onclick="return confirm('هل انت متأكد من تحديث الفاتورة ؟')"
							class="btn btn-success ${billBuyItems.size() eq 0 ? 'disabled' : ''} ">
							تحديث</a>


						<a href="/show-print-buy-bill?buyBillId=${billBuy.id}"
							onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
							class="btn btn-primary ${billBuyItems.size() eq 0 ? 'disabled' : ''} ">
							طباعة</a>

					</form:form>

				</div>

			</div>


			<div ${view ? 'hidden' :  ''  } class="card border-primary"
				style="max-width: 20rem;">
				<div class="card-header ">
					<h5>تعديل الصنف</h5>
				</div>
				<div class="card-body">

					<form:form modelAttribute="updateItem" method="get"
						action="update-buyBillItem">

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
							<label>سعر الشراء</label>
							<form:input path="buyPrice" id="newBuyPrice"
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