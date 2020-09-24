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

</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center;" class="container">
		<div dir="rtl" class="row">

			<div>




				<div class="form-group">
					<label>رقم الفاتورة</label> <input disabled="true"
						value="<%=session.getAttribute("billSellId")%>"
						class="form-control text-center btn-outline-primary" />
				</div>

				<div class="form-group">
					<label>التاريخ</label> <input disabled="true"
						value="<%=LocalDate.now().toString()%>"
						class="form-control text-center btn-outline-primary" />
				</div>

				<div class="form-group">
					<label>الجهة</label> <input disabled="true"
						value="<%=session.getAttribute("clientName")%>"
						class="form-control text-center btn-outline-primary" />
				</div>



				<form:form method="get" action="add-item-to-sell-bill"
					modelAttribute="item">

					<div class="form-group">
						<label>اسم الصنف</label>
						<form:select class="form-control text-center " path="id">
							<form:options items="${itemsList}" itemLabel="name" />
						</form:select>
					</div>


					<div class="form-group">
						<label>الكمية</label>
						<form:input id="quantity" path="quantity"
							class="form-control text-center" />
					</div>

					<button type="submit" class="btn btn-primary btn-lg w-100">اضافة
						للفاتورة</button>


				</form:form>

			</div>

			<div class="mr-4 col-8">

				<div style="position: relative; height: 425px; overflow: auto;">

					<table class="table table-striped table-sm  table-bordered">

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
									<td>${itemTemp.item.name}</td>
									<td>${itemTemp.quantity}</td>
									<td>${itemTemp.sellPrice}</td>
									<td>${itemTemp.sellPrice * itemTemp.quantity}</td>

									<td><a
										href="delete-sellBillItem?sellBillItemId=${itemTemp.id}"
										class="btn btn-outline-danger btn-sm"> ألغاء </a></td>



								</tr>

							</c:forEach>

						</tbody>
					</table>




				</div>

				<div class="float-left pt-sm-4">

					<form:form>



						<a
							href="delete-sellBill?sellBillId=<%=session.getAttribute("billSellId")%>"
							class="btn btn-danger "
							onclick="return confirm('هل انت متأكد من ألغاء الفاتورة ؟')">

							ألغاء </a>


						<a
							href="save-sellBill?sellBillId=<%=session.getAttribute("billSellId")%>"
							class="btn btn-success"> حفظ</a>


						<button class="btn btn-primary">طباعة</button>
					</form:form>

				</div>

			</div>


		</div>

	</div>
</body>
</html>