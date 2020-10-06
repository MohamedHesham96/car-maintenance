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



<link href="webjars/bootswatch/4.5.2/dist/darkly/_bootswatch.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/_variables.scss"
	rel="stylesheet">

<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.min.css"
	rel="stylesheet">


<link href="webjars/bootswatch/4.5.2/dist/darkly/bootstrap.css"
	rel="stylesheet">


</head>
<body>


	<div style="text-align: center;" class="container shadow mt-4">

		<div dir="rtl" class="row">

			<div class="card border-primary float-left w-25 shadow"
				style="margin: 0; position: absolute; top: 45%; left: 50%; transform: translate(-50%, -50%);">
				<div class="card-header ">
					<h3 class="font-weight-bold ">تسجيل الدخول</h3>
				</div>

				<div class="card-body">


					<form:form modelAttribute="user" method="GET" action="login">

						<div class="form-group ">

							<label for="exampleInputEmail1">اسم المستخدم</label>
							<form:input path="name"
								class="form-control h-25 btn-lg font-weight-bold "
								cssStyle="text-align: center; font-size: 35px;" />
						</div>


						<div class="form-group">
							<label for="exampleInputPassword1">كلمة المرور</label>
							<form:input path="password" type="password"
								class="form-control h-25 btn-lg font-weight-bold"
								cssStyle="text-align: center; font-size: 35px;" />
						</div>

						<button type="submit" class="btn btn-primary">تسجيل
							الدخول</button>

					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>