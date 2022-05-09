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
<title>المخزن</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">



<script type="text/javascript">
	function showAddForm(btn) {

		var addForm = document.getElementById("add-form");
		var updateForm = document.getElementById("update-form");

		if (addForm.style.display === "none") {
			addForm.style.display = "block";
			updateForm.style.display = "none";

		}
	}

	function showUpdateForm(btn, id) {

		var addForm = document.getElementById("add-form");
		var updateForm = document.getElementById("update-form");

		var itemId = document.getElementById("itemId" + id).innerText;
		var itemName = document.getElementById("itemName" + id).innerText;
		var itemQuantity = document.getElementById("itemQuantity" + id).innerText;
		var itemBuyPrice = document.getElementById("itemBuyPrice" + id).innerText;
		var itemSellPrice = document.getElementById("itemIdSellPrice" + id).innerText;

		var newId = document.getElementById("newId");
		var newName = document.getElementById("newName");
		var newQuantity = document.getElementById("newQuantity");
		var newBuyPrice = document.getElementById("newBuyPrice");
		var newSellPrice = document.getElementById("newSellPrice");

		newId.value = itemId;
		newName.value = itemName;
		newQuantity.value = itemQuantity;
		newBuyPrice.value = itemBuyPrice;
		newSellPrice.value = itemSellPrice;

		if (updateForm.style.display === "none") {

			addForm.style.display = "none";
			updateForm.style.display = "block";
		}

	}
</script>


</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>


	<br>

	<div style="align-items: center; width: 100%; text-align: center;"
		class="">

		<div dir="rtl" class="row mr-lg-4">

			<div class=" mr-lg-4">
				<div class="row" id="add-form">

					<div class="card  border-warning font-weight-bold  text-warning"
						style="max-width: 20rem;">
						<div class="card-header border-warning ">
							<h5>بيانات المستخدم</h5>
						</div>

						<div class="card-body bg-dark ">

							<form:form modelAttribute="user" method="post"
								action="save-user-data">

								<form:input hidden="hidden" path="id"
									class="form-control text-center bg-light font-weight-bold" />

								<label>اسم المستخدم</label>
								<form:input path="name"
									class="form-control text-center bg-light font-weight-bold" />

								<label>الرقم السري</label>
								<form:input path="password"
									class="form-control text-center  bg-light font-weight-bold" />

								<h6 class="mt-2 font-weight-bold">--------------[ الصلحيات
									]--------------</h6>

								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox class="" path="hasMain" /> الرئيسية
								</label>

								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasSales" /> مبيعات
								</label>

								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasBuys" /> مشتريات
								</label>


								<br>


								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasStore" /> المخزن
								</label>

								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasBank" /> الخزنة
								</label>


								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasItemMove" /> حركة صنف
								</label>


								<br>


								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasClients" /> العملاء
								</label>


								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasCompanies" /> الموردين
								</label>


								<label class="btn btn-primary btn-sm active font-weight-bold">
									<form:checkbox path="hasReports" /> التقارير
								</label>


								<br>


								<button type="submit"
									class="btn btn-outline-warning btn-lg font-weight-bold w-100 mt-1">حفظ
									المستخدم</button>

							</form:form>

						</div>
					</div>

				</div>

			</div>

			<div class="mr-4 col-9">

				<div style="width: 100%; height: 460px; overflow: auto;"
					class=" shadow ">

					<table
						class="table table-dark table-striped table-sm border-primary 	 shadow font-weight-bold">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr class=" ">
								<th>الكود</th>
								<th>الاسم</th>
								<th>الرقم السري</th>
								<th>العملية</th>
							</tr>
						</thead>

						<tbody class=" ">
							<c:forEach var="userTemp" items="${usersList}">

								<tr>

									<td class="pt-2 border-warning text-warning"
										id="itemId${userTemp.id}">${userTemp.id}</td>

									<td class="pt-2 border-primary " id="userName${userTemp.id}">${userTemp.name}</td>

									<td class="pt-2 border-primary" id="userPassword${userTemp.id}">${userTemp.password}</td>

									<td class=" border-primary "><a type="button"
										class="btn btn-primary btn-sm font-weight-bold"
										href="update-user?userId=${userTemp.id}">تعديل</a></td>
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
							<th> عدد المستخدمين : ${usersList.size()}</th>
						</tr>
					</thead>

				</table>
			</div>

		</div>

	</div>
</body>
</html>