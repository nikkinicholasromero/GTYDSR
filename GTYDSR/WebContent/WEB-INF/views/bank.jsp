<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>GTY | DSR</title>
<link rel="stylesheet" type="text/css" href="resources/sbadmin/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/sbadmin/bower_components/metisMenu/dist/metisMenu.min.css">
<link rel="stylesheet" type="text/css" href="resources/sbadmin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/sbadmin/bower_components/datatables-responsive/css/dataTables.responsive.css">
<link rel="stylesheet" type="text/css" href="resources/sbadmin/dist/css/sb-admin-2.css">
<link rel="stylesheet" type="text/css" href="resources/sbadmin/bower_components/font-awesome/css/font-awesome.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>

<body>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="">GTY DSR</a>
			</div>
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><a href="banks">Bank</a></li>
						<li><a href="branches">Branches</a></li>
						<li><a href="discrepancies">Discrepancies</a></li>
						<li><a href="records">Records</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Bank</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-md-2 col-xs-12">
					<h4 class="box-title">
						<a href="#addBankModal" role="button" class="btn btn-lg btn-success" data-toggle="modal" style="box-shadow: 0px 4px 8px #888888">
							ADD BANK
						</a>
					</h4>
				</div>
			</div>

			<div id="successNotificationDiv" class="alert alert-success" style="display: none">
				<p id="successNotificationMessage"></p>
			</div>

			<div id="addBankModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Bank Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successAddModalNotificationDiv" class="alert alert-warning" style="display: none">
										<p id="successAddModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-8 col-xs-12">
									<label>Name</label>
									<input type="text" class="form-control input-sm" id="addName" name="addName" maxlength="50">
								</div>
								<div class="col-md-4 col-xs-12">
									<label>Status</label>
									<select class="form-control input-sm" id="addStatus" name="addStatus">
										<option value="Active">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnAddBank" onclick="saveNewBank()">Save</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div id="updateBankModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Bank Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successUpdateModalNotificationDiv" class="alert alert-warning" style="display: none">
										<p id="successUpdateModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<input type="hidden" class="form-control input-sm" id="updateId" name="updateId">
								<div class="col-md-8 col-xs-12">
									<label>Name</label>
									<input type="text" class="form-control input-sm" id="updateName" name="updateName" maxlength="50">
								</div>
								<div class="col-md-4 col-xs-12">
									<label>Status</label>
									<select class="form-control input-sm" id="updateStatus" name="updateStatus">
										<option value="Active">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnUpdateBank" onclick="updateBank()">Update</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Banks</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover" id="dataTables-banks">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Status</th>
											<th>Update</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${banks}" var="bank">
											<tr id="bankId_${bank.id}">
												<td id="bankRowId_${bank.id}"><c:out value="${bank.id}" /></td>
												<td id="bankRowName_${bank.id}"><c:out value="${bank.name}" /></td>
												<td id="bankRowStatus_${bank.id}"><c:out value="${bank.status}" /></td>
												<td style="width: 100px;"><input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateBankModal" data-id="${bank.id}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/sbadmin/bower_components/jquery/dist/jquery.min.js"></script>
	<script src="resources/sbadmin/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="resources/sbadmin/bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<script src="resources/sbadmin/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script src="resources/sbadmin/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<script src="resources/sbadmin/dist/js/sb-admin-2.js"></script>
	<script> 
		$(document).ready(function() {
			$('#dataTables-banks').DataTable({
				responsive : true,
		        "order": [[ 0, "desc" ]]
			});
		});

		$('#addBankModal').on('show.bs.modal', function(e) {
			$('#addName').val("");
			$('#addStatus option[value=Active]').attr('selected', 'selected');
			$('#successAddModalNotificationDiv').css('display', 'none');
			$('#successAddModalNotificationMessage').text("");
		});

		function saveNewBank() {
			var result = insertNewBank();
			var name = $("#addName").val().trim();
			if (result == "success") {
				$('#addBankModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully added new bank");
				addNewBankToTable(name);
			} else {
				$('#successAddModalNotificationDiv').css('display', 'block');
				$('#successAddModalNotificationMessage').text(result);
			}
		}

		function insertNewBank() {
			var validationResult = "";
			var name = $("#addName").val().trim();
			var status = $("#addStatus").val();
			$.ajax({
				type : 'POST',
				url : 'addNewBank',
				data : {
					'name' : name,
					'status' : status
				},
				async : false,
				success : function(result) {
					validationResult = result;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					validationResult = jqXHR.status + ' ' + jqXHR.responseText;
				}
			});

			return validationResult;
		}

		function addNewBankToTable(name) {
			$.ajax({
				type : 'POST',
				url : 'getBankByBankName',
				data : {
					'name' : name
				},
				dataType : 'json',
				async : true,
				success : function(result) {
					var row = '<tr id="bankId_' + result.id + '">';
					row += '<td id="bankRowId_' + result.id + '">' + result.id + '</td>';
					row += '<td id="bankRowName_' + result.id + '">' + result.name + '</td>';
					row += '<td id="bankRowStatus_' + result.id + '">' + result.status + '</td>';
					row += '<td style="width: 100px;"><input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateBankModal" data-id="' + result.id + '"/></td>';
					row += '</tr>';
					$('#dataTables-banks tr:first').after(row);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status + ' ' + jqXHR.responseText);
					alert(errorThrown);
				}
			});
		}

		$('#updateBankModal').on('show.bs.modal', function(e) {
			var id = $(e.relatedTarget).data('id');
			$('#updateId').attr("value", $('#bankRowId_' + id).text());
			$('#updateName').attr("value", $('#bankRowName_' + id).text());
			$('[name=updateStatus]').val($('#bankRowStatus_' + id).text());
			$('#successUpdateModalNotificationDiv').css('display', 'none');
			$('#successUpdateModalNotificationMessage').text("");
		});

		function updateBank() {
			var result = updateExistingBank();
			var id = $("#updateId").val();
			var name = $("#updateName").val().trim();
			var status = $("#updateStatus").val();
			if (result == "success") {
				$('#updateBankModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully updated bank");
				updateBankToTable(id, name, status);
			} else {
				$('#successUpdateModalNotificationDiv').css('display', 'block');
				$('#successUpdateModalNotificationMessage').text(result);
			}
		}

		function updateExistingBank() {
			var validationResult = "";
			var id = $("#updateId").val();
			var name = $("#updateName").val().trim();
			var status = $("#updateStatus").val();
			$.ajax({
				type : 'POST',
				url : 'updateBank',
				data : {
					'id' : id,
					'name' : name,
					'status' : status
				},
				async : false,
				success : function(result) {
					validationResult = result;
				},
				error : function(jqXHR, textStatus, errorThrown) {
					validationResult = jqXHR.status + ' ' + jqXHR.responseText;
				}
			});

			return validationResult;
		}

		function updateBankToTable(id, name, status) {
			$('#bankRowId_' + id).text(id);
			$('#bankRowName_' + id).text(name);
			$('#bankRowStatus_' + id).text(status);
		}
	</script>
</body>

</html>