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
					<h1 class="page-header">Branch</h1>
				</div>
			</div>

			<div class="row">
				<div class="col-md-2 col-xs-12">
					<h4 class="box-title">
						<a href="#addBranchModal" role="button" class="btn btn-lg btn-success" data-toggle="modal" style="box-shadow: 0px 4px 8px #888888">
							ADD BRANCH
						</a>
					</h4>
				</div>
			</div>

			<div id="successNotificationDiv" class="alert alert-success" style="display:none">
				<p id="successNotificationMessage"></p>
			</div>

			<div id="addBranchModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Branch Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successAddModalNotificationDiv" class="alert alert-warning" style="display:none">
										<p id="successAddModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-6 col-xs-12">
									<label>Name</label>
									<input type="text" class="form-control input-sm" id="addName" name="addName" maxlength="50">
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Location</label>
									<input type="text" class="form-control input-sm" id="addLocation" name="addLocation" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-6 col-xs-12">
									<label>Proprietor</label>
									<input type="text" class="form-control input-sm" id="addProprietor" name="addProprietor" maxlength="50">
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Contact Number</label>
									<input type="text" class="form-control input-sm" id="addContactNumber" name="addContactNumber" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-6 col-xs-12">
									<label>Remittance Bank</label> 
									<input type="text" class="form-control input-sm" id="addRemittanceBank" name="addRemittanceBank" list="banks" maxlength="50">
									<datalist id="banks">
										<c:forEach items="${banks}" var="bank">
											<option value="${bank.name}">
										</c:forEach>
									</datalist>
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Status</label>
									<select class="form-control input-sm"
										id="addStatus" name="addStatus">
										<option value="Active">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnAddBranch" onclick="saveNewBranch()">Save</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div id="updateBranchModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title">Branch Form</h4>
						</div>
						<div class="modal-body">
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-12 col-xs-12">
									<div id="successUpdateModalNotificationDiv" class="alert alert-warning" style="display:none">
										<p id="successUpdateModalNotificationMessage"></p>
									</div>
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<input type="hidden" class="form-control input-sm" id="updateId" name="updateId">
								<div class="col-md-6 col-xs-12">
									<label>Name</label>
									<input type="text" class="form-control input-sm" id="updateName" name="updateName" maxlength="50">
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Location</label>
									<input type="text" class="form-control input-sm" id="updateLocation" name="updateLocation" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-6 col-xs-12">
									<label>Proprietor</label>
									<input type="text" class="form-control input-sm" id="updateProprietor" name="updateProprietor" maxlength="50">
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Contact Number</label>
									<input type="text" class="form-control input-sm" id="updateContactNumber" name="updateContactNumber" maxlength="50">
								</div>
							</div>
							<div class="row" style="margin-bottom: 5px">
								<div class="col-md-6 col-xs-12">
									<label>Remittance Bank</label>
									<input type="text" class="form-control input-sm" id="updateRemittanceBank" name="updateRemittanceBank" list="banks" maxlength="50">
									<datalist id="banks">
										<c:forEach items="${banks}" var="bank">
											<option value="${bank.name}">
										</c:forEach>
									</datalist>
								</div>
								<div class="col-md-6 col-xs-12">
									<label>Status</label>
									<select class="form-control input-sm" id="updateStatus" name="updateStatus">
										<option value="Active">Active</option>
										<option value="Inactive">Inactive</option>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-lg btn-block btn-success" id="btnUpdateBranch" onclick="updateBranch()">Update</button>
							<button type="button" class="btn btn-lg btn-block btn-danger" data-dismiss="modal">Cancel</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Branches</div>
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover" id="dataTables-branches">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Location</th>
											<th>Proprietor</th>
											<th>Contact Number</th>
											<th>Bank</th>
											<th>Status</th>
											<th>Update</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${branches}" var="branch">
											<tr id="branchId_${branch.id}">
												<td id="branchRowId_${branch.id}"><c:out value="${branch.id}" /></td>
												<td id="branchRowName_${branch.id}"><c:out value="${branch.name}" /></td>
												<td id="branchRowLocation_${branch.id}"><c:out value="${branch.location}" /></td>
												<td id="branchRowProprietor_${branch.id}"><c:out value="${branch.proprietor}" /></td>
												<td id="branchRowContactNumber_${branch.id}"><c:out value="${branch.contactNumber}" /></td>
												<td id="branchRowRemittanceBank_${branch.id}"><c:out value="${branch.remittanceBank}" /></td>
												<td id="branchRowStatus_${branch.id}"><c:out value="${branch.status}" /></td>
												<td style="width: 100px;">
													<input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateBranchModal" data-id="${branch.id}" />
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
			$('#dataTables-branches').DataTable({
				responsive : true,
		        "order": [[ 0, "desc" ]]
			});
		});

		$('#addBranchModal').on('show.bs.modal', function(e) {
			$('#addName').val("");
			$('#addLocation').val("");
			$('#addProprietor').val("");
			$('#addContactNumber').val("");
			$('#addRemittanceBank').val("");
			$("#addRemittanceBank").val($("#addRemittanceBank option:first").val());
			$('#addStatus option[value=Active]').attr('selected', 'selected');
			$('#successAddModalNotificationDiv').css('display','none');
			$('#successAddModalNotificationMessage').text("");
		});

		function saveNewBranch() {
			var result = insertNewBranch();
			var name = $("#addName").val();
			if (result == "success") {
				$('#addBranchModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully added new branch");
				addNewBranchToTable(name);
			} else {
				$('#successAddModalNotificationDiv').css('display', 'block');
				$('#successAddModalNotificationMessage').text(result);
			}
		}

		function insertNewBranch() {
			var validationResult = "";
			var name = $("#addName").val().trim();
			var location = $("#addLocation").val().trim();
			var proprietor = $("#addProprietor").val().trim();
			var contactNumber = $("#addContactNumber").val().trim();
			var remittanceBank = $("#addRemittanceBank").val();
			var status = $("#addStatus").val();
			$.ajax({
				type : 'POST',
				url : 'addNewBranch',
				data : {
					'name' : name,
					'location' : location,
					'proprietor' : proprietor,
					'remittanceBank' : remittanceBank,
					'contactNumber' : contactNumber,
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

		function addNewBranchToTable(name) {
			$.ajax({
				type : 'POST',
				url : 'getBranchByBranchName',
				data : {
					'name' : name
				},
				dataType : 'json',
				async : true,
				success : function(result) {
					var row = '<tr id="branchId_' + result.id + '">';
					row += '<td id="branchRowId_' + result.id + '">' + result.id + '</td>';
					row += '<td id="branchRowName_' + result.id + '">' + result.name + '</td>';
					row += '<td id="branchRowLocation_' + result.id + '">' + result.location + '</td>';
					row += '<td id="branchRowProprietor_' + result.id + '">' + result.proprietor + '</td>';
					row += '<td id="branchRowContactNumber_' + result.id + '">' + result.contactNumber + '</td>';
					row += '<td id="branchRowRemittanceBank_' + result.id + '">' + result.remittanceBank + '</td>';
					row += '<td id="branchRowStatus_' + result.id + '">' + result.status + '</td>';
					row += '<td style="width: 100px;"><input type="button" value="Update" class="btn btn-xs btn-block btn-flat" data-toggle="modal" data-target="#updateBranchModal" data-id="' + result.id + '"/></td>';
					row += '</tr>';
					$('#dataTables-branches tr:first').after(row);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.status + ' ' + jqXHR.responseText);
					alert(errorThrown);
				}
			});
		}

		$('#updateBranchModal').on('show.bs.modal', function(e) {
			var id = $(e.relatedTarget).data('id');
			$('#updateId').attr("value", $('#branchRowId_' + id).text());
			$('#updateName').attr("value",$('#branchRowName_' + id).text());
			$('#updateLocation').attr("value",$('#branchRowLocation_' + id).text());
			$('#updateProprietor').attr("value",$('#branchRowProprietor_' + id).text());
			$('#updateContactNumber').attr("value",$('#branchRowContactNumber_' + id).text());
			$('[name=updateRemittanceBank]').val($('#branchRowRemittanceBank_' + id).text());
			$('[name=updateStatus]').val($('#branchRowStatus_' + id).text());
			$('#successUpdateModalNotificationDiv').css('display','none');
			$('#successUpdateModalNotificationMessage').text("");
		});

		function updateBranch() {
			var result = updateExistingBranch();
			var id = $("#updateId").val().trim();
			var name = $("#updateName").val().trim();
			var location = $("#updateLocation").val().trim();
			var proprietor = $("#updateProprietor").val().trim();
			var contactNumber = $("#updateContactNumber").val().trim();
			var remittanceBank = $("#updateRemittanceBank").val();
			var status = $("#updateStatus").val();
			if (result == "success") {
				$('#updateBranchModal').modal('toggle');
				$('#successNotificationDiv').css('display', 'block');
				$('#successNotificationMessage').text("Successfully updated branch");
				updateBranchToTable(id, name, location, proprietor, contactNumber, remittanceBank, status);
			} else {
				$('#successUpdateModalNotificationDiv').css('display', 'block');
				$('#successUpdateModalNotificationMessage').text(result);
			}
		}

		function updateExistingBranch() {
			var validationResult = "";
			var id = $("#updateId").val().trim();
			var name = $("#updateName").val().trim();
			var location = $("#updateLocation").val().trim();
			var proprietor = $("#updateProprietor").val().trim();
			var contactNumber = $("#updateContactNumber").val().trim();
			var remittanceBank = $("#updateRemittanceBank").val();
			var status = $("#updateStatus").val();
			$.ajax({
				type : 'POST',
				url : 'updateBranch',
				data : {
					'id' : id,
					'name' : name,
					'location' : location,
					'proprietor' : proprietor,
					'contactNumber' : contactNumber,
					'remittanceBank' : remittanceBank,
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

		function updateBranchToTable(id, name, location, proprietor, contactNumber, remittanceBank, status) {
			$('#branchRowId_' + id).text(id);
			$('#branchRowName_' + id).text(name);
			$('#branchRowLocation_' + id).text(location);
			$('#branchRowProprietor_' + id).text(proprietor);
			$('#branchRowContactNumber_' + id).text(contactNumber);
			$('#branchRowRemittanceBank_' + id).text(remittanceBank);
			$('#branchRowStatus_' + id).text(status);
		}
	</script>
</body>

</html>	