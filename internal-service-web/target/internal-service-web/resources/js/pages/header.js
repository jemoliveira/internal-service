function LocationController($scope, $location) {
	
    if($location.$$absUrl.lastIndexOf('/contacts') > 0){
        $scope.activeURL = 'contacts';
    } else if($location.$$absUrl.lastIndexOf('/monitor') > 0){
        $scope.activeURL = 'monitor';
    } else {
		$scope.activeURL = 'home';
	}
}