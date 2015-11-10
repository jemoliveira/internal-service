function monitorController($scope, $http, $timeout) {

	$("#myModal").modal('show');

	$scope.timeInMs = 0;

	var countUp = function() {

		$scope.pageToGet = 0;

		$scope.state = 'busy';

		$scope.lastAction = '';

		$scope.url = "/internal-service-web/monitor";

		$scope.errorOnSubmit = false;
		$scope.errorIllegalAccess = false;
		$scope.displayMessageToUser = false;
		$scope.displayValidationError = false;
		$scope.displaySearchMessage = false;
		$scope.displaySearchButton = false;
		$scope.displayCreateContactButton = false;

		$scope.contact = {};

		$scope.searchFor = "";

		$scope.getContactList = function () {

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
					window.location.reload();				
				});
		};

		$scope.populateTable = function (data) {

			if (data.pagesCount > 0) {
				$scope.state = 'list';
				$scope.page = {						
						source: data.listMonitor,
						source2: data.listMonitor2,
						sourceAllTrans: data.listAllTrans,
						sourceAllTrans2: data.listAllTrans2, 
						sourceSaw: data.listSaw, 
						sourceSaw2: data.listSaw2, 
						sourceTms: data.listTms,
						sourceTms2: data.listTms2,
						currentPage: $scope.pageToGet, 
						pagesCount: data.pagesCount, 
						totalContacts : data.total

				};
				
				/*
				$scope.sourceSawAux1;
				$scope.sourceSawAux2;
				$scope.numsSaw = [];
				for(var i = 0; i < data.listSaw.length; i++){
					$scope.sourceSawAux = $scope.page.source[i];
					if($scope.sourceSawAux.length % 4 == 0){
						$scope.numsSaw[i] = $scope.sourceSawAux;
						$scope.sourceSawAux = null;
					}
				}
*/
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

			$("#myModal").modal('hide');

			$scope.timeInMs+= 100000;
			$timeout(countUp, 100000);

		};

		$scope.startDialogAjaxRequest = function () {
			$scope.displayValidationError = false;
			$scope.previousState = $scope.state;
			$scope.state = 'busy';
		};

		$scope.finishAjaxCallOnSuccess = function (data, modalId, isPagination) {
			$scope.populateTable(data);
			if(!isPagination){
				if(modalId){
					$scope.exit(modalId);
				}
			}
			$scope.lastAction = '';
		};
		$scope.getContactList();
	};

	//alert('1');
	$timeout(countUp, 10);
	//alert('2');

}