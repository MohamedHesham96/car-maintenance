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
<title>ادارة الخزنة</title>


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">
</head>
<body background="images/background.jpg"
	style="background-attachment: fixed; background-repeat: no-repeat; background-size: cover; background-color: gray;">

	<%@ include file="header.jsp"%>

	<br>

	<div style="text-align: center; width: 100%;" class="text-center ">


		<div dir="rtl" class="row mt-lg-4 "
			style="width: 800px; margin: 0 auto;">

			<div class="card border-warning text-warning w-50  shadow"
				style="margin: 0; position: absolute; top: 45%; left: 50%; transform: translate(-50%, -50%); max-width: 24rem;">

				<div class="card-header border-warning  ">
					<h4>إدارة الخزنة</h4>
				</div>

				<div class="card-body">


					<a type="button" class="btn btn-success btn-lg w-75"
						href="balances-list">إدارة الأرصدة</a> <a type="button"
						class="btn btn-danger btn-lg w-75 mt-3" href="spend-list">إدارة
						المصاريف</a>
				</div>

			</div>


		</div>



	</div>
</body>
</html>