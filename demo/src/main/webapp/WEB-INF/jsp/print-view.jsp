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
.table {
	border: black solid 3px !important;
}

.table td {
	border: black solid 3px !important;
}

.table th {
	border: black solid 3px !important;
}
</style>
</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div id="printDIV" style="width: 100%; text-align: center;">


		<div dir="rtl" style="width: 100%;">

			<div class="" style="width: 100%;">

				<table class="table table-striped table-lg table-bordered"
					style="width: 100%; font-size: 25px">

					<tbody>


						<tr>

							<td>رقم الفاتورة : ${billSell.id}</td>

							<td>التاريخ : <%=LocalDate.now().toString()%></td>

							<td>الوحدة : ${billSell.client.name}</td>


						</tr>

					</tbody>


				</table>

				<div class="" style="width: 100%;">

					<table class="table table-striped table-lg table-bordered"
						style="width: 100%; font-size: 25px; border: 5px solid black;">

						<thead>
							<tr>
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

							<td colspan="" style="font-size: 25px">اجمالي الفاتورة:</td>

							<td colspan="2"><fmt:formatNumber value="${total}"
									maxFractionDigits="2" /> [جنيه مصري]</td>
						</tr>

					</tbody>
				</table>

				<table class="table  table-lg table-bordered"
					style="width: 100%; font-size: 25px">

					<tbody>
						<tr>

							<td>حالة الفاتورة: ${billSell.late ? "آجل" : "نـقـــدي"}</td>

							<td>المدفوع : ${total}</td>

							<td>المتبقي : --------</td>


						</tr>

					</tbody>





				</table>

				<form:form>
					<a onclick="this.hidden='true'; window.print(); "
						class="btn btn-primary ${billSellItems.size() eq 0 ? 'disabled' : ''} ">
						طباعة</a>
				</form:form>
			</div>


		</div>

	</div>
</body>
</html>