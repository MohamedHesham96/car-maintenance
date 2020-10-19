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

<nav dir="rtl" style="align-items: flex-end;"
	class="navbar navbar-expand-lg navbar-dark bg-primary shadow ">

	<ul class="navbar-nav mr-auto ">

		<li dir="ltr" class=" mr-2  ">
			<div class="btn-group pt-2 " role="group">

				<button type="button"
					class="btn bg-dark border-warning font-weight-bold ">مبيعات</button>

				<div class="btn-group " role="group">

					<button id="btnGroupDrop2" onclick="showSellBill(this);"
						type="button" class="btn bg-dark border-warning dropdown-toggle"></button>

					<div id="sellBill" class="dropdown-menu border-warning" style=""
						onmouseleave="showSellBill(this);">
						<a class="dropdown-item  bg-warning font-weight-bold"
							href="show-sell-bill-info">فاتورة بيع</a> <a
							class="dropdown-item  bg-warning font-weight-bold"
							href="show-sell-bill-list"> فواتير البيع</a>
					</div>


				</div>
			</div>


		</li>




		<li dir="ltr" class="mr-2">
			<div class="btn-group pt-2" role="group">

				<button type="button"
					class="btn bg-dark border-warning font-weight-bold">مشتريات</button>

				<div class="btn-group " role="group">

					<button id="btnGroupDrop2" onclick="showBuyBill(this);"
						type="button" class="btn bg-dark border-warning dropdown-toggle"></button>

					<div id="buyBill" class="dropdown-menu" style=""
						onmouseleave="showBuyBill(this);">

						<a class="dropdown-item bg-warning font-weight-bold"
							href="show-buy-bill-info">فاتورة شراء</a> <a
							class="dropdown-item font-weight-bold bg-warning"
							href="show-buy-bill-list">فواتير الشراء</a>
					</div>


				</div>
			</div>


		</li>






		<li dir="ltr" class=" mr-2 ">
			<div class="btn-group pt-2" role="group">

				<button type="button"
					class="btn bg-dark border-warning font-weight-bold ">مرتجعات</button>

				<div class="btn-group " role="group">

					<button id="btnGroupDrop2" onclick="showReturnBill(this);"
						type="button" class="btn bg-dark border-warning dropdown-toggle"></button>

					<div id="returnBill" class="dropdown-menu" style=""
						onmouseleave="showReturnBill(this);">


						<a class="dropdown-item font-weight-bold bg-warning"
							href="show-return-bill-info" style="font-size: 15px;">فاتورة
							مرتجع عميل</a>
							
							 <a class="dropdown-item font-weight-bold bg-warning "
							href="show-return-bill-list" style="font-size: 15px;">فواتير
							مرتجع عميل</a> 
							
							<a class="dropdown-item font-weight-bold bg-info "
							href="show-company-return-bill-info" style="font-size: 15px;">فاتورة
							مرتجع مورد </a> 
							
							<a class="dropdown-item font-weight-bold bg-info"
							href="show-company-return-bill-list" style="font-size: 15px;">فواتير
							مرتجع مورد</a>

					</div>

				</div>
			</div>

		</li>



		<li
			class="btn bg-dark border-warning  font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link btnd" href="items-list">المخزن</a></li>



		<li
			class="btn bg-dark border-warning font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link" href="item-moves">حركة صنف</a></li>



		<li
			class="btn bg-dark border-warning font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link" href="spend-list"> الخزنة</a></li>




		<li
			class="btn bg-dark border-warning font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link " href="companies-list">الموردين</a></li>

		<li
			class="btn bg-dark border-warning btn-primary font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link" href="clients-list">الوحدات</a></li>

		<li
			class="btn bg-dark border-warning   font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link" href="show-today-report">تقرير اليوم</a></li>




		<li
			class="btn-sm btn-danger font-weight-bold nav-item active mr-2 shadow"><a
			class="nav-link" href="logout"
			onclick="return confirm('هل انت متأكد من الخروج ؟')"
			style="font-size: 15px;"> <%=session.getAttribute("name")%> |
				خروج
		</a></li>



	</ul>

</nav>