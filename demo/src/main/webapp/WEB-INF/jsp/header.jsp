<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav dir="rtl" style="align-items: flex-end;"
	class="navbar navbar-expand-lg navbar-dark bg-dark">

	<ul class="navbar-nav mr-auto">

		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="items-list">الرئيسية</a></li>

		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-sell-bill-info">فاتورة بيع</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-sell-bill-list">فواتير البيع</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-buy-bill-info">فاتورة شراء</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-buy-bill-list">فواتير الشراء</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-return-bill-info">فاتورة مرتجع</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-return-bill-list">فواتير مرتجع</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="spend-list"> المصاريف</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href=""> الخزنة</a></li>


		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1  "><a
			class="nav-link" href="companies-list">الموردين</a></li>

		<li class=" btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="clients-list">الوحدات</a></li>

		<li class="btn-sm btn-info font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="show-today-report">تقرير اليوم</a></li>

		<li class="text-white mr-1 " style="font-size: 30px">|</li>


		<li class="btn-sm btn-danger font-weight-bold nav-item active mr-1"><a
			class="nav-link" href="logout"
			onclick="return confirm('هل انت متأكد من الخروج ؟')"> <%=session.getAttribute("user").toString()%>
				| خروج
		</a></li>



	</ul>

</nav>