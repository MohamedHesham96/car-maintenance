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
<title>إدارة الخزنة</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript">
	
	function showAddForm() {

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

		var spendId = document.getElementById("spendId"+id).innerText;
		var spendAmount = document.getElementById("spendAmount"+id).innerText;
		var spendNote = document.getElementById("spendNote"+id).innerText;
	
		var newId = document.getElementById("newId");
		var newAmount = document.getElementById("newAmount");
		var newNote = document.getElementById("newNote");
	
		newId.value = spendId;
		newAmount.value = spendAmount;
		newNote.value = spendNote;
		
		if (updateForm.style.display === "none")  {

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

	<div style="width: 100%; text-align: center;" class=" ">

		<div dir="rtl" class="row mr-3">

			<div>

				<div class="card border-warning mr-lg-5" style="max-width: 20rem;">
					<div
						class="card-header border-warning text-warning font-weight-bold">
						<h5>إدارة الخزنة</h5>
					</div>
					<div class="card-body font-weight-bold">

						<input name="companyName" value="الخزنة : ${theBank.balance}"
							disabled="disabled"
							class="form-control btn-outline-warning text-center mb-1 font-weight-bold" />

						<input dir="ltr" name="companyName"
							value="الخزنة اليوم : ${theBank.balanceToday}"
							disabled="disabled"
							class="form-control btn-outline-warning text-center mb-1 font-weight-bold" />

						<input name="companyName"
							value="اجمالي الارصدة : ${balancesTotal}" disabled="disabled"
							class="form-control btn-outline-warning text-center mb-1 font-weight-bold" />

						<input name="companyName"
							value="أرصدة اليوم : ${balancesTotalByDate}" disabled="disabled"
							class="form-control btn-outline-warning text-center mb-1 font-weight-bold" />


						<div id="add-form">

							<form:form method="post" action="add-balance"
								modelAttribute="spend">

								<label class="font-weight-bold text-warning pt-1">المبلغ</label>

								<input dir="ltr" name="amount"
									class="form-control text-center disable bg-light font-weight-bold" />

								<div class="form-group text-warning pt-1">
									<label>الملاحظة</label> <input name="note"
										class="form-control text-center bg-light font-weight-bold" />
								</div>

								<input type="submit"
									class="btn btn-success font-weight-bold  w-100 "
									value="اضافة كرصيد" />

							</form:form>

						</div>

						<div style="display: none" id="update-form">

							<form:form method="post" action="update-record"
								modelAttribute="spend">

								<label class="font-weight-bold text-warning pt-1">المبلغ</label>

								<input style="display: none" id="newId" dir="ltr" name="id"
									class="form-control text-center disable bg-light font-weight-bold" />

								<input id="newAmount" dir="ltr" name="amount"
									class="form-control text-center disable bg-light font-weight-bold" />

								<div class="form-group text-warning pt-1">
									<label>الملاحظة</label> <input id="newNote" name="note"
										class="form-control text-center bg-light font-weight-bold" />
								</div>


								<input type="submit"
									class="btn btn-success font-weight-bold  w-100 "
									value="تعديل الرصيد" />


								<button type="button"
									class="btn btn-warning btn-sm font-weight-bold  w-100 mt-2"
									onclick="showAddForm();">إلغاء التعديل</button>
							</form:form>
						</div>
					</div>
				</div>


			</div>

			<div class="mr-4 col-8 ">

				<div style="width: 100%; height: 500px; overflow: auto;"
					class=" shadow">

					<table
						class=" table table-dark table-striped table-sm shadow font-weight-bold">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr>
								<th class="col-2">نوع المبلغ</th>
								<th class="col-2">المبلغ</th>
								<th class="col-2">الملاحظة</th>
								<th class="col-2">التاريخ</th>
								<th class="col-2">العميلة</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="spendTemp" items="${balancesList}">

								<tr class="">

									<td class="pt-2 border-warning text-warning" hidden="hidden"
										id="spendId${spendTemp.id}">${spendTemp.id}</td>
									<td
										class=" p-2 ${spendTemp.type eq 'رصيد' ? 'bg-success' : 'bg-danger'}">${spendTemp.type}</td>
									<td class="border-primary p-2" id="spendAmount${spendTemp.id}">${spendTemp.amount}</td>
									<td class="border-primary p-2" id="spendNote${spendTemp.id}">${spendTemp.note}</td>
									<td class="border-primary p-2">${spendTemp.date}</td>

									<td class="border-primary">




										<button
											${spendTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
											onclick="showUpdateForm(this, ${spendTemp.id})"
											class="btn btn-primary btn-sm font-weight-bold">
											تعديل</button> <a
										${spendTemp.date == LocalDate.now().toString() ? '' :  'hidden'  }
										href="delete-record?spendId=${spendTemp.id}"
										onclick="return confirm('هل انت متأكد من الإلغاء ؟')"
										class="btn btn-danger btn-sm font-weight-bold "> إلغاء </a>
									</td>

								</tr>

							</c:forEach>
						</tbody>
					</table>

				</div>


			</div>
		</div>

	</div>
</body>
</html>