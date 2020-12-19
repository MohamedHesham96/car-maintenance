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
<title>كشف فواتير البيع</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">

</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover;">

	<%@ include file="header.jsp"%>
	<br>

	<div style="text-align: center;" class="container ">
		<div dir="rtl" class="row">

			<div>

				<div
					class="card border-warning shadow text-warning font-weight-bold pb-2"
					style="max-width: 20rem;">

					<div class="card-header  border-warning ">
						<h5>فاتورة بيع</h5>
					</div>

					<div class="card-body bg-dark ">

						<input disabled="disabled"
							value=" رقم الفاتورة : S - ${billSell.id}"
							class="form-control text-center font-weight-bold btn-outline-warning text-warning  mb-2" />

						<input disabled="disabled"
							value="التاريخ : <%=LocalDate.now().toString()%>"
							class="form-control text-center font-weight-bold btn-outline-warning text-warning  mb-2" />


						<input disabled="disabled"
							value=" نوع الفاتورة :  ${billSell.late ? 'آجل' : 'نقدي'} "
							class="form-control text-center font-weight-bold btn-outline-warning text-warning  mb-2" />


						<input disabled="disabled"
							value="العميل : ${billSell.client.name}"
							class="form-control text-center font-weight-bold btn-outline-warning text-warning  mb-2" />

						<form:form method="get" action="add-item-to-sell-bill"
							modelAttribute="item">

							<label>اسم الصنف</label>
							<form:select autofocus="autofocus"
								class="form-control text-center bg-light font-weight-bold "
								path="id">
								<form:options items="${itemsList}" itemLabel="name" />
							</form:select>


							<div class="form-group">
								<label>الكمية</label>
								<form:input id="quantity" path="quantity"
									class="form-control text-center bg-light font-weight-bold" />
							</div>

							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100 ">اضافة
								للفاتورة</button>


						</form:form>

					</div>
				</div>
			</div>

			<div class="mr-4 col-8 shadow">

				<div class="  font-weight-bold shadow "
					style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-dark table-striped table-sm  shadow ">

						<thead class="bg-primary  shadow">
							<tr>
								<th class="col-1">الصنف</th>
								<th class="col-1">الكمية</th>
								<th class="col-1">سعر البيع</th>
								<th class="col-1">اجمالي السعر</th>
								<th class="col-1">العميلة</th>
							</tr>
						</thead>

						<tbody class="shadow">

							<c:forEach var="itemTemp" items="${billSell.billSellItems}">

								<tr>
									<td class="border-primary pt-2">${itemTemp.item.name}</td>

									<td class="border-primary pt-2">${itemTemp.quantity}</td>
									<td class="border-primary pt-2"><fmt:formatNumber
											value="${itemTemp.sellPrice}" maxFractionDigits="2" /></td>
									<td class="border-primary pt-2"><fmt:formatNumber
											value=" ${itemTemp.sellPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

									<td class="border-primary"><a
										href="delete-sellBillItem?sellBillItemId=${itemTemp.id}"
										class="btn btn-danger btn-sm font-weight-bold"> إلغاء </a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>

				<span
					class=" btn bg-light text-dark  float-right mt-sm-4 shadow font-weight-bold">
					اجمالي الفاتورة : <fmt:formatNumber value="${total}"
						maxFractionDigits="2" />
				</span>

				<c:if test="${billSell.late}">
					<span
						class="btn bg-light text-dark float-right mt-sm-4 shadow font-weight-bold mr-2">
						المدفوع :</span>
					<input name="payed" form="form"
						class="form-control w-25 bg-light text-dark float-right mt-sm-4 shadow font-weight-bold mr-2 text-center" />

				</c:if>
				<div class="float-left pt-sm-4 pb-3">

					<div class="row">

						<a href="delete-sellBill?sellBillId=${billSell.id}"
							class="btn btn-danger shadow font-weight-bold"
							onclick="return confirm('هل انت متأكد من إلغاء الفاتورة ؟')">
							حذف </a>


						<c:if test="${billSell.billSellItems.size() > 0 }">

							<form id="form" method="get" action="save-sellBill">

								<input name="sellBillId" value="${billSell.id}" form="form"
									hidden="" /> <input type="submit"
									onclick="return confirm('هل انت متأكد من حفظ الفاتورة ؟')"
									value="حفظ"
									class="btn btn-success shadow mr-1 font-weight-bold ${billSellItems.size() eq 0 ? 'disabled' : ''} ">

							</form>

							<a href="show-printView?sellBillId=${billSell.id}"
								onclick="return confirm('هل انت متأكد من طباعة الفاتورة ؟')"
								class="btn btn-primary shadow mr-1  font-weight-bold ${billSellItems.size() eq 0 ? 'disabled' : ''} ">
								طباعة</a>

						</c:if>

						<c:if test="${billSell.billSellItems.size() eq 0 }">

							<button class="btn btn-success mr-1 shadow font-weight-bold "
								disabled>حفظ</button>

							<button class="btn btn-primary shadow mr-1  font-weight-bold"
								disabled>طباعة</button>

						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



