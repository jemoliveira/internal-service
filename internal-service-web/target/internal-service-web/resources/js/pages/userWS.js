function controller($scope, $http) {
    $scope.pageToGet = 0;
    
    $scope.passwordLength = 11;
    $scope.addUpper       = true;
    $scope.addNumbers     = true;
    $scope.addSymbols        = true;

    $scope.state = 'busy';
    $scope.bean = {};

    $scope.lastAction = '';

    $scope.url = "/internal-service-web/protected/userWs";

    $scope.errorOnSubmit = false;
    $scope.errorIllegalAccess = false;
    $scope.displayMessageToUser = false;
    $scope.displayValidationError = false;
    $scope.displaySearchMessage = false;
    $scope.displaySearchButton = false;
    $scope.displayCreateContactButton = false;

    $scope.searchFor = "";
    

    $scope.getList = function () {
        var url = $scope.url;
        $scope.lastAction = 'list';

        $scope.startDialogAjaxRequest();

        var config = {params: {page: $scope.pageToGet}};

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, null, false);
            })
            .error(function () {
                $scope.state = 'error';
                $scope.displayCreateContactButton = false;
            });
    };

    $scope.populateTable = function (data) {
        if (data.pagesCount > 0) {
            $scope.state = 'list';

            $scope.page = {source: data.list, currentPage: $scope.pageToGet, pagesCount: data.pagesCount, total : data.total};

            if($scope.page.pagesCount <= $scope.page.currentPage){
                $scope.pageToGet = $scope.page.pagesCount - 1;
                $scope.page.currentPage = $scope.page.pagesCount - 1;
            }

            $scope.displayCreateContactButton = true;
            $scope.displaySearchButton = true;
        } else {
            $scope.state = 'noresult';
            $scope.displayCreateContactButton = true;

            if(!$scope.searchFor){
                $scope.displaySearchButton = false;
            }
        }

        if (data.actionMessage || data.searchMessage) {
            $scope.displayMessageToUser = $scope.lastAction != 'search';

            $scope.page.actionMessage = data.actionMessage;
            $scope.page.searchMessage = data.searchMessage;
        } else {
            $scope.displayMessageToUser = false;
        }
    };

    $scope.changePage = function (page) {
        $scope.pageToGet = page;

        if($scope.searchFor){
            $scope.searchContact($scope.searchFor, true);
        } else{
            $scope.getList();
        }
    };

    $scope.exit = function (modalId) {
        $(modalId).modal('hide');

        $scope.contact = {};
        $scope.errorOnSubmit = false;
        $scope.errorIllegalAccess = false;
        $scope.displayValidationError = false;
        
    };

    $scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $("#loadingModal").modal('hide');

        if(!isPagination){
            if(modalId){
                $scope.exit(modalId);
            }
        }

        $scope.lastAction = '';
    };
    
    $scope.finishAjaxCallOnSuccessForm = function (data, modalId, isPagination) {
        $scope.populateTable(data);
        $(modalId).modal('hide');

        $scope.lastAction = '';
    };

    $scope.startDialogAjaxRequest = function () {
        $scope.displayValidationError = false;
        $("#loadingModal").modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    };
    
    $scope.startDialogAjaxRequestForm = function (modalId) {
        $scope.displayValidationError = false;
        $(modalId).modal('show');
        $scope.previousState = $scope.state;
        $scope.state = 'busy';
    };

    $scope.handleErrorInDialogs = function (status) {
        $("#loadingModal").modal('hide');
        $scope.state = $scope.previousState;

        // illegal access
        if(status == 403){
            $scope.errorIllegalAccess = true;
            return;
        }

        $scope.errorOnSubmit = true;
        $scope.lastAction = '';
    };

    $scope.addSearchParametersIfNeeded = function(config, isPagination) {
        if(!config.params){
            config.params = {};
        }

        config.params.page = $scope.pageToGet;

        if($scope.searchFor){
            config.params.searchFor = $scope.searchFor;
        }
    };

    $scope.resetContact = function() {
    	$scope.returnMsg = '';
        $scope.contact = {};
        $scope.bean = {};
    };

    $scope.create = function (newBeanForm) {
        if (!newBeanForm.$valid) {
        	
        	var wsUser = newBeanForm.wsUser.$viewValue;
        	var ascCode = newBeanForm.ascCode.$viewValue;
        	var corpCode = newBeanForm.corpCode.$viewValue;
        	var wsPwd = newBeanForm.wsPwd.$viewValue;
        	
        	if (wsUser !== undefined && ascCode !== undefined && corpCode !== undefined && wsPwd !== undefined ) {
        		if (wsUser.length != 11) {
        			$("#divErrWsUser").modal('show');	
        		}

        		if (ascCode.length != 7) {
        			$("#divErrAscCode").modal('show');	
        		}

        		if (corpCode.length != 4) {
        			$("#divErrCorpCode").modal('show');	
        		}

        		if (wsPwd.length != 10) {
        			$("#divErrWsPwd").modal('show');	
        		}
        	} 
        	
            $scope.displayValidationError = true;
            return;
        }
        
        $scope.lastAction = 'create';

        var url = $scope.url;

        var config = {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}};

        $scope.addSearchParametersIfNeeded(config, false);

        $scope.startDialogAjaxRequestForm("#downloadModal");

        $http.post(url, $.param($scope.bean), config)
            .success(function (data) {            	
            	
            	$scope.returnMsg = data.searchMessage; 
                $scope.finishAjaxCallOnSuccessForm(data, "#downloadModal", false);
                $("#returnMsgError").show();
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.selectedContact = function (contact) {
    	
    	$("#returnMsgErrorUpdate").hide();
    	$("#returnMsgError").hide();
    	
    	var selectedContact = angular.copy(contact);
    	
    	conversion(selectedContact);
    	
    	$scope.contact = selectedContact;
    };

    $scope.update = function (updateUserForm) {
    	if (!updateUserForm.$valid) {
        	
        	var wsUser = updateUserForm.wsUserUpdate.$viewValue;
        	var ascCode = updateUserForm.ascCodeUpdate.$viewValue;
        	var corpCode = updateUserForm.corpCodeUpdate.$viewValue;
        	var wsPwd = updateUserForm.wsPwdUpdate.$viewValue;
        	
        	if (wsUser !== undefined && ascCode !== undefined && corpCode !== undefined && wsPwd !== undefined ) {
        		if (wsUser.length != 11) {
        			$("#divErrWsUserUpdate").modal('show');	
        		}

        		if (ascCode.length != 7) {
        			$("#divErrAscCodeUpdate").modal('show');	
        		}

        		if (corpCode.length != 4) {
        			$("#divErrCorpCodeUpdate").modal('show');	
        		}

        		if (wsPwd.length != 10) {
        			$("#divErrWsPwdUpdate").modal('show');	
        		}
        	}
        	
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'update';

        var url = $scope.url+'/' + $scope.contact.wsUser;
        
        $scope.startDialogAjaxRequestForm("#downloadModalUpdate");
        
        var config = {};
        
        $scope.addSearchParametersIfNeeded(config, false);
        
        $http.put(url, $scope.contact, config)
            .success(function (data) {
            	if (data.searchMessage !== undefined && data.searchMessage != null) {
            		$scope.returnMsg = data.searchMessage;	
            	} else if (data.actionMessage !== undefined && data.actionMessage != null) {
            		$scope.returnMsg = data.actionMessage;	
            	}
            	
                $scope.finishAjaxCallOnSuccessForm(data, "#downloadModalUpdate", false);
                $("#returnMsgErrorUpdate").show();
                
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
        
    };

    $scope.searchContact = function (searchContactForm, isPagination) {
        if (!($scope.searchFor) && (!searchContactForm.$valid)) {
            $scope.displayValidationError = true;
            return;
        }

        $scope.lastAction = 'search';

        var url = $scope.url +  $scope.searchFor;

        $scope.startDialogAjaxRequest();

        var config = {};

        if($scope.searchFor){
            $scope.addSearchParametersIfNeeded(config, isPagination);
        }

        $http.get(url, config)
            .success(function (data) {
                $scope.finishAjaxCallOnSuccess(data, "#searchContactsModal", isPagination);
                $scope.displaySearchMessage = true;
            })
            .error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.deleteUser = function () {
    	
        $scope.lastAction = 'delete';

        var url = $scope.url+'/' + $scope.contact.wsUser;
        
        var params = {searchFor: $scope.searchFor, page: $scope.pageToGet};
        
        $scope.startDialogAjaxRequestForm("#downloadModalDelete");
        
        $("#btnConfirmDeleteUser").prop('disabled', true);
        
        $http({
            method: 'DELETE',
            url: url,
            params: params
        }).success(function (data) {
        	
        	$scope.finishAjaxCallOnSuccessForm(data, "#downloadModalDelete", false);
        	
        	$("#deleteUserModal").modal('hide');
        	$("#btnConfirmDeleteUser").prop('disabled', false);
                
            }).error(function(data, status, headers, config) {
                $scope.handleErrorInDialogs(status);
            });
    };

    $scope.resetSearch = function(){
        $scope.searchFor = "";
        $scope.pageToGet = 0;
        $scope.getList();
        $scope.displaySearchMessage = false;
    };
    
    $scope.createPassword = function() {
    	
        var lowerCharacters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        var upperCharacters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        var numbers = ['0','1','2','3','4','5','6','7','8','9'];
        var symbols = ['!', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', ':', ';', '=', '?', '@', '[', ']', '_', '{', '|', '}'];
        var finalCharacters = lowerCharacters;
        if($scope.addUpper){
            finalCharacters = finalCharacters.concat(upperCharacters);
        }
        if($scope.addNumbers){
            finalCharacters = finalCharacters.concat(numbers);
        }
        if($scope.addSymbols){
            finalCharacters = finalCharacters.concat(symbols);
        }
        var passwordArray = [];
        for (var i = 1; i < $scope.passwordLength; i++) {
            passwordArray.push(finalCharacters[Math.floor(Math.random() * finalCharacters.length)]);
        };
        
        return passwordArray.join("");
    };
    
    $scope.createPasswordForNewUser = function() {
    	$scope.bean.wsPwd = $scope.createPassword();
    };
    
    $scope.createPasswordForUpdatedUser = function() {
    	$scope.contact.wsPwd = $scope.createPassword();
    };
    

    $scope.getList();
}


function conversion(contact) {
	
	contact.ascCode = convertToNumber(contact.ascCode);
	contact.dev = convertToBoolean(contact.dev);
	contact.prd = convertToBoolean(contact.prd);
	contact.token = convertToBoolean(contact.token);
	
}

function convertToBoolean (element) {
	if (element !== undefined || element != null) {
		if (element == 'Sim') {
			element = true;
		} else {
			if (element == 'Não') {
				element = false;
			}
		}
	}
	return element;
}

function convertToNumber(element) {
	if (element !== undefined || element != null) {
		return Number(element);
	}
}
