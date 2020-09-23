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

	<div style="text-align: center;" class="text-center container">
		<div dir="rtl" class="row">

			<form:form modelAttribute="sellBill" method="post"
				action="save-sell-bill-info">

				<!-- 				<div class="form-group"> -->
				<!-- 					<label>رقم الفاتورة</label> -->
				<%-- 					<form:input path="billId" class="form-control text-center" /> --%>
				<!-- 				</div> -->

				<div class="form-group">
					<label>التاريخ</label> 
					
					<input disabled="true" value="<%=LocalDate.now().toString()%>"
						class="form-control text-center btn-outline-primary" />
				</div>

				<div class="form-group">
					<label>الجهة</label>
					<form:select class="form-control text-center" path="clientId">
						<form:options items="${clientsList}" itemLabel="name" />
					</form:select>
				</div>

				<div class="form-group">
					<label>آجل</label>

					<form:checkbox path="late" />

				</div>

				<button type="submit" class="btn btn-primary btn-lg w-100">عمل
					فاتورة</button>

			</form:form>



		</div>

	</div>
</body>
</html>