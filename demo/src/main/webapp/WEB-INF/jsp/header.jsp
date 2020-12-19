<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	function showSellBill(btn) {
		var sellBill = document.getElementById("sellBill");

		if (sellBill.style.display === "block") {
			sellBill.style.display = "none";
		} else {
			sellBill.style.display = "block";

		}
	}

	function showBuyBill(btn) {
		var buyBill = document.getElementById("buyBill");

		if (buyBill.style.display === "block") {
			buyBill.style.display = "none";
		} else {
			buyBill.style.display = "block";

		}
	}

	function showReturnBill(btn) {
		var returnBill = document.getElementById("returnBill");

		if (returnBill.style.display === "block") {
			returnBill.style.display = "none";
		} else {
			returnBill.style.display = "block";

		}
	}
</script>

<nav dir="rtl" style="align-items: flex-end; background-color: gray;"
	class="navbar navbar-expand-lg navbar-dark bg-dark  shadow ">

	<ul class="navbar-nav mr-auto ">




		<li
			class="${home} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow "
			style="min-height: 55px"><a class="nav-link active "
			${sessionScope.isHasMain ?  'href="home"' : ''}>الرئيسية </a></li>

		<li dir="ltr" class=" mr-2">
			<div class="btn-group " role="group" style="min-height: 55px">

				<button type="button"
					class="${sales}
			btn btn-dark border-warning font-weight-bold ">مبيعات
				</button>

				<div class="btn-group " role="group">

					<button id="btnGroupDrop2"
						${sessionScope.isHasSales ?  'onclick="showSellBill(this);"' : ''}
						type="button"
						class="${sales} btn btn-dark border-warning dropdown-toggle"></button>

					<div id="sellBill" class="dropdown-menu  text-center" style=""
						onmouseleave="showSellBill(this);">

						<a
							class="btn dropdown-item  bg-dark text-warning font-weight-bold"
							href="show-sell-bill-info">فاتورة بيع</a> <a
							class="dropdown-item  bg-dark text-warning font-weight-bold"
							href="show-sell-bill-list"> فواتير بيع العملاء</a> <a
							class="dropdown-item font-weight-bold bg-dark text-warning"
							href="show-return-bill-info" style="font-size: 15px;"> مرتجع
							عميل</a> <a
							class="dropdown-item font-weight-bold bg-dark text-warning"
							href="show-return-bill-list" style="font-size: 15px;">فواتير
							مرتجع العملاء</a>
					</div>

				</div>
			</div>
		</li>

		<li dir="ltr" class="mr-2 ">
			<div class="btn-group " role="group" style="min-height: 55px">

				<button type="button"
					class="${buys} btn btn-dark border-warning font-weight-bold">مشتريات</button>

				<div class="btn-group " role="group">

					<button id="btnGroupDrop2"
						${sessionScope.isHasBuys ?  'onclick="showBuyBill(this);"' : ''}
						type="button"
						class="${buys} btn btn-dark border-warning dropdown-toggle"></button>

					<div id="buyBill" class="dropdown-menu text-center" style=""
						onmouseleave="showBuyBill(this);">

						<a class="dropdown-item bg-dark text-warning font-weight-bold"
							href="show-buy-bill-info">فاتورة شراء</a> <a
							class="dropdown-item font-weight-bold bg-dark text-warning"
							href="show-buy-bill-list">فواتير شراء الموردين</a> <a
							class="dropdown-item font-weight-bold bg-dark text-warning"
							href="show-company-return-bill-info" style="font-size: 15px;">
							مرتجع مورد</a> <a
							class="dropdown-item font-weight-bold bg-dark text-warning"
							href="show-company-return-bill-list" style="font-size: 15px;">فواتير
							مرتجع الموردين</a>
					</div>
				</div>
			</div>

		</li>

		<li
			class="${items_list} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow "
			style="min-heitems_listight: 55px"><a class="nav-link active "
			${sessionScope.isHasStore ?  'href="items-list"' : ''}>المخزن</a></li>

		<li
			class="${item_moves} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasItemMove ?  'href="item-moves"' : ''}>حركة
				صنف</a></li>

		<li
			class="${bank} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasBank ?  'href="bank-menu"' : ''}> إدارة
				الخزنة </a></li>

		<li
			class="${clients_list} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasClients ?  'href="clients-list"' : ''}>العملاء</a></li>

		<li
			class="${companies_list} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasCompanies ?  'href="companies-list"' : ''}>الموردين</a></li>

		<li
			class="${today_report} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasReports ?  'href="show-report"' : ''}>إدارة
				التقارير</a></li>
		<li
			class="${users_list} btn btn-dark border-warning font-weight-bold nav-item  mr-2 shadow"><a
			class="nav-link active"
			${sessionScope.isHasMain ?  'href="users-list"' : ''}>الاعدادات</a></li>

		<li
			class="btn-sm btn-danger font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link active" href="logout"
			onclick="return confirm('هل انت متأكد من الخروج ؟')"
			style="font-size: 15px;"> ${sessionScope.name} | خروج </a></li>

	</ul>

</nav>