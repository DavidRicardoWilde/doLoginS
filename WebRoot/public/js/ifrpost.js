IframePost = function() {
    var setFrame = function(oForm, FrmName) {
		oForm.target = FrmName;
	},
	createForm = function(obj) {
		var oForm = document.createElement("form");
		oForm.id = "forPost";
		oForm.method = "post";
		oForm.action = obj.Url;
		oForm.target = obj.Target.name;
		var oIpt, arrParams;
		arrParams = obj.Params;
		for (var tmpName in arrParams) {
			oIpt = document.createElement("input");
			oIpt.type = "hidden";
			oIpt.name = tmpName;
			oIpt.value = arrParams[tmpName];
			oForm.appendChild(oIpt);
		}
		return oForm;
	},
	deleteForm = function() {
		var oOldFrm = document.getElementById("forPost");
		if (oOldFrm) {
			document.body.removeChild(oOldFrm);
		}
	}

    return {
       doPost: function(obj) {
            deleteForm();
            var oForm = createForm(obj);
			document.body.appendChild(oForm);
			setFrame(oForm, obj.Target);
            oForm.submit();
            deleteForm();
        }
    }
}();
