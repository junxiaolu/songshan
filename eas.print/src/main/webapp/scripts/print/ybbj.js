var PaperParam = [ {
	"Id" : 1947,
	"Name" : "双胶纸",
	"Weight" : 60,
	"Pid" : 35
}, {
	"Id" : 1948,
	"Name" : "双胶纸",
	"Weight" : 70,
	"Pid" : 36
}, {
	"Id" : 1949,
	"Name" : "双胶纸",
	"Weight" : 80,
	"Pid" : 37
}, {
	"Id" : 1950,
	"Name" : "双胶纸",
	"Weight" : 100,
	"Pid" : 38
}, {
	"Id" : 1951,
	"Name" : "双胶纸",
	"Weight" : 120,
	"Pid" : 39
}, {
	"Id" : 1952,
	"Name" : "铜版（特级）",
	"Weight" : 80,
	"Pid" : 78
}, {
	"Id" : 1953,
	"Name" : "铜版（特级）",
	"Weight" : 105,
	"Pid" : 79
}, {
	"Id" : 1954,
	"Name" : "铜版（特级）",
	"Weight" : 128,
	"Pid" : 80
}, {
	"Id" : 1955,
	"Name" : "铜版（特级）",
	"Weight" : 157,
	"Pid" : 86
}, {
	"Id" : 1956,
	"Name" : "铜版（特级）",
	"Weight" : 200,
	"Pid" : 87
}, {
	"Id" : 1957,
	"Name" : "铜版（特级）",
	"Weight" : 250,
	"Pid" : 88
}, {
	"Id" : 1958,
	"Name" : "铜版（特级）",
	"Weight" : 300,
	"Pid" : 221
}, {
	"Id" : 1959,
	"Name" : "铜版纸",
	"Weight" : 80,
	"Pid" : 21
}, {
	"Id" : 1960,
	"Name" : "铜版纸",
	"Weight" : 105,
	"Pid" : 22
}, {
	"Id" : 1961,
	"Name" : "铜版纸",
	"Weight" : 128,
	"Pid" : 23
}, {
	"Id" : 1962,
	"Name" : "铜版纸",
	"Weight" : 157,
	"Pid" : 24
}, {
	"Id" : 1963,
	"Name" : "铜版纸",
	"Weight" : 200,
	"Pid" : 25
}, {
	"Id" : 1964,
	"Name" : "铜版纸",
	"Weight" : 250,
	"Pid" : 26
}, {
	"Id" : 1965,
	"Name" : "铜版纸",
	"Weight" : 300,
	"Pid" : 27
}, {
	"Id" : 1966,
	"Name" : "哑粉（特级）",
	"Weight" : 80,
	"Pid" : 82
}, {
	"Id" : 1967,
	"Name" : "哑粉（特级）",
	"Weight" : 105,
	"Pid" : 89
}, {
	"Id" : 1968,
	"Name" : "哑粉（特级）",
	"Weight" : 128,
	"Pid" : 90
}, {
	"Id" : 1969,
	"Name" : "哑粉（特级）",
	"Weight" : 157,
	"Pid" : 91
}, {
	"Id" : 1970,
	"Name" : "哑粉（特级）",
	"Weight" : 200,
	"Pid" : 218
}, {
	"Id" : 1971,
	"Name" : "哑粉（特级）",
	"Weight" : 250,
	"Pid" : 219
}, {
	"Id" : 1972,
	"Name" : "哑粉（特级）",
	"Weight" : 300,
	"Pid" : 220
}, {
	"Id" : 1973,
	"Name" : "哑粉纸",
	"Weight" : 105,
	"Pid" : 31
}, {
	"Id" : 1974,
	"Name" : "哑粉纸",
	"Weight" : 128,
	"Pid" : 32
}, {
	"Id" : 1975,
	"Name" : "哑粉纸",
	"Weight" : 157,
	"Pid" : 33
}, {
	"Id" : 1976,
	"Name" : "哑粉纸",
	"Weight" : 200,
	"Pid" : 34
}, {
	"Id" : 1977,
	"Name" : "哑粉纸",
	"Weight" : 250,
	"Pid" : 116
}, {
	"Id" : 1978,
	"Name" : "哑粉纸",
	"Weight" : 300,
	"Pid" : 217
} ];

$(function() {

	init();

	$("#autoPrice").bind("click", function() {
		var isSuccess = price();
		if (isSuccess) {
			showIframe();
		} else {
			hideIframe();
		}
		return isSuccess;
	});
});

