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
<title>حركة صنف</title>

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">


<script src="webjars/jquery/3.5.1/jquery.js" type="text/javascript"></script>




</head>
<body>

	<%@ include file="header.jsp"%>

	<br>

	<div style="align-items: center; width: 100%; text-align: center;"
		class="">


		<div dir="rtl" class="row mr-lg-4">


			<div class="mr-3">

				<div class="card border-warning font-weight-bold  text-warning "
					style="min-width: 16rem;">
					<div class="card-header border-warning ">
						<h4>حركة صنف</h4>
					</div>

					<div id="" class="card-body bg-dark ">

						<c:set var="today" value="<%=LocalDate.now()%>"></c:set>

						<form method="post" action="item-moves">

							<label>اسم الصنف</label> <select name="itemId"
								class="form-control text-center bg-light font-weight-bold mb-2">
								<c:forEach var="tempItem" items="${itemsList}">
									<option
										${tempItem.id == selectedItemId ? 'selected="selected"' : ''}
										value="${tempItem.id}">${tempItem.name}</option>
								</c:forEach>

							</select> <label>التاريخ من</label> <input type="date" name="dateFrom"
								value="${dateFrom eq '' ? '' : dateFrom}"
								class="form-control text-center  bg-light font-weight-bold mb-2 datepicker">

							<label>التاريخ إلى</label> <input type="date" name="dateTo"
								value="${dateTo eq '' ? '' : dateTo}"
								class="form-control text-center  bg-light font-weight-bold mb-2 datepicker">

							<label>نوع الحركة</label> <select name="moveType"
								class="form-control text-center bg-light font-weight-bold mb-3 ">

								<option ${moveType eq '' ? 'selected="selected"' : ''} value="">غير
									محدد</option>

								<option ${moveType eq 'بيع' ? 'selected="selected"' : ''}
									value="بيع">بيع</option>

								<option ${moveType eq 'شراء' ? 'selected="selected"' : ''}
									value="شراء">شراء</option>

								<option ${moveType eq 'مرتجع عميل' ? 'selected="selected"' : ''}
									value="مرتجع عميل">مرتجع عميل</option>

								<option ${moveType eq 'مرتجع مورد' ? 'selected="selected"' : ''}
									value="مرتجع مورد">مرتجع مورد</option>

							</select>

							<button type="submit"
								class="btn btn-outline-warning btn-lg w-100 ">تقرير
								حركة صنف</button>

						</form>




					</div>
				</div>
			</div>


			<div class="mr-3 col-9">

				<div style="width: 100%; height: 460px; overflow: auto;"
					class=" shadow ">

					<table
						class="table  table-striped table-sm border-primary shadow font-weight-bold">

						<thead class="bg-primary  shadow "
							style="position: sticky; top: 0;">
							<tr class=" ">

								<th>الكود</th>
								<th>الحركة</th>
								<th>الكمية</th>
								<th>الرصيد</th>
								<th>سعر شراء</th>
								<th>جملة شراء</th>
								<th>سعر البيع</th>
								<th>جملة بيع</th>
								<th>التاريح</th>

							</tr>
						</thead>


						<tbody class="">
							<c:forEach var="itemMoveTemp" items="${movesList}">

								<tr dir="ltr"
									class="${ itemMoveTemp.type == 'مرتجع مورد' ? 'bg-info' : '' }  
									${ itemMoveTemp.type == 'مرتجع عميل' ? 'bg-warning' : '' } 
									${ itemMoveTemp.type == 'بيع' ? 'bg-danger ' : 'bg-success' } ">
									<td class=" " id="itemId${itemMoveTemp.id}">${itemMoveTemp.id}</td>

									<td class=" ">${itemMoveTemp.type}</td>

									<td class=" " id="itemQuantity${itemMoveTemp.id}">${itemMoveTemp.quantity}</td>

									<td class=" " id="itemQuantity${itemMoveTemp.id}">${itemMoveTemp.balance}</td>



									<c:set var="quantity" value="${itemMoveTemp.quantity}" />

									<c:set var="quantity"
										value="${quantity < 0 ? quantity * -1 : quantity}" />

									<c:set var="val" value="${itemMoveTemp.buyPrice  *  quantity}" />

									<c:if test="${val != 0}">

										<td class=" " id="itemBuyPrice${itemMoveTemp.id}">${itemMoveTemp.buyPrice}</td>


										<td class=" "><fmt:formatNumber
												value="${val < 0 ? val * -1 : val}" maxFractionDigits="2" /></td>

									</c:if>
									<c:if test="${val == 0}">

										<td class=" ">-----</td>
										<td class=" ">-----</td>

									</c:if>



									<c:set var="val" value="${itemMoveTemp.sellPrice  * quantity}" />


									<c:if test="${val != 0}">

										<td class=" " id="itemIdSellPrice${itemMoveTemp.id}">${itemMoveTemp.sellPrice}</td>
										<td class=" "><fmt:formatNumber
												value="${val < 0 ? val * -1 : val}" maxFractionDigits="2" /></td>

									</c:if>



									<c:if test="${val == 0}">

										<td class=" ">-----</td>
										<td class=" ">-----</td>

									</c:if>


									<td class="  ">${itemMoveTemp.date}</td>
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

							<th>عدد الحركات : ${movesList.size()}</th>

							<th>جملة شراء : <fmt:formatNumber value="${totalItemsBuys}"
									maxFractionDigits="2" /></th>
							<th>جملة بيع : <fmt:formatNumber value="${totalItemsSales}"
									maxFractionDigits="2" /></th>

							<th>جملة ربح : <fmt:formatNumber
									value="${totalItemsSales - totalItemsBuys}"
									maxFractionDigits="2" /></th>

						</tr>
					</thead>

				</table>
			</div>

		</div>

	</div>
</body>
</html>