<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- tabulator -->
<link href="https://unpkg.com/tabulator-tables@5.5.2/dist/css/tabulator.min.css" rel="stylesheet">
<script type="text/javascript" src="https://unpkg.com/tabulator-tables@5.5.2/dist/js/tabulator.min.js"></script>
<!-- XLSX Script -->
<script type="text/javascript" src="https://oss.sheetjs.com/sheetjs/xlsx.full.min.js"></script>
<!-- PDF Script -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.20/jspdf.plugin.autotable.min.js"></script>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>

<body>
	<div>
		<button id="download-csv">Download CSV</button>
		<button id="download-json">Download JSON</button>
	    <button id="download-xlsx">Download XLSX</button>
	    <button id="download-pdf">Download PDF</button>
	    <button id="download-html">Download HTML</button>	  
	    <button id="print-table">Print Table</button>  ㄹㄷ
	</div>
	<br>
	<div id="list"></div>
	
	<script>
		$(function() {
			$.ajax({
				url : "/bookList",
				method : "GET",
				dataType : "JSON",
				success : function(data) {
					table.setData(data);
				}
			});
			var table = new Tabulator('#list', {
				placeholder:"No Data Available",
				layout:"fitColumns",
				columns: [
					{title:"title", field:"title", sorter:"string"},
					{
						title:"all",
						columns:[
							{title:"title", field:"title", sorter:"string"},
							{title:"category", field:"category"},
							{title:"price", field:"price", bottomCalc:"sum"},
						],
					},
					{title:"category", field:"category"},
				],
			});
			
			document.getElementById("download-xlsx").addEventListener("click", function() {
				table.download('xlsx', 'data.xlsx', {sheetName:"BookList"});
			});
			
			document.getElementById("download-pdf").addEventListener("click", function() {
				table.download('pdf', 'data.pdf', {orientation:"portrait", title:"BookList"});
			});

			document.getElementById("print-table").addEventListener("click", function() {
				table.print(false, true);
			});
		});
	</script>
</body>
</html>