function price() {
	if (check()) {		
		$('#autoPrice').attr('disabled', true);
		$('#autoPrice').val('正在报价...');
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : "json",
			data : {
				"paperSize" : $("#size").combobox("getValue"),
				"pages" : $("#pages").val(),			
				"coverPaper" : $("#coverPaperWeight").combobox("getValue"),
				"insidePaper" : $("#insidePaperWeight").combobox("getValue"),
				"quantity" : $("#quantity").val(),
				"isHorseRidingNail" : $("#isHorseRidingNail").prop("checked"),			
				"isCoverLaminate" : $("#isCoverLaminate").prop("checked"),
				"isAdhesiveBinding" : $("#isAdhesiveBinding").prop("checked"),			
				"isCoverUV" : $("#isCoverUV").prop("checked"),
				"isCoverBronzing" : $("#isCoverBronzing").prop("checked")
			},
			url: './../../samplepricecalc/calculate.do',
			error: function () {//请求失败处理函数
				$.messager.alert('错误提示','请求失败！','warning');
				$('#autoPrice').attr('disabled', false);
				$('#autoPrice').val('自动报价');
				return false;
			},
			success: function(data) {
				if (data.success) {
					$('#priceQuantity').html($("#quantity").val());
					var date = new Date();        
					$('#priceTime').html(date.toLocaleDateString());
					$('#priceSize').html($("#size").combobox("getText"));
					$('#pricePages').html($("#pages").val());
					$('#priceCoverPaper').html($("#coverPaper").combobox("getText") + " " + $("#coverPaperWeight").combobox("getValue") + " G");
					$('#priceInsidePaper').html($("#insidePaper").combobox("getText") + " " + $("#insidePaperWeight").combobox("getValue") + " G");
					var processHtml = "";
					if ($("#isHorseRidingNail").prop("checked")) {
						processHtml += "骑马钉 ";
					}
					if ($("#isCoverLaminate").prop("checked")) {
						processHtml += "封面覆膜 ";
					}
					if ($("#isAdhesiveBinding").prop("checked")) {
						processHtml += "胶装 ";
					}
					if ($("#isCoverUV").prop("checked")) {
						processHtml += "封面UV ";
					}
					if ($("#isCoverBronzing").prop("checked")) {
						processHtml += "封面烫金 ";
					}
					$('#priceProcess').html(processHtml);
					$('#totalPrice').html(data.price);
					$('#mail_result').html($('input[name="mail"]:checked').val());							
				}
				$('#autoPrice').attr('disabled', false);
				$('#autoPrice').val('自动报价');
				return true;
			}
		});
	} else {
		return false;
	}
	return true;
}

function showIframe() {
	$("#autoPriceList").attr("height", "515px;");
	$("#autoPriceList")[0].style.display = "";
}
function hideIframe() {
	$("#autoPriceList").attr("height", "0px;");
	$("#autoPriceList")[0].style.display = "none";
}
// 页面初始化信息
function init() {
	// 封面默认
	var _currentPaperWeight = 250;

	var data = [];
	data.push({ "text": "克重", "id": "" });
	for ( var i = 0; i < PaperParam.length; i++) {
		if (_currentPaperName == PaperParam[i].Name) {
			if (PaperParam[i].Weight == _currentPaperWeight) {
				data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight, "selected": true});
			} else {
				data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight });
			}
		}
	}
	$("#coverPaperWeight").combobox("loadData", data);

	// 内页默认
	_currentPaperWeight = 157;

	data = [];
	data.push({ "text": "克重", "id": "" });
	for ( var i = 0; i < PaperParam.length; i++) {
		if (_currentPaperName == PaperParam[i].Name) {
			if (PaperParam[i].Weight == _currentPaperWeight) {
				data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight, "selected": true});
			} else {
				data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight });
			}
		}
	}
	$("#insidePaperWeight").combobox("loadData", data);

	$("#coverPaper").combobox({ 
		onChange: function(n,o) {

			var currentPaperName = n;

			var data = [];
			data.push({ "text": "克重", "id": "", "selected": true });
			for ( var i = 0; i < PaperParam.length; i++) {
				if (currentPaperName == PaperParam[i].Name) {
					data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight });
				}
			}
			$("#coverPaperWeight").combobox("loadData", data);

	}
	});

	$("#insidePaper").combobox({
		onChange: function(n,o) {

				var currentPaperName = n;

				var data = [];
				data.push({ "text": "克重", "id": "", "selected": true });
				for ( var i = 0; i < PaperParam.length; i++) {
					if (currentPaperName == PaperParam[i].Name) {
						data.push({ "text": PaperParam[i].Weight, "id": PaperParam[i].Weight });
					}
				}
				$("#insidePaperWeight").combobox("loadData", data);
			}
	});
	
	var _currentPaperName = "铜版纸";

	var data;
	data = [];	
	
	// 初始化纸张信息
	var tempAttri = "";
	for ( var i = 0; i < PaperParam.length; i++) {
		if (!StringIsInString(tempAttri, "&" + PaperParam[i].Name)) {
			if (PaperParam[i].Name == _currentPaperName) {
				data.push({ "text": PaperParam[i].Name, "id": PaperParam[i].Name, "selected": true});
			} else {
				data.push({ "text": PaperParam[i].Name, "id": PaperParam[i].Name });
			}
		}
		tempAttri += "&" + PaperParam[i].Name + ",";
	}
	$("#coverPaper").combobox("loadData", data);
	$("#insidePaper").combobox("loadData", data);
}

// 数据完整性检查
function check() {

	if ($("#coverPaper").combobox("getValue") == '') {
		alert('请选择“封面纸张种类”，再继续报价！');
		return false;
	}

	if ($("#coverPaperWeight").combobox("getValue") == '') {
		alert('请选择“封面纸张克重”，再继续报价！');
		return false;
	}

	if ($("#pages").val() == '') {
		alert('请选择填写“P数”，再继续报价！');
		return false;
	} else {
		var number = parseInt($("#pages").val());
		if (number % 4 != 0) {
			alert('“P数”必须为4的整数备，请重新输入');
			return false;
		}
	}

	if ($("#insidePaper").combobox("getValue") == '') {
		alert('请选择“内页纸张种类”，再继续报价！');
		return false;
	}

	if ($("#insidePaperWeight").combobox("getValue") == '') {
		alert('请选择“内页纸张克重”，再继续报价！');
		return false;
	}

	if ($("#quantity").val() == '') {
		alert('请选择“印刷数量”，再继续报价！');
		return false;
	}

	return true;
}

function StringIsInString(targetString, searchString) {
	var regu = "(" + searchString + ")"
	var re = new RegExp(regu);
	if (targetString.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}