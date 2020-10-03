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
<title>طباعة فاتورة بيع</title>

<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<script type="text/javascript">
	function PrintElem() {
		var mywindow = window.open('', 'PRINT', 'height=400,width=600');

		mywindow.document.write('<body > ');
		mywindow.document.write('<h1>' + document.title + '</h1>');
		mywindow.document.write(document.getElementById("printDIV").innerHTML);
		mywindow.document.write('</body></html>');

		mywindow.document.close(); // necessary for IE >= 10
		mywindow.focus(); // necessary for IE >= 10*/

		mywindow.print();
		mywindow.close();

		return true;
	}
</script>

<style type="text/css">
.table td {
	border: black solid 3px !important;
	font-family: sans-serif;
}

.table th {
	border: black solid 3px !important;
	font-family: sans-serif;
}

@media print {
	@page {
		margin: 0;
	}
}
</style>
</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div class="container" style="padding-left: 65px; padding-right: 65px;">

		<div>
			<img style="height: 225px;" class="float-left mb-4"
				src="/images/army.jpg">
		</div>



		<div style="font-family: sans-serif;"
			class="float-right mt-sm-4 card border-dark p-3">
			<h2>الجيــش الثانــــي الميدانــــي</h2>

			<h2>فـــوج المشروعات الأنتاجية</h2>

			<h2>دايمــــــــــوند مــــــــــــــول</h2>
		</div>
	</div>


	<div id="printDIV"
		style="width: 100%; padding-left: 65px; padding-right: 65px; text-align: center;"
		class="container">

		<div dir="rtl" style="width: 100%;">

			<div class="" style="width: 100%;">

				<a onclick="this.hidden='true'; window.print(); "
					class="mb-4 btn btn-primary ${billSellItems.size() eq 0 ? 'disabled' : ''} ">
					طباعة</a>



				<table class="table table-striped table-lg table-bordered"
					style="width: 100%; font-size: 22px">

					<tbody>

						<tr>

							<td>رقم الفاتورة : S - ${billSell.id}</td>

							<td>التاريخ : <%=LocalDate.now().toString()%></td>

							<td>الوحدة : ${billSell.client.name}</td>

						</tr>

					</tbody>


				</table>

				<div class="" style="width: 100%;">

					<table class="table table-striped table-sm table-bordered"
						style="width: 100%; font-size: 22px; border: 3px solid black;">

						<thead>
							<tr style="font-style: inherit; font-size: 22px">
								<th>الصنف</th>
								<th>الكمية</th>
								<th>سعر الوحدة</th>
								<th>اجمالي السعر</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="itemTemp" items="${billSell.billSellItems}">

								<tr>
									<td>${itemTemp.item.name}</td>

									<td>${itemTemp.quantity}</td>
									<td><fmt:formatNumber value="${itemTemp.sellPrice}"
											maxFractionDigits="2" /></td>


									<td><fmt:formatNumber
											value="${itemTemp.sellPrice * itemTemp.quantity}"
											maxFractionDigits="2" /></td>

								</tr>

							</c:forEach>

						</tbody>
					</table>

				</div>


				<table class="table  table-lg table-bordered"
					style="width: 100%; font-size: 25px">

					<tbody>

						<tr>

							<td colspan="" style="font-size: 25px">اجمالي الفاتورة :</td>

							<td colspan="2"><fmt:formatNumber value="${total}"
									maxFractionDigits="2" /> [جنيه مصري]</td>
						</tr>

					</tbody>
				</table>

				<table class="table table-striped table-lg table-bordered"
					style="width: 100%; font-size: 25px">

					<tbody>
						<tr>

							<td>حالة الفاتورة : ${billSell.late ? "آجل" : "نـقـــدي"}</td>


							<td>المدفوع : <fmt:formatNumber value="${total}"
									maxFractionDigits="2" /></td>

							<td>المتبقي : --------</td>


						</tr>

						<tr>

							<td colspan="2">الرصيد الحالي : ${billSell.client.drawee}</td>

							<td>البائع : ${billSell.user.name}</td>




						</tr>


					</tbody>





				</table>


			</div>


		</div>

	</div>
</body>
</html>