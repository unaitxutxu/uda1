jQuery(document).ready(function () {
	$("#grid_usuarios").rup_grid({
		url:"../patrones/usuarios",
		colNames: [
			"id",
			"nombre",
			"apellido1",
			"apellido2",
			"ejie",
			"fechaAlta",
			"fechaBaja"
		],
		colModel: [
			{ name: "id",
				label: "id",
				index: "id",
				width: "150",
				sortable: true
			},
			{ name: "nombre",
				label: "nombre",
				index: "nombre",
				width: "150",
				sortable: true
			},
			{ name: "apellido1",
				label: "apellido1",
				index: "apellido1",
				width: "150",
				sortable: true
			},
			{ name: "apellido2",
				label: "apellido2",
				index: "apellido2",
				width: "150",
				sortable: true
			},
			{ name: "ejie",
				label: "ejie",
				index: "ejie",
				width: "150",
				sortable: true,
				editoptions: {
					defaultValue:"0",
					value:{"1":"Sí","0":"No"}
				},
				formatter:"select"
			},
			{ name: "fechaAlta",
				label: "fechaAlta",
				index: "fechaAlta",
				width: "150",
				sortable: true
			},
			{ name: "fechaBaja",
				label: "fechaBaja",
				index: "fechaBaja",
				width: "150",
				sortable: true
			}
	    ],
		width: 600,
		pagerName:"pager",
		sortable: true
	});
	
	$("#btnInsertar").bind("click", function () {
		var properties = {
			id : $("#id").val(),
			nombre : $("#nombre").val(),
			apellido1 : $("#apellido1").val(),
			apellido2 : $("#apellido2").val(),
			ejie : $("#ejie").val(),
			fechaAlta : $("#fechaAlta").val(),
			fechaBaja : $("#fechaBaja").val()
			}, ids;

		ids = $("#grid_usuarios").rup_grid("getDataIDs");
		$("#grid_usuarios").rup_grid("addRowData", Number(ids[ids.length - 1]) + 1, properties, "first");
	});

	$("#btnEditar").bind("click", function () {
		var properties = {
			id : $("#id").val(),
			nombre : $("#nombre").val(),
			apellido1 : $("#apellido1").val(),
			apellido2 : $("#apellido2").val(),
			ejie : $("#ejie").val(),
			fechaAlta : $("#fechaAlta").val(),
			fechaBaja : $("#fechaBaja").val()
			}, selRows;
		selRows = $("#grid_usuarios").rup_grid("getSelectedRows");
		if (selRows === undefined) { 
			alert("debe seleccionar una fila.");
		}else {
			if (selRows.length === 1) {
				$("#grid_usuarios").rup_grid("setRowData", selRows[0], properties);
			} else {
				alert("Hay mas de una fila seleccionada.");
			}
		}
	});
	
	$("#btnSel").bind("click", function () {
		var selRows = $("#grid_usuarios").rup_grid("getSelectedRows"), data;
		if (selRows[0] === null) { 
			alert("Debe seleccionar una fila.");
		}else {
			data = $("#grid_usuarios").rup_grid("getRowData", selRows[0]);
			if (data !== null) {
				$("#id").val(data.id);
				$("#nombre").val(data.nombre);
				$("#apellido1").val(data.apellido1);
				$("#apellido2").val(data.apellido2);
				$("#ejie").val(data.ejie);
				$("#fechaAlta").val(data.fechaAlta);
				$("#fechaBaja").val(data.fechaBaja);
			}
		}
	});
	
	$("#btnBorrar").bind("click", function () {
		var selRows = $("#grid_usuarios").rup_grid("getSelectedRows"), i;
		if (selRows[0] === null) { 
			alert("Debe seleccionar una fila.");
		}else {
			for (i = 0; i < selRows; i++) {
				$("#grid_usuarios").rup_grid("delRowData", selRows[i]);
			}
		}		
	});
	
	$("#btnReload").bind("click", function () {
		$("#grid_usuarios").rup_grid("reloadGrid");
	});
	
	$("#fechaAlta").rup_date({
		noWeekend : true
	});
	$("#fechaBaja").rup_date({
		noWeekend : true
	});
	
});