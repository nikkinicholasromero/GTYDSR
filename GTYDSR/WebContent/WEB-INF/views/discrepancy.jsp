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
					<h1 class="page-header">Discrepancy</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-md-2 col-xs-12">
					<h4 class="box-title">
						<a href="#addDiscrepancyModal" role="button" class="btn btn-lg btn-success" data-toggle="modal" style="box-shadow: 0px 4px 8px #888888">
							ADD DISCREPANCY
						</a>
					</h4>
				</div>
			</div>

			<div id="successNotificationDiv" class="alert alert-success alert-dismissable" style="display: none">
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				<p id="successNotificationMessage"></p>
			</div>

			<div id="addDiscrepancyModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Discrepancy Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successAddModalNotificationDiv" class="alert alert-warning alert-dismissable" style="display: none">
										<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
										<p id="successAddModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<label>Type</label> 
									<input type="text" class="form-control input-sm" id="addType" name="addType" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<label>Description</label>
									<input type="text" class="form-control input-sm" id="addDescription" name="addDescription" maxlength="255">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnAddDiscrepancy" onclick="saveNewDiscrepancy()">Save</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div id="updateDiscrepancyModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Discrepancy Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successUpdateModalNotificationDiv" class="alert alert-warning alert-dismissable" style="display: none">
										<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
										<p id="successUpdateModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<input type="hidden" class="form-control input-sm" id="updateId" name="updateId">
								<div class="col-md-8 col-xs-12">
									<label>Type</label>
									<input type="text" class="form-control input-sm" id="updateType" name="updateType" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-8 col-xs-12">
									<label>Description</label>
									<input type="text" class="form-control input-sm" id="updateDescription" name="updateDescription" maxlength="255">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnUpdateDiscrepancy" onclick="updateDiscrepancy()">Update</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Discrepancies</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover" id="dataTables-discrepancies">
									<thead>
										<tr>
											<th>ID</th>
											<th>Type</th>
											<th>Description</th>
											<th>Update</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${discrepancies}" var="discrepancy">
											<tr id="discrepancyId_${discrepancy.id}">
												<td id="discrepancyRowId_${discrepancy.id}"><c:out value="${discrepancy.id}" /></td>
												<td id="discrepancyRowType_${discrepancy.id}"><c:out value="${discrepancy.type}" /></td>
												<td id="discrepancyRowDescription_${discrepancy.id}"><c:out value="${discrepancy.description}" /></td>
												<td style="width: 100px;">
													<input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateDiscrepancyModal" data-id="${discrepancy.id}" />
												</td>
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
			$('#dataTables-discrepancies').DataTable({
				responsive : true,
		        "order": [[ 0, "desc" ]]
			});
		});

		$('#addDiscrepancyModal').on('show.bs.modal', function(e) {
			$('#addType').val("");
			$('#addDescription').val("");
			$('#successAddModalNotificationDiv').css('display', 'none');
			$('#successAddModalNotificationMessage').text("");
		});

		function saveNewDiscrepancy() {
			var result = insertNewDiscrepancy();
			var type = $("#addType").val();
			if (result == "success") {
				$('#addDiscrepancyModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully added new discrepancy");
				addNewDiscrepancyToTable(type);
			} else {
				$('#successAddModalNotificationDiv').css('display', 'block');
				$('#successAddModalNotificationMessage').text(result);
			}
		}

		function insertNewDiscrepancy() {
			var validationResult = "";
			var type = $("#addType").val().trim();
			var description = $("#addDescription").val().trim();
			$.ajax({
				type : 'POST',
				url : 'addNewDiscrepancy',
				data : {
					'type' : type,
					'description' : description
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

		function addNewDiscrepancyToTable(type) {
			$.ajax({
				type : 'POST',
				url : 'getDiscrepancyByDiscrepancyType',
				data : {
					'type' : type
				},
				dataType : 'json',
				async : true,
				success : function(result) {
					var row = '<tr id="dicrepancyId_' + result.id + '">';
					row += '<td id="discrepancyRowId_' + result.id + '">' + result.id + '</td>';
					row += '<td id="discrepancyRowType_' + result.id + '">' + result.type + '</td>';
					row += '<td id="discrepancyRowDescription_' + result.id + '">' + result.description + '</td>';
					row += '<td style="width: 100px;"><input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateDiscrepancyModal" data-id="' + result.id + '"/></td>';
					row += '</tr>';
					$('#dataTables-discrepancies tr:first').after(row);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status + ' ' + jqXHR.responseText);
					alert(errorThrown);
				}
			});
		}

		$('#updateDiscrepancyModal').on('show.bs.modal', function(e) {
			var id = $(e.relatedTarget).data('id');
			$('#updateId').attr("value", $('#discrepancyRowId_' + id).text());
			$('#updateType').attr("value", $('#discrepancyRowType_' + id).text());
			$('#updateDescription').attr("value", $('#discrepancyRowDescription_' + id).text());
			$('#successUpdateModalNotificationDiv').css('display', 'none');
			$('#successUpdateModalNotificationMessage').text("");
		});

		function updateDiscrepancy() {
			var result = updateExistingDiscrepancy();
			var id = $("#updateId").val().trim();
			var type = $("#updateType").val().trim();
			var description = $("#updateDescription").val().trim();
			if (result == "success") {
				$('#updateDiscrepancyModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully updated discrepancy");
				updateDiscrepancyToTable(id, type, description);
			} else {
				$('#successUpdateModalNotificationDiv').css('display', 'block');
				$('#successUpdateModalNotificationMessage').text(result);
			}
		}

		function updateExistingDiscrepancy() {
			var validationResult = "";
			var id = $("#updateId").val().trim();
			var type = $("#updateType").val().trim();
			var description = $("#updateDescription").val().trim();
			$.ajax({
				type : 'POST',
				url : 'updateDiscrepancy',
				data : {
					'id' : id,
					'type' : type,
					'description' : description
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

		function updateDiscrepancyToTable(id, type, description) {
			$('#discrepancyRowId_' + id).text(id);
			$('#discrepancyRowType_' + id).text(type);
			$('#discrepancyRowDescription_' + id).text(description);
		}
	</script>
</body>

</html>